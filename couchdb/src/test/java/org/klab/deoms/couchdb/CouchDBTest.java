package org.klab.deoms.couchdb;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.io.FileUtils;
import org.jcouchdb.db.Database;
import org.jcouchdb.document.Attachment;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;
import org.jcouchdb.util.CouchDBUpdater;
import org.junit.Test;
import org.klab.demos.couchdb.Order;

public class CouchDBTest {
    
    @Test
    public void createDocumentTest() throws IOException {
        
        Database db = new Database("localhost", "hello-world");

        Order order = new Order();
        order.setPrice(new BigDecimal(200));
        
        Attachment attachment = new Attachment("", FileUtils.readFileToByteArray(new File("src/main/resources/img.jpg")));
        order.addAttachment("image", attachment);

        // create the document in couchdb
        db.createDocument(order);
    }
    
    @Test
    public void viewResultTest() throws IOException {
        
        Database db = new Database("localhost", "hello-world");
        
        CouchDBUpdater updater = new CouchDBUpdater();
        updater.setDatabase(db);
        updater.setDesignDocumentDir(new File("src/main/resources/views"));
        updater.updateDesignDocuments();

        ViewResult<Order> result = db.queryView("demos/orders", Order.class, null, null);
        for (ValueRow<Order> vr : result.getRows()) {
            System.out.println(vr.getKey() + " : " + vr.getValue().getPrice());
        }
    }
}

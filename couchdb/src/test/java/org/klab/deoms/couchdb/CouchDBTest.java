package org.klab.deoms.couchdb;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.jcouchdb.db.Database;
import org.jcouchdb.util.CouchDBUpdater;
import org.junit.Test;
import org.klab.demos.couchdb.Order;

public class CouchDBTest {
    
    @Test
    public void createDocumentTest() {
        
        // create a database object pointing to the database "mycouchdb" on the local host    
        Database db = new Database("localhost", "hello-world");

        Order order = new Order();
        order.setCreationDate(new Date());
        order.setPrice(new BigDecimal(100));

        // create the document in couchdb
        db.createDocument(order);
    }
    
    @Test
    public void viewResultTest() throws IOException {
        
        // create a database object pointing to the database "mycouchdb" on the local host    
        Database db = new Database("localhost", "hello-world");
        
        CouchDBUpdater updater = new CouchDBUpdater();
        updater.setDatabase(db);
        updater.setDesignDocumentDir(new File("src/main/resources/views"));
        updater.updateDesignDocuments();

//        ViewResult<Map> result = db.queryView("myDesignDocId/myViewId", Map.class, null, null);
    }
}

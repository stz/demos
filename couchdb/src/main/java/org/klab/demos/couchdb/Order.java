package org.klab.demos.couchdb;

import java.math.BigDecimal;
import java.util.Date;

import org.jcouchdb.document.BaseDocument;
import org.svenson.JSONProperty;

public class Order extends BaseDocument {
    
    private Date creationDate;
    
    private BigDecimal price;
    
    @JSONProperty(value = "type", readOnly = true)
    public String getDocumentType() {
        return this.getClass().getSimpleName();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

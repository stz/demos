package org.klab.demos.couchdb;

import java.math.BigDecimal;

import org.jcouchdb.document.BaseDocument;
import org.svenson.JSONProperty;

public class Order extends BaseDocument {
    
    private BigDecimal price;
    
    @JSONProperty(value = "type", readOnly = true)
    public String getDocumentType() {
        return this.getClass().getSimpleName();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

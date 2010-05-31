package org.klab.demos.ibatis;

public class Trade {
    
    private long tradeId;
    private String action;
    private String symbol;
    private long shares;
    private double price;
    private String state;
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public long getShares() {
        return shares;
    }
    
    public void setShares(long shares) {
        this.shares = shares;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public long getTradeId() {
        return tradeId;
    }
    
    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }
    
    public String toString() {
        return ("TRADE " + tradeId + ": " + action + ", " + symbol + ", " + shares + " shares, $" + price + "/share (" + state + ")");
    }
}

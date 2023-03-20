package models;

public class Currency {
    private String symbol;
    private String signification;

    public Currency(String symbol, String signification) {
        this.symbol = symbol;
        this.signification = signification;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSignification() {
        return signification;
    }

    public void setSignification(String signification) {
        this.signification = signification;
    }
}

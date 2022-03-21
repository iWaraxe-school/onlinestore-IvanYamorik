package by.issoft;

public class Product {

    private final String name;
    private final int rate;
    private final double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString (){
        StringBuilder productStringBuilder = new StringBuilder();

        productStringBuilder.append("Product: ");
        productStringBuilder.append(name);
        productStringBuilder.append(" Rate: ");
        productStringBuilder.append(rate);
        productStringBuilder.append(" Price: ");
        productStringBuilder.append(price);
        return productStringBuilder.toString();

    }
}


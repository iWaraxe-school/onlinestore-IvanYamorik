package sortProperties;

import by.issoft.Product;

import java.util.Comparator;

public class RateComparator implements Comparator<Product> {

    public int compare(Product p1, Product p2) {
        if (p1.getRate() == p2.getRate()) {
            return 0;
        }
        if (p1.getRate() > p2.getRate()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

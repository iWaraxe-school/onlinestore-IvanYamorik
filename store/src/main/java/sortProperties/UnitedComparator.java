package sortProperties;

import by.issoft.Product;
import by.issoft.store.TagEnum;

import java.util.*;

public class UnitedComparator {
    public static final String ASCENDING = "asc";
    public static final int TOP_FIVE = 5;


    public List<Product> topProducts(List<Product> products) {
        System.out.println("Select top 5 products by price descending: ");

        List<Product> productList = products.stream()
                .sorted(new PriceComparator().reversed())
                .limit(TOP_FIVE).toList();
        return productList;
    }

    protected Comparator<Product> chooseComparator(Map.Entry<String, String> entry) {
        String field = entry.getKey();
        String value = entry.getValue();

        switch (TagEnum.valueOf(field.toUpperCase())) {
            case NAME -> {
                return value.equals(ASCENDING) ? new NameComparator() : new NameComparator().reversed();
            }
            case PRICE -> {
                return checkValue(value) ? new PriceComparator() : new PriceComparator().reversed();
            }
            case RATE -> {
                return checkValue(value) ? new RateComparator() : new RateComparator().reversed();
            }
            default -> System.out.println("No such fields in product");
        }
        throw new RuntimeException("Exeption while choosing comparator");
    }
    protected boolean checkValue(String value){
        return value.equals(ASCENDING);
    }


    public List<Product> sortProducts(List<Product> products, Map<String, String> propertiesMap) {
        List<Product> productList = new ArrayList<>(products);
        for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
            productList.sort(chooseComparator(entry));
        }
        return productList;
    }
}

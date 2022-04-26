package sortProperties;

import by.issoft.Product;
import by.issoft.store.TagEnum;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UnitedComparator {
    public static final String ASCENDING = "asc";
    public static final int TOP_FIVE = 5;


    public List<Product> topProducts(List<Product> products) {
        System.out.println("Select top 5 products by price descending: ");

        List<Product> productList = products.stream()
                .sorted(new PriceComparator().reversed())
                .limit(TOP_FIVE).toList();
        System.out.println(productList);
        return productList;
    }

    protected Comparator<Product> chooseComparator(Map.Entry<String, String> entry) {
        String field = entry.getKey();
        String value = entry.getValue();

        switch (TagEnum.valueOf(field.toUpperCase())) {
            case NAME -> {
                return checkValue(value) ? new NameComparator() : new NameComparator().reversed();
            }
            case PRICE -> {
                return checkValue(value) ? new PriceComparator() : new PriceComparator().reversed();
            }
            case RATE -> {
                return checkvalue(value) ? new RateComparator() : new RateComparator().reversed();
            }
            default -> System.out.println("No such fields in product");
        }
    }

    public List<Product> sortProducts(List<Product> products, Map<String, String> propertiesMap) {

    }
}

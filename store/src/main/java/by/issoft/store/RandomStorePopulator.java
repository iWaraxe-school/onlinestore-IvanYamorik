package by.issoft.store;

import com.github.javafaker.Faker;

public class RandomStorePopulator {
    private Faker faker = new Faker();

    public RandomStorePopulator(Faker faker) {
        this.faker = faker;
    }

    public RandomStorePopulator() {

    }

    public String getProductName(String categoryName) {
        switch (categoryName){
            case "Food":
                return faker.food().fruit();
            case "Milk":
                return "Milk with " + faker.food().ingredient();
            case "Beer":
                return faker.beer().name();
            default:
                return null;
        }
    };

    public int getRate() {
        return faker.number().randomDigit();
    }

    public double getPrice() {
        return faker.number().randomDouble(2,0,1000);
    }
}


package by.issoft;

import java.util.List;

public class Store {

    public static final int STORE_BIKE = 1;
    public static final int STORE_PHONE = 2;
    public static final int STORE_MILK = 3;
    public static final List<Integer> STORE_VALUES = List.of(STORE_BIKE, STORE_PHONE, STORE_MILK);

    private int value;

    public Store(int.value) {
        this.value = value;
    }
}

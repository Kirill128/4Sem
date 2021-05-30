package grsu.edu.oop.mealpalnner.models.api;

import java.util.List;

public class MealTime {
    private String name ;
    private List<Product> products ;

    public MealTime(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public MealTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

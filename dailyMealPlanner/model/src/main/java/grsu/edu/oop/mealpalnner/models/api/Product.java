package grsu.edu.oop.mealpalnner.models.api;

public class Product {
    private  String name ;
    private double grams ;
    private double fats ;
    private double carbs ;
    private double protein ;
    private double calories100 ;

    public Product(String name, double grams, double fats, double carbs, double protein, double calories100) {
        this.name = name;
        this.grams = grams;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
        this.calories100 = calories100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCalories100() {
        return calories100;
    }

    public void setCalories100(double calories100) {
        this.calories100 = calories100;
    }


}

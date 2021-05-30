package grsu.edu.oop.mealpalnner.models.api;

public class User {
    private double weight;
    private double height;
    private int age;
    public Activity activity ;
    public DailyRation dailyRation;


    public User(double weight, double height, int age, Activity activity, DailyRation dailyRation) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.activity = activity;
        this.dailyRation = dailyRation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public DailyRation getDailyRation() {
        return dailyRation;
    }

    public void setDailyRation(DailyRation dailyRation) {
        this.dailyRation = dailyRation;
    }

}

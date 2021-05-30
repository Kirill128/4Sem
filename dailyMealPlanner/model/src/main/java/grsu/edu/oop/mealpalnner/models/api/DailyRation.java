package grsu.edu.oop.mealpalnner.models.api;

import java.util.List;

public class DailyRation {
    private List<MealTime> mealTimes ;

    public List<MealTime> getMealTimes() {
        return mealTimes;
    }

    public void setMealTimes(List<MealTime> mealTimes) {
        this.mealTimes = mealTimes;
    }

    public DailyRation(List<MealTime> mealTimes) {
        this.mealTimes = mealTimes;
    }
}

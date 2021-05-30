package grsu.edu.oop.mealpalnner.models.api;

public enum Activity {
    Low("Низкая"),
    Normal("Нормальная"),
    Average("Средняя"),
    High("Высокая");

    public final String value;

    Activity(String value) {
        this.value=value;
    }
}

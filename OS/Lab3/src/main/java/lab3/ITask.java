package lab3;

public interface ITask  {
    void makeTask();
    void setMyNodeBox(MyNodeBox myNodeBox);
    MyNodeBox getMyNodeBox();
    boolean isInCriticalRegion();
    void setIsInCriticalRegion(boolean isInCriticalRegion);
}

package lab3;

public class Task2 implements Runnable{
    private MyNodeBox myNodeBox;

    public Task2(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }

    @Override
    public void run() {

    }


    public MyNodeBox getMyNodeBox() {
        return myNodeBox;
    }

    public void setMyNodeBox(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }
}

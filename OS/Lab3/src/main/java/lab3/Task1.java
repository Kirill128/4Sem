package lab3;

public class Task1 implements Runnable {
    private MyNodeBox myNodeBox;

    public Task1(MyNodeBox myNodeBox) {
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

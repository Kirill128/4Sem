package lab3;

public class Task2 implements Runnable,ITask{
    private MyNodeBox myNodeBox;

    public Task2(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }

    @Override
    public void run() {
        this.myNodeBox.getGoToCriticalPlaceButton().fire();
//        makeTask();
    }


    @Override
    public void makeTask() {
        String input=this.myNodeBox.getTextInputString();
        char c=input.toCharArray()[0];
        this.myNodeBox.setResultString("Result: "+(int)c);
    }

//---------------------------------------------------------------
    @Override
    public MyNodeBox getMyNodeBox() {
        return myNodeBox;
    }
    @Override
    public void setMyNodeBox(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }
}

package lab3;

public class Task1 implements Runnable,ITask {
    private MyNodeBox myNodeBox;

    public Task1(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }

    @Override
    public void run() {
        this.myNodeBox.getGoToCriticalPlaceButton().fire();
//        makeTask();
    }

    @Override
    public void makeTask() {
        int input=Integer.valueOf(this.myNodeBox.getTextInputString());
        this.myNodeBox.setResultString("Result: "+ factorial(input));
    }

    public long factorial(int input){
        if(input==1){
            return 1;
        }
        return input*factorial(input-1);
    }

    //---------------------------------------------------------
    @Override
    public MyNodeBox getMyNodeBox() {
        return myNodeBox;
    }

    @Override
    public void setMyNodeBox(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }
}

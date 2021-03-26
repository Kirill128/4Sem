package lab3;

public class Task1 implements ITask {
    private MyNodeBox myNodeBox;
    public boolean inCriticalRegion;
    public Task1(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
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
    public boolean isInCriticalRegion() {
        return this.inCriticalRegion;
    }
    @Override
    public void setIsInCriticalRegion(boolean isInCriticalRegion) {
        this.inCriticalRegion=isInCriticalRegion;
    }

    @Override
    public void setMyNodeBox(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
    }
}

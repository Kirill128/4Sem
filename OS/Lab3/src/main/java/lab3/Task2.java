package lab3;

public class Task2 implements ITask{
    private MyNodeBox myNodeBox;
    public boolean inCriticalRegion;

    public Task2(MyNodeBox myNodeBox) {
        this.myNodeBox = myNodeBox;
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

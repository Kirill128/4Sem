package lab.opmemory;

import javafx.scene.layout.HBox;

public class MemoryBlock  {
    private int startAddress;
    private int size;
    private boolean busy;

    private HBox hBox;

    public MemoryBlock(int startAddress, int size, HBox hBox) {
        this.startAddress = startAddress;
        this.size = size;
        this.hBox = hBox;
    }
    public MemoryBlock(int startAddress, int size) {
        this.startAddress = startAddress;
        this.size = size;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

}

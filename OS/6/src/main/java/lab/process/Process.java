package lab.process;

public abstract class Process {
    protected final int size;

    public int getSize() {
        return size;
    }

    public Process(int size) {
        this.size = size;
    }
}


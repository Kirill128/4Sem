package lab.process;

import javafx.scene.layout.HBox;
import lab.opmemory.MemoryBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessInAlg extends Process{
    private final int id;
    private final int startAddress;
    private final List<HBox> cells;
    private final List<MemoryBlock> memoryBlocks;
    public ProcessInAlg(int size, int id, int startAddress) {
        super(size);
        this.id = id;
        this.startAddress = startAddress;
        this.cells = new ArrayList<>();
        this.memoryBlocks=new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessInAlg that = (ProcessInAlg) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public int getStartAddress() {
        return startAddress;
    }
    public List<HBox> getCells() {
        return cells;
    }

    public List<MemoryBlock> getMemoryBlocks() {
        return memoryBlocks;
    }
}

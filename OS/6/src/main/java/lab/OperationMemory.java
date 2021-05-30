package lab;

import java.util.Iterator;
import java.util.List;

public class OperationMemory {
    private List<Process> processList;
    private int size;
    private int freeSpace;

    public OperationMemory(List<Process> processList, int size) {
        this.processList = processList;
        this.size = size;
    }

    public boolean loadProcessToMemory(Process process){

    }

    public void deleteProcessFromMemory(int id){

        Iterator<Process> iterator=this.processList.listIterator();


        this.processList.removeIf(e->e.getId()==id);
    }



    //------------------------------------------------
    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

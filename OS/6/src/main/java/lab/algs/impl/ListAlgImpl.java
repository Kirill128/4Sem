package lab.algs.impl;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import lab.algs.BiasAlg;
import lab.opmemory.MemoryBlock;
import lab.opmemory.OperationMemory;
import lab.process.Process;
import lab.process.ProcessInAlg;

import java.util.ArrayList;
import java.util.List;

public class ListAlgImpl implements BiasAlg {
    private final int segmentSize;
    private final List<ProcessInAlg> processes;
    private final OperationMemory operationMemory;
    private final List<MemoryBlock> memoryBlocks;
    private final List<MemoryBlock> freeSpaces;
    private int idCounter;

    public ListAlgImpl(int segmentSize, OperationMemory operationMemory, List<MemoryBlock> memoryBlocks) {
        this.segmentSize = segmentSize;
        this.operationMemory=operationMemory;
        this.processes = new ArrayList<>();
        this.memoryBlocks=memoryBlocks;
        this.freeSpaces=new ArrayList<>();
        this.freeSpaces.add(new MemoryBlock(0,operationMemory.getSize()));
        Platform.runLater(()->printMemoryGroups());
    }
    @Override
    public boolean addProcess(Process process) {
        for(MemoryBlock freeSpace : this.freeSpaces){
            if(freeSpace.getSize()>=process.getSize()){
                int processAllocatedCountCells= (process.getSize()%this.segmentSize==0)?
                        process.getSize()/this.segmentSize :
                        (process.getSize()/this.segmentSize) + 1;
                int processAllocatedMemory=processAllocatedCountCells*this.segmentSize;

                ProcessInAlg newProcess=new ProcessInAlg(processAllocatedMemory,
                        idCounter++,
                        freeSpace.getStartAddress());
                this.processes.add(newProcess);

                int countBlocks=0;
                for(MemoryBlock block : this.memoryBlocks){
                    if(block.getStartAddress()>=freeSpace.getStartAddress() &&
                            countBlocks<processAllocatedCountCells){
                        countBlocks++;
                        newProcess.getMemoryBlocks().add(block);
                        block.setBusy(true);
                        Platform.runLater(() -> {
                            block.gethBox().setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));
                            ((Label)block.gethBox().getChildren().get(1)).setText(String.format(" PID: %d, size: %d",newProcess.getId(),newProcess.getSize()));
                        });
                    }
                }


                freeSpace.setSize(freeSpace.getSize()-processAllocatedMemory);
                freeSpace.setStartAddress(newProcess.getStartAddress()+processAllocatedMemory);
                if(freeSpace.getSize()==0){
                    this.freeSpaces.remove(freeSpace);
                }
                Platform.runLater(()->printMemoryGroups());
                return true;
            }
        }
        return false;
    }


    @Override
    public void deleteProcess(int processId) {
        for(ProcessInAlg process : this.processes){
            if(process.getId()==processId){
                boolean freeSpaceExist=false;
                for(MemoryBlock memoryBlock : this.freeSpaces){
                    if(memoryBlock.getStartAddress()==(process.getStartAddress() + process.getSize())){
                        memoryBlock.setSize(memoryBlock.getSize()+process.getSize());
                        memoryBlock.setStartAddress(process.getStartAddress());
                        freeSpaceExist=true;
                        memoryBlock.setBusy(false);
                    }else if((memoryBlock.getStartAddress() + memoryBlock.getSize())==process.getStartAddress()){
                        memoryBlock.setSize(memoryBlock.getSize()+process.getSize());
                        freeSpaceExist=true;
                        memoryBlock.setBusy(false);
                    }
                }
                if(!freeSpaceExist){
                    this.freeSpaces.add(new MemoryBlock(process.getStartAddress(),process.getSize()));
                }

                Platform.runLater(() -> {
                    for(MemoryBlock memoryBlock : process.getMemoryBlocks()){
                        ((Label)memoryBlock.gethBox().getChildren().get(1)).setText("");
                        memoryBlock.gethBox().setBackground(new Background(new BackgroundFill(Color.rgb(0xfa,0xda,0xda),null,null)));
                    }
                    printMemoryGroups();

                });


                this.processes.remove(process);

                return;
            }
        }
    }

    @Override
    public void askOneCellFromAnother(final int pid,final int cellNumFrom,final int cellNumTo) {
        for(ProcessInAlg process : this.processes){
            if(pid==process.getId()){
                Platform.runLater(() -> {
                    MemoryBlock from=process.getMemoryBlocks().get(cellNumFrom);
                    MemoryBlock to=process.getMemoryBlocks().get(cellNumTo);
                    from.gethBox()
                                .setBackground(
                                        new Background(
                                                new BackgroundFill(Color.rgb(0xFA,0xEB,0xD7),null,null)));
                    ((Label)from.gethBox().getChildren().get(2))
                            .setText(String.format("| From %d to %d,process bias: %d",from.getStartAddress(),to.getStartAddress(),process.getStartAddress()));

                        to.gethBox()
                                .setBackground(
                                        new Background(
                                                new BackgroundFill(Color.rgb(0xFA,0xEB,0xD7),null,null)));
                        printMemoryGroups();
                });
            }
        }
    }

    @Override
    public void clear(int pid,int cellNumFrom,int cellNumTo) {
        for(ProcessInAlg process : this.processes){
            if(pid==process.getId()){
                Platform.runLater(() -> {
                    MemoryBlock from=process.getMemoryBlocks().get(cellNumFrom);
                    MemoryBlock to=process.getMemoryBlocks().get(cellNumTo);
                    from.gethBox()
                            .setBackground(
                                    new Background(
                                            new BackgroundFill(Color.YELLOW,null,null)));
                    ((Label)from.gethBox().getChildren().get(2)).setText("");

                    to.gethBox()
                            .setBackground(
                                    new Background(
                                            new BackgroundFill(Color.YELLOW,null,null)));
                });
            }
        }
    }

    private void printMemoryGroups(){
        for(ProcessInAlg process: this.processes){
            for(MemoryBlock block : process.getMemoryBlocks()){
                ((Label)block.gethBox().getChildren().get(3)).setText("/Pr G:"+process.getId());
            }
        }
        int index=0;
        for(MemoryBlock freeBlock : this.freeSpaces){
            for(MemoryBlock block : this.memoryBlocks){
                if(block.getStartAddress()>=freeBlock.getStartAddress() &&
                        block.getStartAddress() <= freeBlock.getStartAddress()+ freeBlock.getSize()){
                    ((Label)block.gethBox().getChildren().get(3)).setText("/ G:"+index);
                }
            }
            index++;
        }
    }

    public int getSegmentSize() {
        return segmentSize;
    }

    public List<ProcessInAlg> getProcesses() {
        return processes;
    }





}

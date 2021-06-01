package lab.algs;


import lab.process.Process;

public interface BiasAlg {
    boolean addProcess(Process process);
    void deleteProcess(int processId);
    void askOneCellFromAnother(int pid,int cellNumFrom,int cellNumTo);
    void clear(int pid,int cellNumFrom,int cellNumTo);
}

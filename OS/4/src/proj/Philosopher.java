package proj;

import java.util.concurrent.Semaphore;

class Philosopher extends Thread
{
    Semaphore sem;
    int eatCount;
    int id;
    Philosopher(Semaphore sem, int id)
    {
        this.sem=sem;
        this.id=id;
    }

    public void run()
    {
        try
        {
            while(true)
            {
                sem.acquire();
                System.out.println ("Философ " + id+" садится за стол-----------Он ел "+(eatCount++)+" раз-----------"+id+" in");

                sleep(500);

                System.out.println ("Философ " + id+" выходит из-за стола-------Он ел "+(eatCount)+" раз--------"+id+" out");
                sem.release();

                sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println ("у философа " + id + " проблемы со здоровьем");
        }
    }
}
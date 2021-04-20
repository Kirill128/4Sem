package lab;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread{
    private Semaphore sem;

    // поел ли философ
    private boolean full = false;

    private Integer name;

    Philosopher(Semaphore sem, Integer name,Semaphore mutex) {
        this.sem=sem;
        this.name=name;
    }
    @Override
    public void run() {
        while(true){
            try{

                    //Запрашиваем у семафора разрешение на выполнение
                    sem.acquire();
                    System.out.println (name + " садится за стол");

                    // философ ест
                    sleep(eatTime());
                    full = true;

                    System.out.println (name + " поел! Он выходит из-за стола");
                    sem.release();

                    // философ ушел, освободив место другим
                    sleep(thinkTime());

            }
            catch(InterruptedException e) {
                System.out.println ("Что-то пошло не так!");
            }
        }

    }

    public void think()throws InterruptedException{
        long thinkTime=thinkTime();
        System.out.printf("Philosopher "+name+ " is thinking "+thinkTime);
        sleep(thinkTime);
    }
    public void takeForks(){

    }
    public void putForks(){

    }
    public void test(){

    }

    public long eatTime(){
        return new Random().nextInt(1500)+500;
    }
    public long thinkTime(){
        return new Random().nextInt(1500)+500;
    }
}

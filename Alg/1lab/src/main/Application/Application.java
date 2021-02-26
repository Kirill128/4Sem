package main.Application;

import main.Rectangle.Rectangle;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        List<Rectangle> rectInfo=new ArrayList<Rectangle>();

        Rectangle R = new Rectangle(6, 8,0);                // создаем большой прямоугольник R в который будем пытаться складывать маленькие

        List<Rectangle> r = new ArrayList<Rectangle>();        // создаем массив маленьких прямоугольников r
        r.add(new Rectangle(3, 3,1,0,0));                  // и заполняем...
        r.add(new Rectangle(3, 5,2,0,0));
        r.add(new Rectangle(3, 2,3,0,0));
        r.add(new Rectangle(6, 2,4,0,0));
        Collections.sort(r, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return o2.getS()-o1.getS();
            }
        });   // сортируем по убыванию площади
        r.stream().forEach((a)->rectInfo.add(a));
        rectInfo.stream().forEach(System.out::println);
        System.out.printf(Test(R,r,rectInfo,0,0)?"Yes":"No");
        System.out.println("");
        rectInfo.stream().forEach(System.out::println);

    }

    public static boolean Test(Rectangle R,  List<Rectangle> r,List<Rectangle> infoList,int start_x,int start_y)  // функция проверяет можно ли составить большой прямоугольник R из маленьких r
    {
        for (Rectangle v : r)                // берем каждый прямоугольник из тех что надо вписать
        {
            if (!R.Contains(v)) continue;   // если не помещается берем следующий
            List<Rectangle> s = R.Split(v);             // иначе убираем его из большого прямоугольника и делим оставшееся на 2 части
            r.remove(v);                    // удаляем использованный прямоугольник
            infoList.get(infoList.size()-r.size()).setStart_x(start_x);
            infoList.get(infoList.size()-r.size()).setStart_y(start_y);
            if (s == null) {
                return true;
            }     // если размеры прямоугольников совпали то функция вернёт true
            else if (s.size() == 2)          // если получилось две части то проверяем каждую
                return Test(s.get(0),  r,infoList,start_x+v.getX(),start_y+v.getY()) && Test(s.get(1),  r,infoList,start_x+v.getX(),start_y+v.getY());
            else return Test(s.get(0),  r,infoList,start_x+v.getX(),start_y+v.getY());      // если одна, то только её
        }
        return false;                       // если не нашли что вписать то функция вернёт false
    }

    public static List<Rectangle> inputRectangles(){
        int count;
        while(true) {
            System.out.println("How much (less then 8):");
            count = inputInt();
            if(count>0 && count<8)break;
            else System.out.println("Wrong input ! Try again.");
        }
        List<Rectangle> rectangles=new ArrayList<>();
        for(int i=0;i<count;i++){
            System.out.println("Rectangle "+i+":");
            rectangles.add(inputRectangle(i));
        }
        return rectangles;
    }
    public static Rectangle inputRectangle(int num){
        System.out.println("Input size of X:");
        int x=inputInt();
        System.out.println("Input size of Y:");
        int y=inputInt();
        return new Rectangle(x,y,num);
    }
    public static int inputInt(){
        while(true){
            try(Scanner scan=new Scanner(System.in)){
                int a=scan.nextInt();
                return a;
            }catch (Exception e){
                System.out.println("Wrong input.Try again:");
            }
        }
    }
}

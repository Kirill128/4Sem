package eu.grsu.algoritms.main;

import eu.grsu.algoritms.rectangles.BigRectangle;
import eu.grsu.algoritms.rectangles.LowRectangle;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
//        BigRectangle bigLowRectangle=new BigRectangle(6,7);
//        BigRectangle bigLowRectangle=new BigRectangle(24,26);
        BigRectangle bigLowRectangle=new BigRectangle(1,5);

        List<LowRectangle> lowRectangleList=Application.getInitial();

        System.out.println("Answer:"+bigLowRectangle.canConsistOf(lowRectangleList));
        lowRectangleList.stream().forEach(System.out::println);
    }

    public static List<LowRectangle> getInitial(){
        List<LowRectangle> lowRectangleList=new ArrayList<LowRectangle>();
//        lowRectangleList.add(new LowRectangle(3,2));
//        lowRectangleList.add(new LowRectangle(3,3));
//        lowRectangleList.add(new LowRectangle(3,5));
//        lowRectangleList.add(new LowRectangle(6,2));

//          lowRectangleList.add(new LowRectangle(3,5));
//          lowRectangleList.add(new LowRectangle(1,3));
//          lowRectangleList.add(new LowRectangle(5,2));
//          lowRectangleList.add(new LowRectangle(5,1));
//          lowRectangleList.add(new LowRectangle(3,2));
//          lowRectangleList.add(new LowRectangle(3,3));

//        lowRectangleList.add(new LowRectangle(5,6,"1"));
//        lowRectangleList.add(new LowRectangle(5,9,"2"));
//        lowRectangleList.add(new LowRectangle(9,11,"3"));
//        lowRectangleList.add(new LowRectangle(4,5,"4"));
//        lowRectangleList.add(new LowRectangle(11,5,"5"));
//        lowRectangleList.add(new LowRectangle(3,4,"6"));
//        lowRectangleList.add(new LowRectangle(4,5,"7"));
//        lowRectangleList.add(new LowRectangle(4,10,"8"));
//        lowRectangleList.add(new LowRectangle(3,11,"9"));
//        lowRectangleList.add(new LowRectangle(5,12,"10"));
//        lowRectangleList.add(new LowRectangle(4,4,"11"));
//        lowRectangleList.add(new LowRectangle(3,4,"12"));
//        lowRectangleList.add(new LowRectangle(4,4,"13"));
//        lowRectangleList.add(new LowRectangle(3,4,"14"));
//        lowRectangleList.add(new LowRectangle(7,4,"15"));
//        lowRectangleList.add(new LowRectangle(4,9,"16"));
//        lowRectangleList.add(new LowRectangle(3,6,"17"));
//        lowRectangleList.add(new LowRectangle(2,9,"18"));
//        lowRectangleList.add(new LowRectangle(5,2,"19"));
//        lowRectangleList.add(new LowRectangle(5,1,"20"));
//        lowRectangleList.add(new LowRectangle(1,6,"21"));
//        lowRectangleList.add(new LowRectangle(4,4,"22"));
//        lowRectangleList.add(new LowRectangle(4,2,"23"));
//        lowRectangleList.add(new LowRectangle(1,3,"24"));
//        lowRectangleList.add(new LowRectangle(1,2,"25"));
//        lowRectangleList.add(new LowRectangle(1,4,"26"));

        lowRectangleList.add(new LowRectangle(3,2,"1"));
        lowRectangleList.add(new LowRectangle(7,1,"2"));
        lowRectangleList.add(new LowRectangle(3,3,"3"));
        lowRectangleList.add(new LowRectangle(2,4,"4"));
        lowRectangleList.add(new LowRectangle(9,2,"5"));
        lowRectangleList.add(new LowRectangle(4,3,"6"));
        lowRectangleList.add(new LowRectangle(3,5,"7"));
        lowRectangleList.add(new LowRectangle(5,2,"8"));
        lowRectangleList.add(new LowRectangle(3,1,"9"));
        lowRectangleList.add(new LowRectangle(3,4,"10"));

        return lowRectangleList;
    }
}

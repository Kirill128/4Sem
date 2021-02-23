package eu.grsu.algoritms.main;

import eu.grsu.algoritms.api.IInsideRect;
import eu.grsu.algoritms.api.IRectangle;
import eu.grsu.algoritms.rectangles.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        IRectangle bigRectangle=new Rectangle(12,6);
        List<Rectangle> rectangleList=Application.getInitial();

        boolean result = bigRectangle.canConsistOf(rectangleList,new IInsideRect() {
            @Override
            public boolean insideRect() {

                return false;
            }
        });
        System.out.printf("Answer:"+result);
    }

    public static List<Rectangle> getInitial(){
        List<Rectangle> rectangleList=new ArrayList<Rectangle>();
        rectangleList.add(new Rectangle(2,6));
        rectangleList.add(new Rectangle(5,2));
        rectangleList.add(new Rectangle(5,3));
        rectangleList.add(new Rectangle(5,3));
        rectangleList.add(new Rectangle(5,2));
        rectangleList.add(new Rectangle(3,2));
        rectangleList.add(new Rectangle(2,2));
        return rectangleList;
    }
}

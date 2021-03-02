package eu.grsu.algoritms.rectangles;

import eu.grsu.algoritms.api.IInsideRect;
import eu.grsu.algoritms.points.Point;

import java.util.ArrayList;
import java.util.List;

public class LowRectangle {
    protected int xSize;
    protected int ySize;
    protected int xStart;
    protected int yStart;
    protected int xEnd;
    protected int yEnd;
    private String name;

    private boolean mustBeInFinal;


    public LowRectangle(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }
    public LowRectangle(int xSize, int ySize,String name) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.name=name;
    }

    public static List<LowRectangle> cloneWithout(List<LowRectangle> source,int without){
        List<LowRectangle> result=new ArrayList<>(source);
        result.remove(without);
        return result;
    }
    public static List<LowRectangle> cloneWithout(List<LowRectangle> source,LowRectangle without){
        List<LowRectangle> result=new ArrayList<>(source);
        result.remove(without);
        return result;
    }

    public void rotateRect() {
        int buf;
        //swap x_size and y_size
        buf=this.getxSize();
        this.setxSize(this.getySize());
        this.setySize(buf);
    }


    //-----------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMustBeInFinal() {
        return mustBeInFinal;
    }

    public void setMustBeInFinal(boolean mustBeInFinal) {
        this.mustBeInFinal = mustBeInFinal;
    }


    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xEnd=this.xStart+xSize;
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.yEnd=this.yStart+ySize;
        this.ySize = ySize;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xEnd=xStart+this.xSize;
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yEnd=yStart+this.ySize;
        this.yStart = yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    @Override
    public String toString() {
        return "LowRectangle{" +
                "xSize=" + xSize +
                ", ySize=" + ySize +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                ", xEnd=" + xEnd +
                ", yEnd=" + yEnd +
                ", name='" + name + '\'' +
                ", mustBeInFinal=" + mustBeInFinal +
                '}';
    }
}

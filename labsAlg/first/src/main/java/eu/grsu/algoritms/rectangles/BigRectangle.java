package eu.grsu.algoritms.rectangles;

import eu.grsu.algoritms.api.IInsideRect;
import eu.grsu.algoritms.points.Point;
import javafx.animation.ScaleTransition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BigRectangle {//Starts from (0,0)
    public static final int X_START=0;
    public static final int Y_START=0;

    protected int xSize;


    protected int ySize;

    private List<Point> leftBorderPoints;// Заполнять сверху-вниз

    public BigRectangle(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        leftBorderPoints=new ArrayList<>(ySize+1+2);//+2 points in right

        leftBorderPoints.add(new Point(xSize,ySize));
        for(int i=ySize;i>=0;i--){
            leftBorderPoints.add(new Point(0,i));
        }
        leftBorderPoints.add(new Point(xSize,0));

    }

    public Point searchLeftLowPoint(){
        Point res=this.leftBorderPoints.get(0);
        Point currentPoint;
        for(int i=1;i<this.leftBorderPoints.size();i++){
            currentPoint=this.leftBorderPoints.get(i);
            if(res.getX()>=currentPoint.getX())
            {
                res=currentPoint;
            }
        }
        return res;
    }

    public boolean canConsistOf(List<LowRectangle> source) {//nextItem=0 in first iteration

        for (LowRectangle first : source)
        {
            Point startPoint=searchLeftLowPoint();
            first.setxStart(startPoint.getX());
            first.setyStart(startPoint.getY());

            if (givenIsInside(first)) {
                List<LowRectangle> newList = LowRectangle.cloneWithout(source,first);
                List<Point> currentPoints=this.leftBorderPoints;
                this.leftBorderPoints=movePointsToTheEndOfGiven(first);
                if (pointsAtTheEnd() || canConsistOf(newList)) {
                    first.setMustBeInFinal(true);
                    return true;
                }
                this.leftBorderPoints=currentPoints;
            }
            first.rotateRect();
            if (givenIsInside(first)) {
                List<LowRectangle> newList = LowRectangle.cloneWithout(source,first);
                List<Point> currentPoints=this.leftBorderPoints;
                this.leftBorderPoints=movePointsToTheEndOfGiven(first);
                if (pointsAtTheEnd() || canConsistOf(newList)){
                    first.setMustBeInFinal(true);
                    return true;
                }
                this.leftBorderPoints=currentPoints;
            }
//            System.out.println(first+"\n");
        }
        return false;
    }
    public List<Point> movePointsToTheEndOfGiven(LowRectangle given){
        List<Point> res=new ArrayList<>();
        for(Point i:this.leftBorderPoints){
            res.add(new Point(i.getX(),i.getY()));
        }
        Point endPoint=new Point(given.getxEnd(),given.getyEnd());
        Point startPoint=new Point(given.getxStart(),given.getyStart());

        Point startOfLine;
        Point endOfLine;
        for(int i=1;i<res.size();i++){
            startOfLine=res.get(i-1);
            endOfLine=res.get(i);

            if(startOfLine.getY()==given.getyEnd()){
                if(startOfLine.getY()!=endOfLine.getY() && startOfLine.getX()==endOfLine.getX()){
                    Point newEnd=new Point(given.getxEnd(),startOfLine.getY());
                    res.add(i,newEnd);
                    endOfLine=newEnd;
                }else
                    endOfLine.setX(given.getxEnd());
            }
            if(startOfLine.getY()<given.getyEnd() && startOfLine.getY()>given.getyStart() && endOfLine.getX()<=given.getxEnd() ){//startOfLine.getY()>=given.getyStart()
                endOfLine.setX(given.getxEnd());
            }
        }
        for(int i=0;i<res.size();i++){
            for(int j=i+1;j<res.size();j++){
                if(res.get(i).equals(res.get(j)))res.remove(j);
            }
        }
        return res;
    }

    public boolean pointsAtTheEnd(){
        for(Point buf:this.leftBorderPoints){
            if(buf.getX()!=this.xSize)return false;
        }
        return true;
    }

//    public boolean givenIsInsideRotateIfNeed(LowRectangle given){
//        if(givenIsInside(given)){
//            return true;
//        }
//        given.rotateRect();
//        return givenIsInside(given);
//    }
    public boolean givenIsInside(LowRectangle given){
        Point startPoint=new Point(given.getxStart(),given.getyStart());
        int endY=this.leftBorderPoints.get(0).getY();
        Point iterator;
        boolean meetStart=false;
        for(int i=this.leftBorderPoints.size()-1;i>=0;i--){
            iterator=this.leftBorderPoints.get(i);
            if(iterator.equals(startPoint))
            {   meetStart=true; }
            if(iterator.getX()>startPoint.getX() && meetStart){
                endY=iterator.getY();
                break;
            }

        }
        return this.getxSize()- given.getxStart()>= given.getxSize()&&
                endY-given.getyStart()>=given.getySize();

//        return this.X_START<=given.getxStart()&&
//                this.X_START+this.xSize>= given.getxEnd()&&
//                this.Y_START<=given.getyStart()&&
//                this.Y_START+this.ySize>= given.getyEnd();
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }


}

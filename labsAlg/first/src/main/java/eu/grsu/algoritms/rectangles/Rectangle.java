package eu.grsu.algoritms.rectangles;

import eu.grsu.algoritms.api.IInsideRect;
import eu.grsu.algoritms.api.IRectangle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

@Getter
@Setter
public class Rectangle implements IRectangle {
    protected int xSize;
    protected int ySize;
    protected int xStart;
    protected int yStart;

    public Rectangle(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Override
    public boolean canConsistOf(List<Rectangle> source, IInsideRect inside) {
        for(Rectangle rectangle:source) {
            if (inside.insideRect() && source.stream().count() > 0) {//first inside Rect
                canConsistOf(cloneWithout(source, 1), inside);

            } else {
                
                return false;
            }
        }
        return true;
    }

    public List<Rectangle> cloneWithout(List<Rectangle> source,int without){
        List<Rectangle> result=new ArrayList<>(source);
        result.remove(without);
        return result;
    }

    @Override
    public void rotateRect() {
        int buf;
        //swap x_size and y_size
        buf=this.xSize;
        this.xSize=this.ySize;
        this.ySize=buf;
    }

    @Override
    public boolean givenIsInside(Rectangle given) {
        return this.xStart<=given.xStart &&
                this.xSize>=given.xSize&&
                this.yStart<=given.yStart&&
                this.ySize>=given.ySize;
    }


    public int getXEnd(){return this.xStart+this.xSize;}
    public int getYEnd(){return this.yStart+this.ySize;}


}


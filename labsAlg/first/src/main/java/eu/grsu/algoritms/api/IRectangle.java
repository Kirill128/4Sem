package eu.grsu.algoritms.api;

import eu.grsu.algoritms.rectangles.Rectangle;

import java.util.List;

public interface IRectangle {

    boolean canConsistOf(List<Rectangle> source, IInsideRect insideRect);

    void rotateRect();

    boolean givenIsInside(Rectangle given);

}

package eu.grsu.algoritms.api;

import eu.grsu.algoritms.rectangles.LowRectangle;

import java.util.List;

public interface IRectangle {

    boolean canConsistOf(List<LowRectangle> source, IInsideRect insideRect);

    void rotateRect();

   // boolean givenIsInside(Rectangle given);

}

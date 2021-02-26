package eu.grsu.map.lines;

import eu.grsu.map.points.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Line {
    private Point maxPoint;
    private Point minPoint;

    public Point getMiddlePoint(){
        return null;
    }

    public double calculateX(double y){
        return 0;
    }

    public double calculateY(double x){
        return 0;
    }
}

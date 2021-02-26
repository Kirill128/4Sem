package eu.grsu.map.twits;

import com.sun.source.tree.CaseTree;
import eu.grsu.map.points.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Twit {
    private Point x;
    private Point y;
    private GregorianCalendar data;
    private List<String> text;
    private double positiveness;

    public Twit(Point x, Point y, GregorianCalendar data,String text) {
        this.x = x;
        this.y = y;
        this.data = data;
        this.text=doTextListFromString(text);
    }
    public static List<String> doTextListFromString(String text){
        return Arrays.asList(text.split(" " ));//TODO: ignore empty strings
    }

}



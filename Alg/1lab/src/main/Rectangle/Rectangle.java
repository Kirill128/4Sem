package main.Rectangle;


import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getS() {
        return this.X*this.Y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStart_x() {
        return start_x;
    }

    public void setStart_x(int start_x) {
        this.start_x = start_x;
    }

    public int getEnd_x() {
        return this.start_x+X;
    }

    public int getStart_y() {
        return start_y;
    }

    public void setStart_y(int start_y) {
        this.start_y = start_y;
    }

    public int getEnd_y() {
        return this.start_y+Y;
    }



    public int X ;                   // ширина прямоугольника
    public int Y ;                   // высота прямоугольника

    public int S ;       // площадь прямоугольника
    public int num;

    public int start_x;
    public int start_y;


    public Rectangle(int x, int y,int num)
    {
        X = x;
        Y = y;
        this.num=num;
    }
    public Rectangle(int x, int y,int num,int start_x,int start_y)
    {
        X = x;
        Y = y;
        this.num=num;
        this.start_x=start_x;
        this.start_y=start_y;
    }

    public boolean Contains(Rectangle r)            // функция проверяет вписывается ли r в этот прямоугольник
    {
        if ((r.X <= X) && (r.Y <= Y) ||     // проверяем ширину с шириной высоту с высотой
                (r.X <= Y) && (r.Y <= X))       // или ширину с высотой и высоту с шириной т.к. прямоугольники можно поворачивать
            return true;                    // возвращает true если да
        return false;                       // и false если нет
    }

    public List<Rectangle> Split(Rectangle r)         // функция убирает из прямоугольника r и возвращает получившиеся куски
    {
        List<Rectangle> R = new ArrayList<>();
        if ((r.X == X) && (r.Y < Y))        // если r по ширине такой же как и этот прямоугольник то нужно вернуть нижнюю часть
        {
            R.add(new Rectangle(r.X, Y - r.Y,r.getNum()));
            return R;
        }
        else if ((r.Y == X) && (r.X < Y))
        {
            R.add(new Rectangle(r.Y, Y - r.X,r.getNum()));
            return R;
        }

        else if ((r.X < X) && (r.Y == Y))   // если r по высоте совпадает, то вернуть правую часть
        {
            R.add(new Rectangle(X - r.X, r.Y,r.getNum()));
            return R;
        }
        else if ((r.Y < X) && (r.X == Y))
        {
            R.add(new Rectangle(X - r.Y, r.X,r.getNum()));
            return R;
        }

        else if ((r.X < X) && (r.Y < Y))    // если r меньше по всем осям, то получится две части
        {
            R.add(new Rectangle(X - r.X, Y,r.getNum()));
            R.add(new Rectangle(r.X, Y - r.Y,r.getNum()));
            return R;
        }
        else if ((r.Y < X) && (r.X < Y))
        {
            R.add(new Rectangle(X - r.Y, Y,r.getNum()));
            R.add(new Rectangle(r.Y, Y - r.X,r.getNum()));
            return R;
        }
        return null;                        // если прямоугольники совпали то вернём null
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "X=" + X +
                ", Y=" + Y +
                ", S=" + getS() +
                ", num=" + num +
                ", start_x=" + start_x +
                ", start_y=" + start_y +
                '}';
    }
}

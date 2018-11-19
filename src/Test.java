import java.util.ArrayList;
import java.util.Arrays;

public class Test
{
    public static void main (String[] args)
    {
        Coords point1 = new Coords(2.1, 3);
        Coords point2 = new Coords(2);
        //Coords point3 = new Coords();
        //Coords point4 = new Coords(point1); //point4==point1 is false because different memory addresses, point1.equal(point4) returns true since logic is true
        //Coords point5 = point1; //point1 and point5 are just nicknames, same memory address, so point1==point5 is true
        System.out.println(point2.equals(point1));
        System.out.println(point1.distanceFrom(point2)); //point1 (calling the method) is the this (implicit parameter), point2 is the other
        System.out.println(point1.distanceFromOrigin());

        Coords point3 = new Coords(5,7);
        Coords point4 = new Coords(15,18);
        Coords point5 = new Coords(6,20);
        Coords point6 = new Coords(2,4);
        Coords point7 = new Coords(1,5);
        Coords point8 = new Coords(1,2);
        Coords point9 = new Coords(1.5,2.5);
        Coords point10 = new Coords(18,5);



        LinearEquation negLine = new LinearEquation(2,1,-4);
        LinearEquation posLine = new LinearEquation(-2,1,-4);
        LinearEquation horizontalLine = new LinearEquation(0,1,-2);
        LinearEquation verticalLine = new LinearEquation(1,0,2);
        System.out.println(horizontalLine.getSlopeInterceptForm());
        System.out.println(posLine);

        ComplexNumber first = new ComplexNumber(-2,3);
        ComplexNumber second = new ComplexNumber(3,-4);
        //first.divideBy(second);
        System.out.println(first);

        QuadraticEquation one = new QuadraticEquation(1, 6, 9);
        QuadraticEquation two = new QuadraticEquation(1,-1,-12);
        QuadraticEquation three = new QuadraticEquation(1,-4,4);

        System.out.println(three);
        System.out.println(three.getRoots());

        QuadraticRoots theRoot = new QuadraticRoots(new ComplexNumber(5,3));
        System.out.println(theRoot.getQuadEquation());

        ArrayList<Coords> myPoints = new ArrayList<Coords>();
        myPoints.addAll(Arrays.asList(point1, point2, point3, point4, point5, point6, point7, point8, point9, point10));

        System.out.println(Coords.closestPointToOrigin(myPoints)); //static method must call off Coords
        System.out.println(point1.closestPoint(myPoints));


    }
}

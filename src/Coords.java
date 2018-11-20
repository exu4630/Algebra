import java.util.ArrayList;

public class Coords
{
    private double x; //has value of 0 because it's an instance variable
    private double y; //has value of 0 because it's an instance variable

    /**
     *
     * @param x the x coordinate as a double
     * @param y the y coordinate as a double
     */
    public Coords(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * x and y to the value of parameter
     * @param value a passed in parameter
     */
    public Coords(double value)
    {
        /*
        x = value;
        y = value;
        */
        this(value, value); //finds another Coords constructor that matches its parameters
    }

    /**
     * set coordinates to the origin
     */
    public Coords()
    {
        /*
        x = 0;
        y = 0;
        */
        this(0);
    }

    /**
     * sets coordinates to coords of the other point
     * @param other the other point
     */
    public Coords(Coords other)
    {
        /*
        this.x = other.x;
        this.y = other.y;
        */
        this(other.x, other.y);
    }

    //accessors
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public String toString() //when you print out a coords, this is what is returned
    {
        return "(" + x + "," + y + ")";
    }
    public int compareTo(Coords other)
    {
        if (this.x == other.x)
        {
            if(this.y < other.y)
                return -1;
            if(this.y > other.y)
                return 1;
            return 0;
        }
        if (this.x < other.x)
            return -1;

        return 1;

    }

    public static void sortCoords(Coords[] points)
    {
        for(int i = 0; i < points.length-1;i++)
        {
            int lowPosition = i;
            for (int j = i+1; j < points.length; j++)
            {
                if(points[j].compareTo(points[lowPosition]) < 0)
                {
                    lowPosition = j;
                }
            }
            swap(i, lowPosition, points);
        }
    }
    private static void swap(int indexOne, int indexTwo, Coords[] points)
    {
        Coords temp = points[indexOne];
        points[indexOne] = points[indexTwo];
        points[indexTwo] = temp;
    }
    public static void printArray(Coords[] points)
    {
        for(Coords c : points)
            System.out.println(c + " ");
    }


    //mutators
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void reflectOnXAxis()
    {
        y = -y;
    }
    public void reflectOnYAxis()
    {
        x = -x;
    }
    public void reflectOnOrigin()
    {
        x = 0;
        y = 0;
    }
    public void translate(double horizontal, double vertical)
    {
        x = x + horizontal;
        y = y + vertical;
    }
    public void dilate(double dilationFactor)
    {
        x = x * dilationFactor;
        y = y * dilationFactor;
    }

    //other methods
    public double distanceFromOrigin()
    {
        return Math.sqrt(x*x + y*y);
        // return distanceFrom(new Coords()); //passing a Coords with 0,0
    }
    public double distanceFrom(Coords other)
    {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        double deltaXSquared = deltaX * deltaX;
        double deltaYSquared = deltaY * deltaY;
        double deltaSquaredSum = deltaXSquared + deltaYSquared;

        return Math.sqrt(deltaSquaredSum);
    }

    public boolean equals(Coords other) //when two coords are logically the same (not memory address)
    {
        return this.x == other.x && this.y == other.y;
    }

    public double getSlopeOfLineSegment(Coords other)
    {
        return ((this.y - other.y) / (this.x - other.x));
    }
    public boolean slopeOfLineSegmentDefined(Coords other)
    {
        return this.x != other.x;
    }

    public static Coords closestPointToOrigin(ArrayList<Coords> points) //static because everything that is contained is inside the parameter - array list, not connected to this object (ex: closest point to this object is NOT static) -- Coords.closestPointToOrigin(myPoints);
    {
        Coords closestPointToOrigin = points.get(0);
        for(Coords p : points)
            if(p.distanceFromOrigin() < closestPointToOrigin.distanceFromOrigin())
                closestPointToOrigin = p;

        return closestPointToOrigin;

    }

    public Coords closestPoint(ArrayList<Coords> points) //this is not static because it relies on an object that can change, not static! -- someCoordsObject.closestPoint(myPoints);
    {
        Coords closestPoint = points.get(0);
        for(Coords p : points)
            if(this.distanceFrom(p) < this.distanceFrom(closestPoint)) //implied this if you don't write this
                closestPoint = p;

        return closestPoint;
    }
}


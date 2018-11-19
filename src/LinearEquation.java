public class LinearEquation
{
    private double a;
    private double b;
    private double c;

    /**
     *
     * @param a is the coefficient of x in ax+by+c = 0
     * @param b is the coefficient of y in ax+by+c = 0
     * @return returns true if given parameters are valid, meaning a and b are not both 0
     */
    public boolean areValidParams(double a, double b)
    {
        if(a == 0 && b == 0)
            return false;
        else
            return true;
    }

    /**
     * Creates a linear equation of ax + by + c = 0 given params a, b, c
     * @param a is the coefficient of x in ax+by+c = 0
     * @param b is the coefficient of y in ax+by+c = 0
     * @param c is the constant in ax+by+c = 0
     */
    public LinearEquation(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a linear equation with a given slope and point (point-slope form to ax + by + c = 0)
     * @param slope client provides a slope
     * @param point client provides a point
     */
    public LinearEquation(double slope, Coords point)
    {
        /*
        this.a = -slope;
        this.b = 1;
        this.c = (slope * point.getX()) - (point.getY());
        */
        this(-slope, 1, (slope * point.getX()) - (point.getY()));
    }

    /**
     * copy constructor
     * @param other passing through another LinearEquation object (ax + by + c = 0)
     */
    public LinearEquation(LinearEquation other)
    {
        /*
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
        */
        this(other.a, other.b, other.c);
    }

    /**
     * Creates a linear equation given two points, with the precondition that point1 is not equal to point2
     * @param point1 Coords object called point1
     * @param point2 Coords object called point2
     */
    public LinearEquation(Coords point1, Coords point2)
    {
        if(!point1.equals(point2))
        {
            if (point1.getX() == point2.getX()) //horizontal line
            {
                a = 1;
                b = 0;
                c = -point1.getX();
            } else {
                double slope = point1.getSlopeOfLineSegment(point2);
                this.a = -slope;
                this.b = 1;
                this.c = slope * point1.getX() - point1.getY();
            }
        }
    }

    /**
     * Creates a line passing through the point and is parallel to other
     * @param other other linear equation
     * @param point a given point
     */
    public LinearEquation(LinearEquation other, Coords point)
    {
        //if vertical!!!
        if(other.isVertical())
        {
            a = 1;
            b = 0;
            c = -1 * point.getX();

        }

        else
        {
            double slope = other.slope();
            this.a = -slope;
            this.b = 1;
            this.c = point.getY() - (-point.getX() * slope);
        }

    }

    //accessors
    public double getA()
    {
        return a;
    }
    public double getB()
    {
        return b;
    }
    public double getC()
    {
        return c;
    }

    public boolean slopeDefined()
    {
        return b != 0;
    }
    public boolean hasYIntercept()
    {
        if(this.isVertical())
            return false;

        return yIntercept() != 0;
    }
    public boolean hasXIntercept()
    {
         if(this.isHorizontal())
            return false;

        return xIntercept() != 0;
    }
    public double slope()
    {
        return -a/b;
    }
    public double yIntercept()
    {
        return -c/b;

    }
    public double xIntercept()
    {
        return -c/a;
    }
    public double slopeOfPerpendicularLine()
    {
        return -1/slope();
    }
    public boolean isIncreasing()
    {
        return this.slope() > 0;
    }
    public boolean isDecreasing()
    {
        return this.slope() < 0;
    }
    public boolean isHorizontal()
    {
        return this.slope() == 0;
    }
    public  boolean isVertical()
    {
        return !slopeDefined();
    }
    public boolean isAFunction()
    {
        return slopeDefined();
    }
    public boolean isValidLinearEquation()
    {
        return areValidParams(a, b);
    }

    /**
     *
     * @return true if crosses through 0,0
     */
    public boolean isDirectVariation()
    {
        return yIntercept() == 0 && xIntercept() == 0;
    }


    //other methods
    public boolean equals(LinearEquation other)
    {
        return ((this.slope() == other.slope()) && (this.yIntercept() == other.yIntercept()));

    }

    /**
     *
     * @param point a Coords object (point)  passed in as a parameter
     * @return a linear equation that passes through point and is parallel to this line
     */
    public LinearEquation parallelLine(Coords point)
    {
        if(!this.isValidLinearEquation())
            return null;

        if(this.isHorizontal())
        {
            return new LinearEquation(0,1,-1 * point.getY());
        }

        if(this.isVertical())
        {
            return new LinearEquation(1,0,-1 * point.getX());
        }
        return new LinearEquation(this.slope(), point);

    }

    /**
     *
     * @param point a Coords object (point)  passed in as a parameter
     * @return linear equation that passes through point and is perpendicular to this line
     */
    public LinearEquation perpendicularLine(Coords point)
    {
        //check horizontal and vertical lines and not valid equation
        if(!this.isValidLinearEquation())
            return null;

        if(this.isHorizontal())
            return new LinearEquation(1,0,-1 * point.getX());

        return new LinearEquation(this.slopeOfPerpendicularLine(), point);
    }

    /**
     *
     * @param point a Coords object (point)  passed in as a parameter
     * @return returns shortest distance from point to this line
     */
    public double shortestDistanceFrom(Coords point)
    {
        /*
        LinearEquation perp = perpendicularLine(point);
        Coords ptOfIntersection = pointOfIntersection(perp);
        double distance = point.distanceFrom(ptOfIntersection);

        return distance;
        */

        return point.distanceFrom(pointOfIntersection(perpendicularLine(point))); //kinda like f(g(h(x)))

    }
    public LinearEquation inverseFunction()
    {
        return new LinearEquation(this.b, this.a, this.c);
    }
    public Coords pointOfIntersection(LinearEquation other)
    {
        if(this.isVertical() && other.isHorizontal())
        {
            return new Coords(this.xIntercept(), other.yIntercept());
        }
        if(other.isVertical() && this.isHorizontal())
        {
            return new Coords(other.xIntercept(), this.yIntercept());
        }
        if(!this.isValidLinearEquation() || !other.isValidLinearEquation())
        {
            return null;
        }
        if(this.isVertical() && other.isVertical())
        {
            return null;
        }
        if(this.slope() == other.slope())
        {
            return null;
        }

        double d = other.getA();
        double e = other.getB();
        double f = other.getA();

        double y = (a*f - c*d)/(b*d - a*e);
        double x = -(b*y + c)/a;

        return new Coords(x, y);
    }
    public boolean isParallel(LinearEquation other)
    {
        return this.slope() == other.slope();
    }
    public boolean isPerpendicular(LinearEquation other)
    {
        if(this.isHorizontal() && other.isVertical())
            return true;
        if(other.isHorizontal() && this.isVertical())
            return true;
        if(this.slope() == other.slopeOfPerpendicularLine())
        {
            return true;
        }
        else
            return false;
    }
    public String toString()
    {
        if(b<0 && c<0)
            return a + "x" + b + "y" + c + "=0";
        if(b<0)
            return a + "x" + b + "y" + "+" + c + "=0";
        if(c<0)
            return a + "x" + "+" + b + "y" + c + "=0";

        return a + "x" + "+" + b + "y" + "+" + c + "=0";
    }
    public String getSlopeInterceptForm()
    {
        if(this.yIntercept()<0 && this.slope() == 0)
            return "y="+ this.yIntercept();
        if(this.yIntercept() < 0)
            return "y=" + this.slope() + "x" + this.yIntercept();
        if(this.slope() == 0)
            return "y=" + this.yIntercept();

        return "y=" + this.slope() + "x" + "+" + this.yIntercept();
    }

}

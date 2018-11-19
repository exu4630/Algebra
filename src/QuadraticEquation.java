public class QuadraticEquation
{
    //y = ax^2 + bx + c
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation(QuadraticEquation other)
    {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    public QuadraticEquation()
    {
        this.a = 1;
        this.b = 0;
        this.c = 0;
    }

    public QuadraticEquation(double coefficient, Coords vertex)
    {
        this.a = coefficient;
        this.b = coefficient * -2 * vertex.getX();
        this.c = coefficient * (vertex.getX() * vertex.getX()) + vertex.getY();
    }

    public String toString()
    {
        if(this.a==1 &&this.b<0&&this.c<0)
            return "y=" + "x^2" + this.b + "x" + this.c;
        if(this.a == 1 && this.b < 0)
            return "y=" + "x^2" + this.b + "x" + "+" + this.c;
        if(this.a == 1 && this.c < 0)
            return "y=" + "x^2" + "+" + this.b + "x" + this.c;
        if(this.b < 0)
            return "y=" + this.a + "x^2" + this.b + "x" + "+" + this.c;
        if(this.c < 0)
            return "y=" + this.a + "x^2" + this.b + "x" + this.c;

        return "y=" + this.a + "x^2" + "+" + this.b + "x" + "+" + this.c;

    }

    public double getDiscriminant()
    {
        return (b*b) + (-4*a*c);
    }

    public boolean hasRealRoots()
    {
        return getDiscriminant() >= 0;
    }

    public boolean hasEqualRoots()
    {
        return getDiscriminant() == 0;
    }

    public boolean hasMinimum()
    {
        return a > 0;
    }

    public boolean hasMaximum()
    {
        return a < 0;
    }

    public LinearEquation axisOfSymmetry()
    {

        return new LinearEquation(1, 0, b/2*a);
    }

    public Coords vertex()
    {

        double xCoord = -1 * axisOfSymmetry().getC();
        return new Coords(xCoord, ((a * xCoord * xCoord) + (b * xCoord) + c));
    }

    public LinearEquation derivative()
    {
        return new LinearEquation(2*a, -1, b);
    }

    public QuadraticRoots getRoots()
    {
        double sqrtOfDiscriminant = Math.sqrt(getDiscriminant());
        double absValOfDiscriminant = Math.abs(getDiscriminant());
        if(hasRealRoots() == true)
        {
            double root1 = (-b + sqrtOfDiscriminant) / (2 * a);
            double root2 = (-b - sqrtOfDiscriminant) / (2 * a);
            return new QuadraticRoots(root1, root2);
        }
        else
        {
            ComplexNumber root = new ComplexNumber((-b)/(2*a), (Math.sqrt(absValOfDiscriminant))/(2*a));
            return new QuadraticRoots(root);
        }
    }

}

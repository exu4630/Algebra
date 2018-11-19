public class ComplexNumber
{
    //a+bi
    private double a;
    private double b;


    public ComplexNumber(double realPart, double imaginaryPart)
    {
        a = realPart;
        b = imaginaryPart;
    }
    public ComplexNumber(ComplexNumber other)
    {
        this.a = other.a;
        this.b = other.b;
    }

    public double getRealMagnitude()
    {
        return this.a;
    }

    public double getImaginaryMagnitude()
    {
        return this.b;
    }


    public String toString()
    {
        if(this.a == 0 && this.b == 0)
            return "0";
        if(this.a==0)
            return this.b + "i";
        if(this.b==0)
            return this.a + "";
        if(this.b < 0)
            return this.a + "" + this.b + "i";

        return this.a + "+" + this.b + "i";
    }
    public void add(double scalar)
    {
        a = a + scalar;
    }
    public void add(ComplexNumber other)
    {
        this.a += other.a;
        this.b += other.b;
    }
    public void subtract(double scalar)
    {
        this.a = this.a - scalar;
    }
    public void subtract(ComplexNumber other)
    {
        this.a -= other.a;
        this.b -= other.b;
    }
    public void multiply(double scalar)
    {
        this.a *= scalar;
        this.b *= scalar;
    }
    public void multiply(ComplexNumber other)
    {
        double a = this.a;
        double b = this.b;
        double c = other.a;
        double d = other.b;

        //foiling
        double first = a * c;
        double second = a * d;
        double third = b * c;
        double fourth = -1 * b * d;

        this.a = first + fourth;
        this.b = (second + third);

    }
    public boolean divideBy(double scalar)
    {
        if(scalar != 0)
        {
            this.a /= scalar;
            this.b /= scalar;
            return true;
        }

        return false;
    }
    public boolean divideBy(ComplexNumber other)
    {
        if(other.a != 0 && other.b != 0)
        {
            double a = this.a;
            double b = this.b;
            double c = other.a;
            double d = other.b;

            double first = a * c;
            double second = b * d;
            double third = b * c;
            double fourth = a * -d;
            double denominator = (c * c) + (d * d);

            this.a = (first + second) / (denominator);
            this.b = (third + fourth) / (denominator);

            return true;
        }
        return false;
    }
}







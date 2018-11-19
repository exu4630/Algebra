public class QuadraticRoots
{
    ComplexNumber root1;
    ComplexNumber root2;

    public QuadraticRoots(ComplexNumber r1)
    {
        root1 = r1;
        root2 = new ComplexNumber(r1.getRealMagnitude(), -1 * r1.getImaginaryMagnitude());

    }

    public QuadraticRoots(double r1, double r2)
    {
        root1 = new ComplexNumber(r1, 0);
        root2 = new ComplexNumber(r2, 0);
    }

    public ComplexNumber getRoot1()
    {
        return root1;
    }

    public  ComplexNumber getRoot2()
    {
        return  root2;
    }

    public String toString()
    {
        return "(" + root1.toString() + ", " + root2.toString() + ")";
    }

    public QuadraticEquation getQuadEquation()
    {
        double sumOfRoots = 0;
        double productOfRoots = 0;

        if(root1.getImaginaryMagnitude() == 0)
        {
            productOfRoots = root1.getRealMagnitude() * root2.getRealMagnitude();
            sumOfRoots = root1.getRealMagnitude() + root2.getRealMagnitude();
            return new QuadraticEquation(1,-sumOfRoots,productOfRoots);
        }
        else
        {
            double firstPart = root1.getRealMagnitude() + root1.getRealMagnitude();
            double secondPart = (root1.getRealMagnitude() * root2.getRealMagnitude()) + (root1.getImaginaryMagnitude() * root1.getImaginaryMagnitude());
            return new QuadraticEquation(1, -firstPart,secondPart);
        }
        //sum and product of roots
    }



    public boolean areValid()
    {
        //valid if roots are conjugates or real
        return (root1.getRealMagnitude() == root2.getRealMagnitude() && -root1.getImaginaryMagnitude() == root2.getImaginaryMagnitude()) || (root1.getImaginaryMagnitude() == 0 && root2.getImaginaryMagnitude() == 0);
    }



}

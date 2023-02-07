import java.util.ArrayList;
import java.util.Scanner;

public class BigRectLister
{
    public static void main(String[] args)
    {
        ArrayList<Object> rectangles = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        BigRectangleFilter bigRect = new BigRectangleFilter();

        Object rectInput;

        System.out.println("Enter the perimeter of 10 different rectangles");

        for(int i = 0; i < 10; i++)
        {
            System.out.println("Rectangle number " + (i + 1));
            rectInput = SafeInput.getInt(in, "Enter the perimeter");
            rectangles.add(rectInput);
        }
        ArrayList<Object> passedRects;
        passedRects = collectAll(rectangles, bigRect);

        System.out.println("\nEntered perimeters: ");
        for(int i = 0; i < rectangles.size(); i++)
        {
            System.out.println((i + 1) + ": " + rectangles.get(i));
        }

        System.out.println("\nPassed perimeters: ");
        for (Object passedRect : passedRects)
        {
            System.out.println(passedRect);
        }
    }

    private static ArrayList<Object> collectAll(ArrayList<Object> objects, Filter filter)
    {
        ArrayList<Object> passed = new ArrayList<>();
        for (Object object : objects)
        {
            if (filter.accept(object))
            {
                passed.add(object);
            }
        }
        return passed;
    }

}
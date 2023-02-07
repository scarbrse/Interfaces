public class BigRectangleFilter implements Filter
{

    @Override
    public boolean accept(Object x) {
        int perimeter  = (int) x;
        return (perimeter > 10);
    }
}
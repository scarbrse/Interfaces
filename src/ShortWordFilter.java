public class ShortWordFilter implements Filter{
    @Override
    public boolean accept(Object x) {
        if (x instanceof String) {
            String str = (String) x;
            return str.length() < 5;
        }
        return false;
    }
}
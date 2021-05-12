package prob0719;

public class THexadecimal extends AToken{
    private final int hexValue;
    public THexadecimal(int i) {
        hexValue = i;
    }
    public int getIntValue() {
        return hexValue;
    }
}

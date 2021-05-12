package prob0719;

public class TDot extends AToken{
    private final String dotValue;
    public TDot(StringBuffer stringBuffer) {
        dotValue = new String(stringBuffer);
    }
    public String getStringValue() {
        return dotValue;
    }
}

package prob0719;

public class TAddress extends AToken{
    private final String addressValue;
    public TAddress(StringBuffer stringBuffer) {
        addressValue = new String(stringBuffer);
    }
    public String getAddressValue() {
        return addressValue;
    }
}

package prob0719;

public class HexArg extends AArg{
    private final int hexValue;
    public HexArg(int i) {
        hexValue = i;
    }
    @Override
    public String generateCode() {
        return String.format("0X%04X", hexValue & 0xFFFF);
    }
    public int getIntValue(){
        return hexValue;
    }
}

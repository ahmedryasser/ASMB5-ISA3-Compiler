package prob0719;

public class AddrArg extends AArg{
    private final String addrValue;
    public AddrArg(String str) {
        addrValue = str;
    }
    @Override
    public String generateCode() {
        return addrValue;
    }

    @Override
    public int getIntValue() {
        if (addrValue.equals("i")){
            return 0;
        }
        else if (addrValue.equals("d")){
            return 1;
        }
        else if (addrValue.equals("n")){
            return 2;
        }
        else if (addrValue.equals("s")){
            return 3;
        }
        else if (addrValue.equals("sf")){
            return 4;
        }
        else if (addrValue.equals("x")){
            return 5;
        }
        else if (addrValue.equals("sx")){
            return 6;
        }
        else if (addrValue.equals("sfx")){
            return 7;
        }
        else{
            return 0;
        }
    }
}

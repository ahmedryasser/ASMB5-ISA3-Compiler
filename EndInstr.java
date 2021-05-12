package prob0719;

public class EndInstr extends ACode{
    private final DotCmnd Dot;
    public EndInstr(DotCmnd mn) {
        Dot = mn;

    }
    @Override

    public String generateListing() {
        return String.format("%s \n", Maps.endStringTable.get(Dot));
    }
    public String generateCode() {
        return "";
    }
}

package prob0719;

public class BlockInstr extends ACode{
    private final DotCmnd Dot;
    private final AArg operand;
    public BlockInstr(DotCmnd Cmnd, AArg fArg) {
        Dot = Cmnd;
        operand = fArg;

    }
    public String generateListing() {
        return String.format("%-8s%-8s\n", Maps.blockStringTable.get(Dot), operand.generateCode());
    }
    public String generateCode() {
        int n = operand.getIntValue();
        String str = "";
        for( int i = 0; i<n; i++){
            str += "00 ";
        }
        return str+ "\n";
    }
}

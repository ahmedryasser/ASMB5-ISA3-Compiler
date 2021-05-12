package prob0719;


import java.util.ArrayList;

public class Translator {
    private final InBuffer b;
    private Tokenizer t;
    private ACode aCode;

    public Translator(InBuffer inBuffer) {
        b = inBuffer;
    }

    // Sets aCode and returns boolean true if end statement is processed.
    private boolean parseLine() {
        boolean terminate = false;
        AArg localOperand = new DecArg(0);
        AddrArg localAddressMode = new AddrArg(" ");
        // Compiler requires following useless initialization.
        Mnemon localMnemon = Mnemon.M_STOP;
        DotCmnd localDotCmnd = DotCmnd.D_END;
        AToken aToken;
        aCode = new EmptyInstr();
        ParseState state = ParseState.PS_START;
        do {
            aToken = t.getToken();
            switch (state) {
                case PS_START:
                    if (aToken instanceof TIdentifier) {
                        TIdentifier localTIdentifier = (TIdentifier) aToken;
                        String tempStr = localTIdentifier.getStringValue().toUpperCase();
                        if (Maps.unaryMnemonTable.containsKey(tempStr)) {
                            localMnemon = Maps.unaryMnemonTable.get(tempStr);
                            aCode = new UnaryInstr(localMnemon);
                            state = ParseState.PS_END;
                        }
                        else if ( Maps.nonUnaryBrMnemonTable.containsKey(tempStr) ) {

                            localMnemon = Maps.nonUnaryBrMnemonTable.get(tempStr);
                            state = ParseState.PS_BRANCH;
                        }
                        else if (Maps.nonUnaryMnemonTable.containsKey(tempStr)) {

                            localMnemon = Maps.nonUnaryMnemonTable.get(tempStr);
                            state = ParseState.PS_NONUNARY;
                        }
                        else {
                            aCode = new Error("Line must begin with a valid identifier.");
                        }

                    }
                    else if (aToken instanceof TDot) {
                        TDot localTDot = (TDot) aToken;
                        String tempStr = localTDot.getStringValue().toUpperCase();

                        if (Maps.blockTable.containsKey(tempStr)) {
                            localDotCmnd = Maps.blockTable.get(tempStr);
                            state = ParseState.PS_BLOCK;
                        }
                        else if (Maps.endTable.containsKey(tempStr)) {
                            localDotCmnd = Maps.endTable.get(tempStr);
                            aCode = new EndInstr(localDotCmnd);
                            state = ParseState.PS_END;
                            terminate = localDotCmnd == DotCmnd.D_END;
                        }
                        else {
                            aCode = new Error("Invalid dot command.");
                        }
                    }

                    else if (aToken instanceof TEmpty) {
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    }
                    else {
                        aCode = new Error("Line must begin with instruction identifier.");
                    }
                    break;
                case PS_BLOCK:
                    if (aToken instanceof TInteger ) {
                        TInteger localTInteger = (TInteger) aToken;
                        localOperand= new DecArg(localTInteger.getIntValue());
                        aCode = new BlockInstr(localDotCmnd, localOperand);
                        state = ParseState.PS_END;
                    }
                    else if (aToken instanceof THexadecimal){
                        THexadecimal localTHexadecimal = (THexadecimal) aToken;
                        localOperand = new HexArg(localTHexadecimal.getIntValue());
                        aCode = new BlockInstr(localDotCmnd, localOperand);

                        state = ParseState.PS_END;
                    }
                    else {
                        aCode = new Error("Decimal or Hex value expected after instruction.");
                    }
                    break;
                case PS_BRANCH:
                    if (aToken instanceof THexadecimal) {
                        THexadecimal localTHexadecimal = (THexadecimal) aToken;
                        localOperand = new HexArg(localTHexadecimal.getIntValue());
                        state = ParseState.PS_BROPERANDSPEC;
                    }
                    else if (aToken instanceof TInteger) {
                        TInteger localTInteger = (TInteger) aToken;
                        localOperand= new DecArg(localTInteger.getIntValue());
                        state = ParseState.PS_BROPERANDSPEC;
                    }else {
                        aCode = new Error("First argument not an operand specifier.");
                    }
                    break;
                case PS_NONUNARY:
                    if (aToken instanceof THexadecimal) {
                        THexadecimal localTHexadecimal = (THexadecimal) aToken;
                        localOperand = new HexArg(localTHexadecimal.getIntValue());
                        state = ParseState.PS_OPERANDSPEC;
                    }
                    else if (aToken instanceof TInteger) {
                        TInteger localTInteger = (TInteger) aToken;
                        localOperand= new DecArg(localTInteger.getIntValue());
                        state = ParseState.PS_OPERANDSPEC;
                    }else {
                        aCode = new Error("First argument not an operand specifier.");
                    }
                    break;
                case PS_BROPERANDSPEC:
                    if (aToken instanceof TAddress ) {
                        TAddress localAddress = (TAddress) aToken;
                        String addrStr = localAddress.getAddressValue().toLowerCase();
                        localAddressMode = new AddrArg(addrStr);
                        state = ParseState.PS_ADRRMODE;
                        if (Maps.addrBrTable.containsKey(addrStr)){
                            aCode = new NonUnaryInstr(localMnemon, localOperand, localAddressMode);
                            state = ParseState.PS_END;
                        }
                        else{
                            aCode = new Error("Invalid addressing mode.");

                        }
                    }
                    else if(aToken instanceof  TEmpty){
                        localAddressMode = new AddrArg(" ");
                        aCode = new NonUnaryInstr(localMnemon, localOperand, localAddressMode);
                        state = ParseState.PS_FINISH;
                    }
                    else{
                        aCode = new Error("Illegal trailing charachter");
                    }
                    break;
                case PS_OPERANDSPEC:
                    if (aToken instanceof TAddress ) {
                        TAddress localAddress = (TAddress) aToken;
                        String addrStr =localAddress.getAddressValue().toLowerCase();
                        localAddressMode = new AddrArg(addrStr);
                        state = ParseState.PS_ADRRMODE;
                        if ( ((localMnemon == Mnemon.M_DECI) || (localMnemon == Mnemon.M_STWA)) && !Maps.addrITable.containsKey(addrStr)){
                            aCode = new Error("Invalid addressing mode.");
                        }
                        else if (Maps.addrTable.containsKey(localAddress.getAddressValue().toLowerCase())){
                            aCode = new NonUnaryInstr(localMnemon, localOperand, localAddressMode);
                            state = ParseState.PS_END;
                        }
                        else{
                            aCode = new Error("Invalid addressing mode.");
                        }
                    }

                    else {
                        aCode = new Error("Addressing mode expected after first argument.");
                    }
                    break;

                case PS_END:
                    if (aToken instanceof TEmpty){
                        state = ParseState.PS_FINISH;
                    }
                    else{
                        aCode = new Error("Illegal trailing character.");
                    }
            }
        } while (state != ParseState.PS_FINISH && !(aCode instanceof Error));
        return terminate;
    }
    public void translate() {
        ArrayList<ACode> codeTable = new ArrayList<>();
        int numErrors = 0;
        t = new Tokenizer(b);
        boolean terminateWithEnd = false;
        b.getLine();
        while (b.inputRemains() && !terminateWithEnd) {
            terminateWithEnd = parseLine(); // Sets aCode and returns boolean.
            codeTable.add(aCode);
            if (aCode instanceof Error) {
                numErrors++;
            }
            b.getLine();
        }
        if (!terminateWithEnd) {
            aCode = new Error("Missing \"end\" sentinel.");
            codeTable.add(aCode);
            numErrors++;
        }
        if (numErrors == 0) {
            System.out.printf("Object code:\n");
            for (int i = 0; i < codeTable.size(); i++) {
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        }
        if (numErrors == 1) {
            System.out.printf("One error was detected.\n");
        } else if (numErrors > 1) {
            System.out.printf("%d errors were detected.\n", numErrors);
        }
        System.out.printf("\nProgram listing:\n");
        for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateListing());
        }
    }
}

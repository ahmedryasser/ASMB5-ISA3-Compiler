package prob0719;

public class Tokenizer {
    private final InBuffer b;
    public Tokenizer(InBuffer inBuffer) {
        b = inBuffer;
    }
    public int HexVal(char ch){
        if (ch >= '0' && ch <= '9'){
            return ch-'0';
        }
        else if(ch >= 'a' && ch <= 'f' ){
            return ch - 'a' + 10;
        }
        else{
            return ch - 'A' + 10;
        }
    }
    public AToken getToken() {
        char nextChar;
        StringBuffer localStringValue = new StringBuffer("");
        int localIntValue = 0;
        int sign = +1;
        AToken aToken = new TEmpty();
        LexState state = LexState.LS_START;
        int limit = 65535;
        do {
            nextChar = b.advanceInput();
            switch (state) {
                case LS_START:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    } else if (nextChar == '-') {
                        limit = 32768;
                        sign = -1;
                        state = LexState.LS_SIGN;
                    } else if (nextChar == '+') {
                        limit = 32768;
                        sign = +1;
                        state = LexState.LS_SIGN;
                    } else if (nextChar == '0'){
                        state = LexState.LS_INT1;
                    }
                    else if (Util.isDigit(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2;
                    } else if (nextChar == '\n') {
                        state = LexState.LS_STOP;
                    }
                    else if (nextChar == '.') {
                        state = LexState.LS_DOT1;
                    }
                    else if (nextChar == ',') {
                        state = LexState.LS_ADDR1;
                    }
                    else if (nextChar != ' ') {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_IDENT:
                    if (Util.isAlpha(nextChar) || Util.isDigit(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_SIGN:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2;
                    }
                    else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_INT1:
                    if (nextChar == 'X' || nextChar == 'x'){
                        state = LexState.LS_HEX1;
                    }
                    else if(Util.isDigit(nextChar)){
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2;
                    }
                    else {
                        b.backUpInput();
                        aToken = new TInteger( localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_INT2:
                    if (localIntValue > limit ){
                        aToken = new TInvalid();
                    }
                    else if (Util.isDigit(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;

                case LS_HEX1:
                    if (Util.isHexDigit(nextChar)){
                        localIntValue = HexVal(nextChar);
                        state = LexState.LS_HEX2;
                    }
                    else{
                        aToken = new TInvalid();
                    }
                    break;
                case LS_HEX2:
                    if (localIntValue > 65535){
                        aToken = new TInvalid();
                    }
                    else if (Util.isHexDigit(nextChar)){
                        localIntValue = 16 * localIntValue + HexVal(nextChar);
                    }
                    else{
                        b.backUpInput();
                        aToken = new THexadecimal(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }

                    break;
                case LS_DOT1:
                    if (Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    }
                    else{
                        aToken = new TInvalid();
                    }

                    break;
                case LS_DOT2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TDot(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_ADDR1:
                    if (Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2;
                    }
                    else if(nextChar == ' '){

                    }
                    else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_ADDR2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TAddress(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
            }
        } while ((state != LexState.LS_STOP) && !(aToken instanceof TInvalid));
        return aToken;
    }
}

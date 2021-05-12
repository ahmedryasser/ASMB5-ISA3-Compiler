package prob0719;

public class NonUnaryInstr extends ACode {
        private final Mnemon mnemonic;
        private final AArg operand;
        private final AddrArg address;
        public NonUnaryInstr(Mnemon mn, AArg fArg, AddrArg sArg) {
            mnemonic = mn;
            operand = fArg;
            address = sArg;
        }
        @Override
        public String generateListing() {

            return String.format("%-8s%s,%s \n", Maps.mnemonStringTable.get(mnemonic), operand.generateCode(), address.generateCode());


        }
        @Override
        public String generateCode() {
            int identVal = 0;
            int addr = 0;
            int opVal = 0;
            int h1 = 0;
            int h2 = 0;
            switch (mnemonic) {
                case M_LDWA:
                    identVal = 192;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_STWA:
                    identVal = 224;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_BR:
                    identVal = 18;
                    addr = address.getIntValue() == 0?0:1;
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_BRLE:
                    identVal = 20;
                    addr = address.getIntValue() == 0?0:1;
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);

                case M_BRLT:
                    identVal = 22;
                    addr = address.getIntValue() == 0?0:1;
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);

                case M_BREQ:
                    identVal = 24;
                    addr = address.getIntValue() == 0?0:1;
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_ADDA:
                    identVal = 96;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_SUBA:
                    identVal = 112;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_CPWA:
                    identVal= 160;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                case M_DECI:
                    identVal = 48;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);

                case M_DECO:
                    identVal= 56;
                    addr = address.getIntValue();
                    identVal += addr;
                    opVal = operand.getIntValue();
                    opVal = opVal >= 0?opVal:opVal+65536;
                    h1 = opVal/256;
                    h2 = opVal%256;
                    return String.format("%02X %02X %02X\n",identVal, h1, h2);
                default:
                    return ""; // Should not occur.
            }
        }
}

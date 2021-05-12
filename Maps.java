package prob0719;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Maps {

        public static final Map<String, Mnemon> unaryMnemonTable;
        public static final Map<String, Mnemon> nonUnaryMnemonTable;
        public static final Map<String, Mnemon> nonUnaryBrMnemonTable;

        public static final Map<String, DotCmnd> endTable;
        public static final Map<String, DotCmnd> blockTable;

        public static final Map<String, AddrMode> addrTable;
        public static final Map<String, AddrMode> addrBrTable;
        public static final Map<String, AddrMode> addrITable;



    public static final Map<Mnemon, String> mnemonStringTable;
        public static final Map<AddrMode, String> addrModeStringTable;
        public static final Map<DotCmnd, String> endStringTable;
        public static final Map<DotCmnd, String> blockStringTable;


        static {
            unaryMnemonTable = new HashMap<>();
            unaryMnemonTable.put("STOP", Mnemon.M_STOP);
            unaryMnemonTable.put("ASLA", Mnemon.M_ASLA);
            unaryMnemonTable.put("ASRA", Mnemon.M_ASRA);
            addrBrTable = new HashMap<>();
            addrBrTable.put("x", AddrMode.A_X);
            addrBrTable.put("i", AddrMode.A_I);
            addrTable = new HashMap<>();
            addrTable.put("sfx", AddrMode.A_SFX);
            addrTable.put("x", AddrMode.A_X);
            addrTable.put("sf", AddrMode.A_SF);
            addrTable.put("s", AddrMode.A_S);
            addrTable.put("n", AddrMode.A_N);
            addrTable.put("i", AddrMode.A_I);
            addrTable.put("d", AddrMode.A_D);
            addrTable.put("sx", AddrMode.A_SX);
            addrITable = new HashMap<>();
            addrITable.put("sfx", AddrMode.A_SFX);
            addrITable.put("x", AddrMode.A_X);
            addrITable.put("sf", AddrMode.A_SF);
            addrITable.put("s", AddrMode.A_S);
            addrITable.put("n", AddrMode.A_N);
            addrITable.put("d", AddrMode.A_D);
            addrITable.put("sx", AddrMode.A_SX);
            endTable = new HashMap<>();
            endTable.put("END", DotCmnd.D_END);
            blockTable = new HashMap<>();
            blockTable.put("BLOCK", DotCmnd.D_BLOCK);
            nonUnaryBrMnemonTable = new HashMap<>();
            nonUnaryBrMnemonTable.put("BR", Mnemon.M_BR);
            nonUnaryBrMnemonTable.put("BRLT", Mnemon.M_BRLT);
            nonUnaryBrMnemonTable.put("BREQ", Mnemon.M_BREQ);
            nonUnaryBrMnemonTable.put("BRLE", Mnemon.M_BRLE);
            nonUnaryMnemonTable = new HashMap<>();
            nonUnaryMnemonTable.put("DECI", Mnemon.M_DECI);
            nonUnaryMnemonTable.put("STWA", Mnemon.M_STWA);
            nonUnaryMnemonTable.put("CPWA", Mnemon.M_CPWA);
            nonUnaryMnemonTable.put("DECO", Mnemon.M_DECO);
            nonUnaryMnemonTable.put("ADDA", Mnemon.M_ADDA);
            nonUnaryMnemonTable.put("SUBA", Mnemon.M_SUBA);
            nonUnaryMnemonTable.put("LDWA", Mnemon.M_LDWA);
            mnemonStringTable = new EnumMap<>(Mnemon.class);
            mnemonStringTable.put(Mnemon.M_ASLA, "ASLA");
            mnemonStringTable.put(Mnemon.M_ASRA, "ASRA");
            mnemonStringTable.put(Mnemon.M_BR, "BR");
            mnemonStringTable.put(Mnemon.M_BRLT, "BRLT");
            mnemonStringTable.put(Mnemon.M_BREQ, "BREQ");
            mnemonStringTable.put(Mnemon.M_BRLE, "BRLE");
            mnemonStringTable.put(Mnemon.M_CPWA, "CPWA");
            mnemonStringTable.put(Mnemon.M_STOP, "STOP");
            mnemonStringTable.put(Mnemon.M_DECI, "DECI");
            mnemonStringTable.put(Mnemon.M_DECO, "DECO");
            mnemonStringTable.put(Mnemon.M_ADDA, "ADDA");
            mnemonStringTable.put(Mnemon.M_SUBA, "SUBA");
            mnemonStringTable.put(Mnemon.M_STWA, "STWA");
            mnemonStringTable.put(Mnemon.M_LDWA, "LDWA");
            endStringTable = new EnumMap<>(DotCmnd.class);
            endStringTable.put(DotCmnd.D_END, ".END");
            blockStringTable = new EnumMap<>(DotCmnd.class);
            blockStringTable.put(DotCmnd.D_BLOCK, ".BLOCK");
            addrModeStringTable = new EnumMap<>(AddrMode.class);
            addrModeStringTable.put(AddrMode.A_SFX, ",sfx");
            addrModeStringTable.put(AddrMode.A_SX, ",sx");
            addrModeStringTable.put(AddrMode.A_S, ",s");
            addrModeStringTable.put(AddrMode.A_X, ",x");
            addrModeStringTable.put(AddrMode.A_SF, ",sf");
            addrModeStringTable.put(AddrMode.A_I, ",i");
            addrModeStringTable.put(AddrMode.A_D, ",d");
            addrModeStringTable.put(AddrMode.A_N, ",n");
            addrModeStringTable.put(AddrMode.A_E, " ");



        }
}

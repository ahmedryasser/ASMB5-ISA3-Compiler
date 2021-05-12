package prob0719;

public class Util {
    public static boolean isDigit(char ch) {
        return ('0' <= ch) && (ch <= '9');
    }
    public static boolean isAlpha(char ch) {
        return (('a' <= ch) && (ch <= 'z') || ('A' <= ch) && (ch <= 'Z'));
    }
    public static boolean isHexDigit(char ch) {
        return ('0' <= ch) && (ch <= '9') || (('a' <= ch) && (ch <= 'f')) || ('A' <= ch) && (ch <= 'F');
    }
}

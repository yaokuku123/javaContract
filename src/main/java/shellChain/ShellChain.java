package shellChain;

/**
 * Created by lenovo on 2019/10/15.
 */
public class ShellChain {

    private static String fromAddress;

    public static String getFromAddress() {
        return fromAddress;
    }

    public static void setFromAddress(String fromAddress) {
        ShellChain.fromAddress = fromAddress;
    }
}

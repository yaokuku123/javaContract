package shellChain;

import contractLib.Party;

/**
 * Created by lenovo on 2019/10/15.
 */
public class ShellChain {

    private static String fromAddress;
    public static Party party;

    public static void setParty(Party party) {
        ShellChain.party = party;
    }



    public static String getFromAddress() {
        return fromAddress;
    }

    public static void setFromAddress(String fromAddress) {
        ShellChain.fromAddress = fromAddress;
    }


}

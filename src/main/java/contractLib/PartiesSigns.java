package contractLib;

import java.io.Serializable;

public class PartiesSigns implements Serializable{
    public String userAddress;
    public String signature;

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getSignature() {
        return signature;
    }
}

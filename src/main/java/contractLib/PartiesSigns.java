package contractLib;

import java.io.Serializable;

public class PartiesSigns implements Serializable{

    private static final long serialVersionUID = -1112121462802879461L;
    public String userAddress;
    public String signature;
    public String signDate;

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignDate() {
        return signDate;
    }



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

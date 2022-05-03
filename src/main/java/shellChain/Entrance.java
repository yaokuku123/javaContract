package shellChain;

import contractLib.PartiesSigns;
import contractLib.Party;
import custom.Contract;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import shellChain.util.ReadWriteTxt;

import java.util.ArrayList;

public class Entrance {

    public static void main(String[] args) {

        String randPos = args[0];
        String inPath = randPos+"Out.txt"; // 区块链的out文件进入合约
        String outPath = randPos+"In.txt"; // 合约的输出文件做为区块链的输入
        ReadWriteTxt.writeTxt(outPath,"aaa");
        String inParam = ReadWriteTxt.readTxt(inPath);

        String contractData;
        String funName;

        JSONObject jsonObject = JSONObject.fromObject(inParam);
        JSONObject systemInput = jsonObject.getJSONObject("systemInput");
        contractData = systemInput.getString("contractData");
        funName = systemInput.getString("funName");
        ShellChain.setFromAddress(systemInput.getString("fromAddress"));
        ShellChain.setParty(new Party());
       // JSONArray userParams = jsonObject.getJSONArray("userParams");
        JSONObject contractParams = jsonObject.getJSONObject("contractParams");

        try {
            JSONObject result = HandleContractEntrance.execute(Contract.class,contractData,funName,contractParams);
            ReadWriteTxt.writeTxt(outPath,result.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

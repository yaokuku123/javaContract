package shellChain;

import custom.Contract;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import shellChain.util.ReadWriteTxt;

public class Entrance {

    public static void main(String[] args) {

        String randPos = args[0];
        String inPath = randPos+"Out.txt";
        String outPath = randPos+"In.txt";
        ReadWriteTxt.writeTxt(outPath,"aaa");
        String inParam = ReadWriteTxt.readTxt(inPath);

        String contractData;
        String funName;

        JSONObject jsonObject = JSONObject.fromObject(inParam);
        JSONObject systemInput = jsonObject.getJSONObject("systemInput");
        contractData = systemInput.getString("contractData");
        funName = systemInput.getString("funName");
        ShellChain.setFromAddress(systemInput.getString("fromAddress"));
        JSONArray userParams = jsonObject.getJSONArray("userParams");

        try {
            JSONObject result = HandleContractEntrance.execute(Contract.class,contractData,funName,userParams);
            ReadWriteTxt.writeTxt(outPath,result.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
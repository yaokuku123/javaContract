package shellChain;

import contractLib.PartiesSigns;
import contractLib.Party;
import custom.Contract;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class HandleContractEntrance {

    private static Object[] params;

    public static JSONObject execute(Class c, String contractData, String funName, JSONObject contractParams) throws Exception {

        JSONArray userParams = contractParams.getJSONArray("userParams");
        Object userResult = "";
        String contractDataResult = "";
        String partyForm = "";
        boolean executed = false;
        Contract contract = null;
        if ("_init".equals(funName)) {
            Constructor cons[];
            cons = c.getDeclaredConstructors();
            for (Constructor constructor : cons) {
                Type[] types = constructor.getGenericParameterTypes();
                if (transformParams(types, userParams)) {
                    contract = (Contract) constructor.newInstance(params);
                    executed = true;
                    break;
                }
            }
        } else if ("_sign".equals(funName)) {
            String signature = contractParams.getString("signature");
            String partyForms = contractParams.getString("partyForm");
            Party party = (Party) readObject(partyForms);
            Boolean isSuccess = ShellChain.party.toSign(ShellChain.getFromAddress(), signature);
            if (isSuccess == true) {
                ArrayList<PartiesSigns> listSign = ShellChain.party.getListSign();

                userResult = "Sign Success!!!";
            }



        } else {
            contract = (Contract) readObject(contractData);
            if (contract == null) {
                Constructor constructor = c.getDeclaredConstructor();
                contract = (Contract) constructor.newInstance();
            }
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(funName)) {
                    Type[] types = method.getGenericParameterTypes();
                    if (types.length==0) {
                        userResult = method.invoke(contract);
                        executed = true;
                        break;
                    }else{
                        if (transformParams(types, userParams)) {
                            userResult = method.invoke(contract, params);
                            executed = true;
                            break;
                        }
                    }

                }
            }
        }
        if (!executed) {
            throw new RuntimeException("execute failed");
        }
        contractDataResult = writeObject(contract);
        partyForm = writeObject(ShellChain.party);
        JSONObject result = new JSONObject();
        result.put("contractData", contractDataResult);
        result.put("partyForm", partyForm);
        result.put("userResult", userResult);

        return result;
    }


    public static boolean transformParams(Type[] types, JSONArray jsonParam) {
        if (types.length != jsonParam.size())
            return false;
        params = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            if (types[i] == int.class) {
                params[i] = jsonParam.getInt(i);
            } else if (types[i] == long.class) {
                params[i] = jsonParam.getLong(i);
            } else if (types[i] == Boolean.class || types[i] == boolean.class) {
                params[i] = jsonParam.getBoolean(i);
            } else if (types[i] == float.class || types[i] == double.class) {
                params[i] = jsonParam.getDouble(i);
            } else if (types[i] == String.class) {
                params[i] = jsonParam.getString(i);
            } else {
                return false;
            }
        }
        return true;
    }

    public static String writeObject(Object o) {
        String result = null;
        Object temp;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //向OutPutStream中写入，如 message.writeTo(baos);
            oos.writeObject(o);
            byte[] r = baos.toByteArray();
            result = toHexString(r);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object readObject(String data) {
        Object result = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(toByteArray(data)));
            result = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String toHexString(byte[] byteArray) {
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }


    public static byte[] toByteArray(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}

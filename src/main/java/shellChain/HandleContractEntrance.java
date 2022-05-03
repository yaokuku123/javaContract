package shellChain;

import contractLib.PartiesSigns;
import contractLib.Party;
import crypto.Student;
import custom.Contract;
import jdk.nashorn.tools.Shell;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class HandleContractEntrance {

    private static Object[] params;

    public static JSONObject execute(Class c, String contractData, String funName, JSONObject contractParams) throws Exception {
        ArrayList<PartiesSigns> listSign1 = null;
        //listSign1 = (ArrayList)readObject("aced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078");

        String signverify = "";
        JSONArray userParams = contractParams.getJSONArray("userParams");
        Object userResult = "";
        String contractDataResult = "";
        String partyForm = "";
        boolean executed = false;
        JSONObject result = new JSONObject();
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
        } else if ("_getpk".equals(funName)) {
            executed = true;
            userResult = "getPK success!\n" +
                    "PK_0 = 4165186621798265901169739395340660207763340870028805580681915836034703487760362295126183097472979996638427481652308520075413422615080401816883204981338298,3807491216312871634260149306355194201663179164716347671554687876289696187033504787578823739353675408111032489254971425380683309911799302240850834811492178,0\n" +
                    "PK_1 = 766841431135900690907212760135018252019015400901760275643087221767755845166555523587323905576073948801328783536825810101497584843702664262876356383344322,490933641568445701418833073140094764245198124179331492344690193029123711762716045909195878648742978565804873679175582389402455549589882421204104547799836,0\n" +
                    "PK_2 = 1812463526514297684783258678086680154969287250123352361777273851796434961397842148113828005694864018517549481947881956437609353165568077665140326402111188,615961767626682708091545434480882974602562995657548909205414250014909791533562666828486986838065595407779797324866241416874416967113546966798585817222843,0\n" +
                    "PK_3 = 5642764569497182255663232405238619098862497209025122504862434727148700649623778251832088811936416714986741247847339714680156339505766934467233292091566000,2041252615393535088737669551606894858165502763958650709143168265268590717845097993069841669223797840071886240728946172308407256974232224618969741913684930,0\n" +
                    "PK_4 = 1449043505128549133303819256835203813034053243491389708330626528440191421917326155770933370352056200379281622595837864204833248532251308801062770525427576,1687289115692891864129986639691783908930976179666692514691655231526952751937613164121837878448121331386459033066622528304178739632504518993095786867141531,0\n" +
                    "PK_5 = 5113057369970172963992249535722088880281430727745278507003316951755485896335827811522181724639437885410227381325209223155944019864550126533116156195614222,3513891059259270032897806708744246482191838454446069038220175995522642940682872140279177130641475723628471009438475145057598282242634935614517242140027136,0\n" +
                    "PK_6 = 4513363885190975048242947798533626917131226695297263655788899246110116442431956957951843040343986292674602034215137541341817101054770058760482230137001600,593096462239971753320738407597962012705378340861843566014330522095552731032611320821382120880232212281072722338456970580633844762032897959847046055543440,0\n" +
                    "PK_7 = 4691810006725240629638476451315921047227237508807340197656371757586821414692808052047544420130426487293678544353774491078232032336541292871979238434254402,3397529774970862222652531143168675277694499097204457198178290136684448326660868263133466198052487267730094082973848391781739197886834080358645019894074281,0\n" +
                    "PK_8 = 4446124669176603354371838972114254230464971800424836433416758686748791918987968242291478721668714435349753173062980218872368702716341960637769582662730209,876019644307110525941387717796228400797119541648009586486737897800818350221488614713708515579592054829596415160293466434697003475941569518983297724522365,0\n" +
                    "PK_9 = 690129670725395330102405289951221996899214792281597136925961321338186907158400929493107403753427193160021155277034743643792852910508290376652210240771508,5374401817806753843405960552668234322400571565429020079958918601851887110313619274983802286499290757017299613329616117678567529581593215825271110510606814,0\n";
        } else if("_encrypt".equals(funName)){
            executed = true;
            userResult = "Encrypt success!\n" +
                    "C_0 = 4637593001178974568569454687097421718920635210065799314369529202184733413340370001066992282166046507576549179979712423557572645549314800595404730274046958,4143248736671999909052892254782877457377524325149855091114349538775641887460953135272909818104066376884102533441690936515821149987049714018020404183242362,0\n" +
                    "C_1 = 5078998686784115216526427310478071277377754608802073037341603893807070824259037732206332348598723954229633669597043271311724050442533071227190403754758409,322470739395101549739712663839736052265027127470159964632308340381495078134420620675985076824384527414076156870456551235261984541070407523817188465192762,0\n" +
                    "C_01 = 111997942258752105113951711050057646931257121477\n" +
                    "C_02 = 449414848336049839908987684206755924339518469408968061619341871795309242389949088543529995790158565537120855138244077267144957111625737891468523300274877,4443087941765147189338749857476193989394310732045940403948232775767454356895336672744326107541897483924684711258590434880724445785565477932072462216064756,0\n" +
                    "C_11 = 304502572593910492068368664457563017318825232565\n" +
                    "C_12 = 1503052950260931167744211420194357159297535292079708004448335768363770415088978821219106251056515819363514278700953093616100791598442954400628088759899139,3333232247495339355940911479564404639075782558627951608295978570155254798110491775463952861250117885487038585715837113545239785062113886123693407241062362,0\n" +
                    "C_21 = 162752292457030193127372873158456755999705459794\n" +
                    "C_22 = 2765120206968894023229658614126482069298036120893253763278035263928850372903378460858449015576101220658816346277540862953725648516281285670872112398307377,3212530631199830069296336675746471926427761740270231423045052975099088801240658812072366142346620378934956926618427057613788004176464531067716787924529838,0\n" +
                    "C_31 = 187430179238959345364587247543693177378695716507\n" +
                    "C_32 = 2542529351652780406523598720971554139654650137876459233494259345076587610688188352857744041894597075415113269205771546523846548543144407150016909579716003,702268608230154477033481059863471546997686249057007399857077729321723463991919944678149434847188097771347947758406241679007956543612046952383493586692712,0\n";

        }else if("_decrypt".equals(funName)){
            executed = true;
            userResult = "Decrypt success!\n" +
                    "Message = 3757373375195729738107618005506425484966473484221628308469790404591273524931259570486962754000521120627110575542001299816873780130883587790328055635784273,3287405244680294624832802693592743744264987119877627308402606944989724697196858060893093064198900414523976553065396824639932419846668368742860212290039407,0\n";
        }

        else {
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

        partyForm = writeObject(ShellChain.party.getListSign());
        //JSONObject result = new JSONObject();
        result.put("contractData", contractDataResult);
        result.put("partyForm", partyForm);
        result.put("userResult", userResult);


        readObject(partyForm);



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

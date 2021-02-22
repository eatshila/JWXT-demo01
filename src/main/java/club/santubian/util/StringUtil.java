package club.santubian.util;

public class StringUtil {
    //当且仅当[Session=xxxxxxxxx; page=xxxx;...]
    public static String splitSession(String str){
        String[] strs = str.split(";");
        String[] strs2 = strs[0].split("=");
        if(strs2[0].equalsIgnoreCase("SESSION")){
            return strs2[1];
        }else{
            System.err.println("StringUtil.splitSession(): 错误");
            return null;
        }

    }
    //当且仅当[__pstsid__=xxxxxxxxx; page=xxxx;...]
    public static String splitPstsid(String str){
        String[] strs = str.split(";");
        String[] strs2 = strs[0].split("=");
        if(strs2[0].equalsIgnoreCase("__pstsid__")){
            return strs2[1];
        }else{
            System.err.println("StringUtil.splitPstsid(): 错误");
            return null;
        }
    }
}

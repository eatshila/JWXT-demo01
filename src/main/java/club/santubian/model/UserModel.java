package club.santubian.model;


import club.santubian.pojo.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

import static club.santubian.util.JiaMI.getSha1;
import static club.santubian.util.StringUtil.splitPstsid;
import static club.santubian.util.StringUtil.splitSession;

public class UserModel {
    public static String name;
//    public static String username;
//    public static String password;
    public static String pstsid;
    public static String session;
    public static String salt;
    public static boolean isLogin;
    public static String msg;
    public static int state;

    private static void setPstsid() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://jw.ahnu.edu.cn/student/login")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.headers("Set-Cookie").get(0);
            pstsid = splitPstsid(str);
        } catch (IOException e) {
            System.err.println("UserModel.setPstsid(): pstsid获取失败");
            e.printStackTrace();
        }
    }

    private static boolean setSalt() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://jw.ahnu.edu.cn/student/login-salt")
                .method("GET", null)
                .addHeader("Cookie", "__pstsid__=" + pstsid)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.headers("Set-Cookie").get(0);
            session = splitSession(str);
            String salt1 = response.body().string();
            if(null == salt1){
                System.err.println("UserModel.setSalt(): 获取salt失败");
                return false;
            }else{
                salt = salt1;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //无验证码登录
    public static boolean login(String username,String password){
        setPstsid();
        setSalt();
        String passwordSha1 = getSha1(salt + "-" + password);
        if(session == null ||
                session.equalsIgnoreCase("") ||
                pstsid == null ||
                pstsid.equalsIgnoreCase("")
        ){
            System.err.println("UserModel.login():session 和 pstsid 错误");
            return false;
        }else{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            String bodyContend = "{\r\n    \"username\":\""
                    + username
                    + "\",\r\n    \"password\":\""
                    + passwordSha1
                    + "\", \r\n    \"captcha\":\""
                    + ""
                    + "\",\r\n    \"terminal\":\"student\"\r\n}";
            RequestBody body = RequestBody.create(mediaType,bodyContend);
            Request request = new Request.Builder()
                    .url("http://jw.ahnu.edu.cn/student/login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "SESSION=" + session + "; __pstsid__=" + pstsid + "")
                    .build();
            try {
                Response response = client.newCall(request).execute();
//                System.out.println(response.body().string());
                String str = response.body().string();
                System.out.println(str);
                ObjectMapper objectMapper = new ObjectMapper();
                LoginResponse loginResponse = objectMapper.readValue(str, LoginResponse.class);
                if (loginResponse.isResult()){
                    msg = loginResponse.getMessage();
                    return true;
                }else{
                    msg = loginResponse.getMessage();
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void logout(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://jw.ahnu.edu.cn/student/logout")
                .method("GET", null)
                .addHeader("Cookie", "SESSION=" + UserModel.session + "; __pstsid__=" + UserModel.pstsid)
                .build();
        try {
            Response response = client.newCall(request).execute();
            UserModel.isLogin = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserModel(){}
}

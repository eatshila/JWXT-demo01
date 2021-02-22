package club.santubian.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("result")
    private boolean result;
    @JsonProperty("message")
    private String message;
    @JsonProperty("needCaptcha")
    private boolean needCaptcha;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNeedCaptcha() {
        return needCaptcha;
    }

    public void setNeedCaptcha(boolean needCaptcha) {
        this.needCaptcha = needCaptcha;
    }
}

package cn.svecri.autotopo.vo;

import java.io.Serializable;

public class ResponseVo implements Serializable {

    public static final int OK = 0;
    public static final int SUBSCRIBE_FAILED = 1;
    public static final int UNSUBSCRIBE_FAILED = 2;

    public ResponseVo(int errorID, String errorMsg) {
        this.errorID = errorID;
        this.errorMsg = errorMsg;
        this.data = null;
    }

    public ResponseVo(int errorID, String errorMsg, Object data) {
        this.errorID = errorID;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static ResponseVo ok() {
        return new ResponseVo(OK, "success");
    }

    public static ResponseVo ok(Object data) {
        return new ResponseVo(OK, "success", data);
    }

    public static ResponseVo error(int errorID, Exception e) {
        return new ResponseVo(errorID, e.getMessage());
    }

    public int errorID;

    public String errorMsg;

    public Object data;
}

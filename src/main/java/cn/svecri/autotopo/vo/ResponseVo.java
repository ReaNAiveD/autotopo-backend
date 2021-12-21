package cn.svecri.autotopo.vo;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {

    public static final int UNKNOWN = -1;
    public static final int OK = 0;
    public static final int NOT_FOUND = 1;
    public static final int NON_DELETABLE = 2;
    public static final int NO_TOPO_DEPLOYED = 3;
    public static final int TOPO_RUNNING = 4;

    public ResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResponseVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVo<Object> ok() {
        return new ResponseVo<>(OK, "success");
    }

    public static <T> ResponseVo<T> ok(T data) {
        return new ResponseVo<>(OK, "success", data);
    }

    public static ResponseVo<Object> error(int errorID, Exception e) {
        return new ResponseVo<>(errorID, e.getMessage());
    }

    public int code;

    public String msg;

    public T data;
}

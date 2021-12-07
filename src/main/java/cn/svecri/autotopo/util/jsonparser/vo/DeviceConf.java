package cn.svecri.autotopo.util.jsonparser.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Lmt
 * @date 2021/12/7 19:45
 */
@Data
public class DeviceConf {
    private String topotype;
    @SerializedName("RouterA")
    private DeviceConfItem routerA;
    @SerializedName("RouterB")
    private DeviceConfItem routerB;
    @SerializedName("RouterC")
    private DeviceConfItem routerC;
}

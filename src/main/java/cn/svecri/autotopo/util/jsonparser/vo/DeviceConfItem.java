package cn.svecri.autotopo.util.jsonparser.vo;

import lombok.Data;

/**
 * @author Lmt
 * @date 2021/12/7 19:47
 */
@Data
public class DeviceConfItem {
    private PortDetail[] port;
    private String[] command;
}

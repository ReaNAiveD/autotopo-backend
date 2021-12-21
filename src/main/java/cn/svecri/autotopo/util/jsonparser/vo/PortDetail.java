package cn.svecri.autotopo.util.jsonparser.vo;

import lombok.Data;

/**
 * @author Lmt
 * @date 2021/12/7 19:48
 */
@Data
public class PortDetail {
    private String name;
    private String ip;
    private Integer mask;
    private Boolean up;
}

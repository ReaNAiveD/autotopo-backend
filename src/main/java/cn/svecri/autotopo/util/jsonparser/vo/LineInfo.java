package cn.svecri.autotopo.util.jsonparser.vo;

import lombok.Data;

/**
 * @author Lmt
 * @date 2021/12/21 11:14
 */
@Data
public class LineInfo {
    private String[] from;
    private String[] to;
    private Boolean logicalConnected;
}

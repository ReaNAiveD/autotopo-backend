package cn.svecri.autotopo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Lmt
 * @date 2021/12/21 19:38
 */
@Slf4j
public final class NetmaskConverter {
    //从数字格式的子网掩码获得255.255.255.0形式的子网掩码
    public static String masknum2str(int maskNum){
        int addr=0xffffffff<<32-maskNum;
        String res= (addr >> 24 & 0xff) + "." + (addr >> 16 & 0xff) + "." + (addr >> 8 & 0xff) + "." + (addr & 0xff);
        log.info("netmask: "+res);
        return res;
    }

    //根据数字格式的子网掩码和ip地址获得其网段
    public static String getNetSegment(String ip,int maskNum){
        int[] num= Arrays.stream(ip.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int addr=0xffffffff<<32-maskNum;
        String res= (num[0] & addr >> 24 & 0xff) + "." + (num[1]&addr >> 16 & 0xff) + "."
                + (num[2] &addr >> 8 & 0xff) + "." + (num[3]&addr & 0xff);
        log.info("network segment:"+res);
        return res;
    }

    private NetmaskConverter(){}
}

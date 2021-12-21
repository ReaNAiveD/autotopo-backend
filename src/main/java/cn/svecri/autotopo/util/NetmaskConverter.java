package cn.svecri.autotopo.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lmt
 * @date 2021/12/21 19:38
 */
@Slf4j
public final class NetmaskConverter {
    public static String masknum2str(int maskNum){
        int addr=0xffffffff<<32-maskNum;
        String res= (addr >> 24 & 0xff) + "." + (addr >> 16 & 0xff) + "." + (addr >> 8 & 0xff) + "." + (addr & 0xff);
        log.info("netmask: "+res);
        return res;
    }

    private NetmaskConverter(){}
}

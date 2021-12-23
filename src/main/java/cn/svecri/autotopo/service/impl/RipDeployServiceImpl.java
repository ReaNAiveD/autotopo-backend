package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.NetmaskConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Qualifier("ripDeployService")
public class RipDeployServiceImpl extends TopoDeployServiceBaseImpl{
    @Override
    public void clean() {
        isDeploying.set(true);
        log.info("in rip clean");
        isDeploying.set(false);
        clientList.clear();
    }

    @Override
    public String concatCommand(String deviceName,String originRe){
        log.info("in rip concat Command");
        String rexpStr="";
        switch (deviceName) {
            case "routerA":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sIpAddr[2],sMask[2]))
                        .replace("#2",sMask[2]+"").replace("#3",sIpAddr[1])
                        .replace("#4",sName[0].substring(1));
                break;
            case "routerB":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sIpAddr[1],sMask[1]))
                        .replace("#2",sMask[1]+"").replace("#3",sName[1].substring(1))
                        .replace("#4",NetmaskConverter.getNetSegment(sIpAddr[2],sMask[2]))
                        .replace("#5",sMask[2]+"").replace("#6",sIpAddr[2].substring(1));
                break;
            case "routerC":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sIpAddr[1],sMask[1]))
                        .replace("#2",sMask[1]+"").replace("#3",sIpAddr[2])
                        .replace("#4",sName[3].substring(1));
                break;
            default:
                log.error("invalid target telnet");
        }
        return rexpStr;
    }

}

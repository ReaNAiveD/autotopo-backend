package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.NetmaskConverter;
import cn.svecri.autotopo.vo.TestCaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("ospfDeployService")
public class OspfDeployServiceImpl extends TopoDeployServiceBaseImpl{
    @Override
    public String concatCommand(String deviceName,String originRe){
        log.info("in ospf concat Command");
        String rexpStr="";
        switch (deviceName) {
            case "routerA":
                rexpStr=originRe
                        .replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b1").getIp(),sPortList.get("b1").getMask()))
                        .replace("#2",sPortList.get("b1").getMask()+"")
                        .replace("#3",sPortList.get("b0").getIp())
                        .replace("#4",sPortList.get("a0").getName().substring(1));
                break;
            case "routerB":
                rexpStr=originRe
                        .replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b0").getIp(),sPortList.get("b0").getMask()))
                        .replace("#2",sPortList.get("b0").getMask()+"")
                        .replace("#3",sPortList.get("b0").getName().substring(1))
                        .replace("#4",NetmaskConverter.getNetSegment(sPortList.get("b1").getIp(),sPortList.get("b1").getMask()))
                        .replace("#5",sPortList.get("b1").getMask()+"")
                        .replace("#6",sPortList.get("b1").getIp().substring(1));
                break;
            case "routerC":
                rexpStr=originRe
                        .replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b0").getIp(),sPortList.get("b0").getMask()))
                        .replace("#2",sPortList.get("b0").getMask()+"")
                        .replace("#3",sPortList.get("b1").getIp())
                        .replace("#4",sPortList.get("c0").getName().substring(1));
                break;
            default:
                log.error("invalid target telnet");
        }
        return rexpStr;
    }

}

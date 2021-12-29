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
            case "RouterA":
                rexpStr=originRe
                        .replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b1").getIp(),sPortList.get("b1").getMask()))
                        .replace("#2",sPortList.get("b1").getMask()+"")
                        .replace("#3",sPortList.get("b0").getIp())
                        .replace("#4",sPortList.get("a0").getName().substring(1));
                break;
            case "RouterB":
                rexpStr=originRe
                        .replace("#1", loPortList.get("lo0").getIp())
                        .replace("#2", sPortList.get("a0").getIp())
                        .replace("#3", sPortList.get("b0").getName().substring(1))
                        .replace("#4", loPortList.get("lo1").getIp())
                        .replace("#5", loPortList.get("lo2").getIp());
                break;
            case "RouterC":
                rexpStr=originRe
                        .replace("#1", loPortList.get("lo0").getIp())
                        .replace("#2", sPortList.get("b1").getIp())
                        .replace("#3", sPortList.get("c0").getName().substring(1))
                        .replace("#4", NetmaskConverter.getNetSegment(sPortList.get("a0").getIp(),sPortList.get("a0").getMask()))
                        .replace("#5", NetmaskConverter.getNetSegment(fPortList.get("af1").getIp(),fPortList.get("af1").getMask()))
                        .replace("#6",sPortList.get("a0").getMask()+"")
                        .replace("#7",fPortList.get("af1").getMask()+"");
                break;
            default:
                log.error("invalid target telnet");
        }
        return rexpStr;
    }

}

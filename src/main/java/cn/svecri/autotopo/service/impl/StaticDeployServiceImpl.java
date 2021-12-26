package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.util.NetmaskConverter;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;

import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import cn.svecri.autotopo.vo.TestCaseResult;
import cn.svecri.autotopo.vo.TestCaseResultItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Qualifier("staticDeployService")
public class StaticDeployServiceImpl extends TopoDeployServiceBaseImpl{
    @Override
    public String concatCommand(String deviceName,String originRe){
        log.info("in static concat Command");
        String rexpStr="";
        switch (deviceName) {
            case "routerA":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sPortList.get("a0").getIp(),sPortList.get("a0").getMask()))
                        .replace("#2",sPortList.get("a0").getMask()+"")
                        .replace("#3",sPortList.get("a0").getName().substring(1))
                        .replace("#4",NetmaskConverter.getNetSegment(sPortList.get("b1").getIp(),sPortList.get("b1").getMask()))
                        .replace("#5",sPortList.get("b1").getMask()+"")
                        .replace("#6",sPortList.get("b0").getIp());
                break;
            case "routerB":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b0").getIp(),sPortList.get("b0").getMask()))
                        .replace("#2",sPortList.get("b0").getMask()+"")
                        .replace("#3",sPortList.get("b0").getName().substring(1))
                        .replace("#4",NetmaskConverter.getNetSegment(sPortList.get("b1").getIp(),sPortList.get("b1").getMask()))
                        .replace("#5",sPortList.get("b1").getMask()+"")
                        .replace("#6",sPortList.get("b1").getIp().substring(1));
                break;
            case "routerC":
                rexpStr=originRe.replace("#1", NetmaskConverter.getNetSegment(sPortList.get("b0").getIp(),sPortList.get("b0").getMask()))
                        .replace("#2",sPortList.get("b0").getMask()+"")
                        .replace("#3",sPortList.get("b1").getIp())
                        .replace("#4",NetmaskConverter.getNetSegment(sPortList.get("c0").getIp(),sPortList.get("c0").getMask()))
                        .replace("#5",sPortList.get("c0").getMask()+"")
                        .replace("#6",sPortList.get("c0").getName());
                break;
            default:
                log.error("invalid target telnet");
        }
        return rexpStr;
    }
}

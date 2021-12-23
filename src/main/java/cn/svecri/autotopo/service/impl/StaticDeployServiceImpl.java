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
    public void clean() {
        log.info("in static clean");
    }

    @Override
    public TestCaseResult runTestCase(List<TopoTestCase> testCaseList){
        //分别是 A s0/0/0 B s0/0/0 s0/0/1 C s0/0/0 先写死了后面要改再说吧
        String[] sIpAddr=new String[4];
        int[] sMask=new int[4];
        String[] sName=new String[4];

        int index=0;
        while(index<deviceConf.getRouter().length) {
            for (PortDetail portDetail : deviceConf.getRouter()[index].getPort()) {
                //打开的s口
                if (portDetail.isUp() && portDetail.getName().startsWith("s")) {
                    sIpAddr[index] = portDetail.getIp();
                    sMask[index] = portDetail.getMask();
                    sName[index] = portDetail.getName();
                }
                index++;
            }
        }
        List<TestCaseResultItem> resultItems = new ArrayList<>();
        int success = 0;
        int total = 0;
        boolean pass=false;
        List<CommandWithResult> result;
        String cmdStr="";
        String rexpStr="";

        for(TopoTestCase testCase:testCaseList) {
            //打开的s口应该可以互相ping通 下面添加的是ping命令
            if(testCase.getCmd().startsWith("ping ")) {
                for(String ip:sIpAddr) {
                    cmdStr=testCase.getCmd().replace("#1",ip);
                    rexpStr=testCase.getExpectedRe();
                    result = exec(Collections.singletonList(
                            new Command(testCase.getTargetTelnet(), cmdStr)));
                    pass = result.get(0).rsp.matches(rexpStr);
                    resultItems.add(new TestCaseResultItem(
                            result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                    if (pass) {success++;}
                    total++;
                }
            }else{
                //show ip route 命令
                cmdStr=testCase.getCmd();
                result=exec(Collections.singletonList(new Command(testCase.getTargetTelnet(), cmdStr)));
                switch (testCase.getTargetTelnet()) {
                    case "routerA":
                        rexpStr=testCase.getExpectedRe().replace("#1", NetmaskConverter.getNetSegment(sIpAddr[0],sMask[0]))
                                .replace("#2",sMask[0]+"").replace("#3",sName[0].substring(1))
                                .replace("#4",NetmaskConverter.getNetSegment(sIpAddr[2],sMask[2]))
                                .replace("#5",sMask[2]+"").replace("#6",sIpAddr[1]);
                        break;
                    case "routerB":
                        rexpStr=testCase.getExpectedRe().replace("#1", NetmaskConverter.getNetSegment(sIpAddr[1],sMask[1]))
                                .replace("#2",sMask[1]+"").replace("#3",sName[1].substring(1))
                                .replace("#4",NetmaskConverter.getNetSegment(sIpAddr[2],sMask[2]))
                                .replace("#5",sMask[2]+"").replace("#6",sIpAddr[2].substring(1));
                        break;
                    case "routerC":
                        rexpStr=testCase.getExpectedRe().replace("#1", NetmaskConverter.getNetSegment(sIpAddr[1],sMask[1]))
                                .replace("#2",sMask[1]+"").replace("#3",sIpAddr[2])
                                .replace("#4",NetmaskConverter.getNetSegment(sIpAddr[3],sMask[3]))
                                .replace("#5",sMask[3]+"").replace("#6",sName[3].substring(1));
                        break;
                    default:
                        log.error("invalid target telnet");
                }
                pass = result.get(0).rsp.matches(rexpStr);
                resultItems.add(new TestCaseResultItem(
                        result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                if (pass) {success++;}
                total++;
            }
        }
        return new TestCaseResult(((double) success) / total, resultItems);
    }
}

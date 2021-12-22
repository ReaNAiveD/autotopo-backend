package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.service.TopoConfigService;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/topology/cmd")
public class DeviceController {
    @Autowired
    TopoDeployService topoDeployService;

    @PostMapping("/exec")
    public ResponseVo<Object> execCmd(@RequestBody Command cmd) {
        return ResponseVo.ok(topoDeployService.exec(Collections.singletonList(cmd)));
    }

}

package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/topology/cmd")
public class DeviceController {

    @PostMapping("/exec")
    public ResponseVo<Object> execCmd(@RequestBody Command cmd) {
        return ResponseVo.ok();
    }

}

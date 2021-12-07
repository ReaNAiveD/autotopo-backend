package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topology/config")
public class TopoConfigController {

    @GetMapping("/default")
    public ResponseVo getDefaultConfig(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @GetMapping("/schema")
    public ResponseVo getConfigSchema(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @PostMapping("")
    public ResponseVo saveConfig(@RequestParam int topo, @RequestBody String content) {
        return ResponseVo.ok();
    }

    @PostMapping("/apply")
    public ResponseVo applyConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

    @PostMapping("")
    public ResponseVo getConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

    @PostMapping("/check")
    public ResponseVo checkConfig(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @GetMapping("/all")
    public ResponseVo getAllConfig() {
        return ResponseVo.ok();
    }

    @DeleteMapping("")
    public ResponseVo deleteConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

}

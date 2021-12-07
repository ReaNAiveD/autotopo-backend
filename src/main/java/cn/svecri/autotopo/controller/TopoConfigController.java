package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topology/config")
public class TopoConfigController {

    @GetMapping("/default")
    public ResponseVo<Object> getDefaultConfig(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @GetMapping("/schema")
    public ResponseVo<Object> getConfigSchema(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @PostMapping("")
    public ResponseVo<Object> saveConfig(@RequestParam int topo, @RequestBody String content) {
        return ResponseVo.ok();
    }

    @PostMapping("/apply")
    public ResponseVo<Object> applyConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

    @GetMapping("")
    public ResponseVo<Object> getConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

    @PostMapping("/check")
    public ResponseVo<Object> checkConfig(@RequestParam int topo) {
        return ResponseVo.ok();
    }

    @GetMapping("/all")
    public ResponseVo<Object> getAllConfig() {
        return ResponseVo.ok();
    }

    @DeleteMapping("")
    public ResponseVo<Object> deleteConfig(@RequestParam int configId) {
        return ResponseVo.ok();
    }

}

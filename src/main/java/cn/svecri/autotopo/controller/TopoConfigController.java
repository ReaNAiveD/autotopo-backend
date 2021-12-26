package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.exception.NoTopoDeployedException;
import cn.svecri.autotopo.exception.NonDeletableException;
import cn.svecri.autotopo.exception.NotFoundException;
import cn.svecri.autotopo.exception.TopoRunningException;
import cn.svecri.autotopo.service.TopoConfigService;
import cn.svecri.autotopo.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/topology/config")
public class TopoConfigController {

    @Resource
    private TopoConfigService topoConfigService;

    @GetMapping("/default")
    public ResponseVo<TopoConfigVo> getDefaultConfig(@RequestParam int topo) {
        return ResponseVo.ok(topoConfigService.getDefaultTopoConfig(topo));
    }

    @GetMapping("/schema")
    public ResponseVo<TopoSchemaVo> getConfigSchema(@RequestParam int topo) {
        return ResponseVo.ok(topoConfigService.getTopoSchema(topo));
    }

    @PostMapping("")
    public ResponseVo<TopoConfigSaveResult> saveConfig(@RequestParam int topo, @RequestBody String content) {
        return ResponseVo.ok(topoConfigService.saveTopoConfig(topo, content));
    }

    @PostMapping("/apply")
    public ResponseVo<TopoConfigApplyResult> applyConfig(@RequestParam int configId) {
        return ResponseVo.ok(topoConfigService.deployConfig(configId, true));
    }

    @GetMapping("")
    public ResponseVo<TopoConfigVo> getConfig(@RequestParam int configId) {
        return ResponseVo.ok(topoConfigService.getConfig(configId));
    }

    @PostMapping("/check")
    public ResponseVo<TestCaseResult> checkConfig(@RequestParam int topo) {
        return ResponseVo.ok(topoConfigService.testConfig(topo, false));
    }

    @GetMapping("/all")
    public ResponseVo<TopoConfigListVo> getAllConfig() {
        return ResponseVo.ok(topoConfigService.getAllTopoConfig());
    }

    @DeleteMapping("")
    public ResponseVo<Object> deleteConfig(@RequestParam int configId) {
        topoConfigService.deleteByConfigId(configId);
        return ResponseVo.ok();
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseVo<Object> handleNotFoundException(Exception e) {
        return ResponseVo.error(ResponseVo.NOT_FOUND, e);
    }

    @ExceptionHandler({NonDeletableException.class})
    public ResponseVo<Object> handleNonDeletableException(Exception e) {
        return ResponseVo.error(ResponseVo.NON_DELETABLE, e);
    }

    @ExceptionHandler({NoTopoDeployedException.class})
    public ResponseVo<Object> handleNoTopoDeployedException(Exception e) {
        return ResponseVo.error(ResponseVo.NO_TOPO_DEPLOYED, e);
    }

    @ExceptionHandler({TopoRunningException.class})
    public ResponseVo<Object> handleTopoRunningException(Exception e) {
        return ResponseVo.error(ResponseVo.TOPO_RUNNING, e);
    }

}

package com.gsp.app.controller;

import com.gsp.app.model.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 */
@RestController
@RequestMapping("/basic")
public class HealthCheckController {

    @RequestMapping("/healthCheck")
    public ResponseVo<String> name() {
        return ResponseVo.ofSuccess();
    }
}
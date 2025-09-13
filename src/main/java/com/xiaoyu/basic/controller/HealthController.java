package com.xiaoyu.basic.controller;

import com.xiaoyu.basic.common.BaseResponse;
import com.xiaoyu.basic.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HealthController
 * Description:
 *
 * @Author: fy
 * @create: 2025-07-26 21:21
 * @version: 1.0
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }
}

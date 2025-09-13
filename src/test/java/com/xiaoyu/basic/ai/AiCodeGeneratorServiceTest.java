package com.xiaoyu.basic.ai;

import com.xiaoyu.basic.ai.model.HtmlCodeResult;
import com.xiaoyu.basic.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: AiCodeGeneratorServiceTest
 * Description:
 *
 * @Author: fy
 * @create: 2025-08-31 16:06
 * @version: 1.0
 */
@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("请你帮我创建一个个人博客，代码不超过20行");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("请你帮我创建一个个人博客，总代码不超过50行");
        Assertions.assertNotNull(result);
    }
}
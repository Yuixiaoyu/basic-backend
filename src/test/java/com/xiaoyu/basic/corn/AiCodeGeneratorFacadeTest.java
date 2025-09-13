package com.xiaoyu.basic.corn;

import com.xiaoyu.basic.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.File;

/**
 * ClassName: AiCodeGeneratorFacadeTest
 * Description:
 *
 * @Author: fy
 * @create: 2025-08-31 17:31
 * @version: 1.0
 */
@SpringBootTest
class AiCodeGeneratorFacadeTest {


    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;


    @Test
    void generateAndSaveCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("生成一个HTML页面,总代码不超过20行", CodeGenTypeEnum.MULTI_FILE);
        System.out.println(file);
    }


    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("生成一个网页，总代码不能超过20行", CodeGenTypeEnum.HTML);
    }
}
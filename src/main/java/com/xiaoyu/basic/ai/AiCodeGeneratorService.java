package com.xiaoyu.basic.ai;

import com.xiaoyu.basic.ai.model.HtmlCodeResult;
import com.xiaoyu.basic.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

/**
 * ClassName: AiCodeGeneratorService
 * Description:
 *
 * @Author: fy
 * @create: 2025-08-31 13:47
 * @version: 1.0
 */
public interface AiCodeGeneratorService {

    /**
     * 生成html代码
     * @param userMessage 用户消息
     * @return 生成的代码
     *
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMessage);

    /**
     * 生成多文件代码
     * @param userMessage 用户消息
     * @return 生成的代码
     *
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);

    /**
     * 生成html代码(流式)
     * @param userMessage 用户消息
     * @return 生成的代码
     *
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream(String userMessage);

    /**
     * 生成多文件代码(流式)
     * @param userMessage 用户消息
     * @return 生成的代码
     *
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userMessage);

}

package com.course.system.controller;

import com.course.server.dto.ResponseDto;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 田付成
 * @date 2021/4/12 21:33
 */


/*自动刷新*/
@RefreshScope
@RestController
@RequestMapping("/test")
public class TestController {

   /* @Value("${system.test}")*/
    private String systemTest;

    @GetMapping("/list")
    public ResponseDto list() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(systemTest);
        return responseDto;
    }
}

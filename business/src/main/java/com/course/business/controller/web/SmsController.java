package com.course.business.controller.web;

import com.course.server.dto.ResponseDto;
import com.course.server.dto.SmsDto;
import com.course.server.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 田付成
 * @date 2021/3/26 10:26
 */

@RestController("webSmsController")
@RequestMapping("/web/sms")
public class SmsController {

    private static final Logger LOG = LoggerFactory.getLogger(SmsController.class);
    public static final String BUSINESS_NAME = "短信验证码";

    @Resource
    private SmsService smsService;

    /**
     * 前端传递的对象是sms对象
     * @param smsDto
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseDto send(@RequestBody SmsDto smsDto) {
        LOG.info("发送短信请求开始: {}", smsDto);
        //创建一个返回Dto值
        ResponseDto responseDto = new ResponseDto();
        smsService.sendCode(smsDto);
        LOG.info("发送短信请求结束");
        return responseDto;
    }
}

package com.course.generator.server;

import com.course.generator.util.FreemarkUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 田付成
 * @date 2021/3/17 11:09
 */
public class ServerGenerator {

    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = "business\\src\\main\\java\\com\\course\\business\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);

        //生成service
        FreemarkUtil.initConfig("service.ftl");
        FreemarkUtil.generator(toServicePath + Domain + "Service.java",map);

        //生成controller
        FreemarkUtil.initConfig("controller.ftl");
        FreemarkUtil.generator(toControllerPath + Domain + "Controller.java",map);
    }
}

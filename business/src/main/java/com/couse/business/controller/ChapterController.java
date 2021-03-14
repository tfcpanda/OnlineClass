package com.couse.business.controller;

import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class ChapterController {

    @Resource
    private ChapterService ChapterService;

    @RequestMapping("/Chapter")
    public List<Chapter> Chapter(){
        return ChapterService.list();
    }

}

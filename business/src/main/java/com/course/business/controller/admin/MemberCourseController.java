package com.course.business.controller.admin;

import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberCourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/memberCourse")
public class MemberCourseController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);
    public static final String BUSINESS_NAME = "会员课程报名";
    @Resource
    private MemberCourseService memberCourseService;


/*    *//**
     * 列表查询
     * @param pageDto
     * @return
     *//*
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto:{}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        memberCourseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }*/

    /**
     * 保存id，id有值的时候更新，没有值的时候增加
     *
     * @param memberCourseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberCourseDto memberCourseDto) {
        ResponseDto responseDto = new ResponseDto();
        //后端校验填入信息
        ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
        ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");
        ValidatorUtil.require(memberCourseDto.getAt(), "报名时间");
        memberCourseService.save(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("id:{}", id);
        memberCourseService.delete(id);

        return responseDto;
    }
}

package com.course.business.controller.admin;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";
    @Resource
    private CourseService courseService;


    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto:{}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     *保存id，id有值的时候更新，没有值的时候增加
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        ResponseDto responseDto = new ResponseDto();

        //后端校验填入信息


            ValidatorUtil.require(courseDto.getName(), "名称");
            ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);


            ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);






            ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);













        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     *删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("id:{}", id);
        courseService.delete(id);

        return responseDto;
    }
}

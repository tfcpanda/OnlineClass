package com.course.business.controller.admin;

import com.course.server.dto.ResponseDto;
import com.course.server.dto.SectionDto;
import com.course.server.dto.SectionPageDto;
import com.course.server.service.SectionService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";
    @Resource
    private SectionService sectionService;


    /**
     * 列表查询
     * @param sectionPageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SectionPageDto sectionPageDto) {
        LOG.info("PageDto:{}", sectionPageDto);
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(sectionPageDto.getCourseId(),"课程ID");
        ValidatorUtil.require(sectionPageDto.getChapterId(), "大章ID");
        sectionService.list(sectionPageDto);
        responseDto.setContent(sectionPageDto);
        return responseDto;
    }

    /**
     *保存id，id有值的时候更新，没有值的时候增加
     * @param sectionDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {
        ResponseDto responseDto = new ResponseDto();
        //后端校验填入信息
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
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
        sectionService.delete(id);
        return responseDto;
    }
}

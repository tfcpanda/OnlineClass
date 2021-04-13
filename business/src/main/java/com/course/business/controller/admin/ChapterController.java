package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";
    @Resource
    private ChapterService chapterService;


    /**
     * 列表查询
     * @param chapterPageDto
     * @return
     *
     *
     *
     *         //前端传递数据，page当前页码
     *         page: page,
     *         //每页的多少数据，vue语法，获取子组件的值。
     *         size: _this.$refs.pagination.size,
     *         //对应课程的id
     *         courseId: _this.course.id
     *
     *
     */
    @PostMapping("/list")
    //前端请求的的是课程的id
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto) {
        LOG.info("PageDto:{}", chapterPageDto);
        //创建一个对象，默认设置请求成功。
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程ID");
        chapterService.list(chapterPageDto);
        //把查询到的结果放进content中
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }

    /**
     *保存id，id有值的时候更新，没有值的时候增加
     * @param chapterDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("chapterDto:{}", chapterDto);
        //后端校验填入信息
        ValidatorUtil.require(chapterDto.getName(),"课程");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);

        return responseDto;
    }

    /**
     *删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    //路径映射
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("id:{}", id);
        chapterService.delete(id);
        return responseDto;
    }
}

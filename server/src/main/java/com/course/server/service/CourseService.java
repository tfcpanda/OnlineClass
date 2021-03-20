package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {
    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;
    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
//        for (int i = 0, l = courseList.size(); i < l; i++) {
//            Course course = courseList.get(i);
//            CourseDto courseDto = new CourseDto();
//            BeanUtils.copyProperties(course, courseDto);
//            courseDtoList.add(courseDto);
//        }
        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setList(courseDtoList);

    }

    /**
     *保存
     * @param courseDto
     */
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(courseDto.getId())) {
            this.insert(course);
        } else {
            this.update(course);
        }
        //批量保存课程分类
        courseCategoryService.saveBatch(courseDto.getId(),courseDto.getCategorys());
    }


    // 批量保存课程分类



    /**
     * 新增
     * @param course
     */
    public void insert(Course course) {
            Date now = new Date();
                    course.setCreatedAt(now);
                    course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());

        courseMapper.insert(course);

    }


    /**
     * 更新
     * @param course
     */
    private void update(Course course) {
                    course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);

    }

    /**
     * 更新课程时长
     * @param courseId
     * @return
     */
    public void updateTime(String courseId) {
        LOG.info("更新课程时长：{}", courseId);
        myCourseMapper.updateTime(courseId);
    }


}

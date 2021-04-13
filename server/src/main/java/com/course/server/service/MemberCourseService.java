package com.course.server.service;

import com.course.server.domain.MemberCourse;
import com.course.server.domain.MemberCourseExample;
import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.MemberCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

    /**
     * 列表查询
     *
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        //pagehelper插件，getpage第几页，pagesize每页的条数。SQl语句加了一个LIMIT，控制查询的条数。
        //pagehelper遇到的第一个sql语句，会被加上LIMIT。
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberCourseExample memberCourseExample = new MemberCourseExample();
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
        PageInfo<MemberCourse> pageInfo = new PageInfo<>(memberCourseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberCourseDto> memberCourseDtoList = CopyUtil.copyList(memberCourseList, MemberCourseDto.class);
        pageDto.setList(memberCourseDtoList);

    }

    /**
     * 保存
     *
     * @param memberCourseDto
     */
    public void save(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
        if (StringUtils.isEmpty(memberCourseDto.getId())) {
            this.insert(memberCourse);
        } else {
            this.update(memberCourse);
        }

    }

    /**
     * 新增
     *
     * @param memberCourse
     */
    private void insert(MemberCourse memberCourse) {
        Date now = new Date();

        memberCourse.setId(UuidUtil.getShortUuid());
        memberCourse.setAt(now);

        memberCourseMapper.insert(memberCourse);

    }


    /**
     * 更新
     *
     * @param memberCourse
     */
    private void update(MemberCourse memberCourse) {
        memberCourseMapper.updateByPrimaryKey(memberCourse);

    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void delete(String id) {
        memberCourseMapper.deleteByPrimaryKey(id);

    }

    /**
     * 报名，先判断是否已报名
     * @param memberCourseDto
     */
    public MemberCourseDto enroll(MemberCourseDto memberCourseDto) {
        //
        MemberCourse memberCourseDb = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        //如果数据库中为空的
        if (memberCourseDb == null) {
            //如果就把Dto层复制到实体层
            MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
            //然后全部添加进去。
            this.insert(memberCourse);
            //实体层的数据给Dto层。
            return CopyUtil.copy(memberCourse, MemberCourseDto.class);
        } else {
            // 如果已经报名，则直接返回已报名的信息
            return CopyUtil.copy(memberCourseDb, MemberCourseDto.class);
        }
    }

    /**
     * 根据memberId和courseId查询记录
     */
    public MemberCourse select(String memberId, String courseId) {
        //sql查询
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria()
                //courseid等于courseId，
                .andCourseIdEqualTo(courseId)
                //memberid等于memberId的值
                .andMemberIdEqualTo(memberId);
        //先查询是否有这个id
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(example);
        //如果这个是空的就返回null
        if (CollectionUtils.isEmpty(memberCourseList)) {
            return null;
        } else {
            //然后得到course中的数据。
            return memberCourseList.get(0);
        }
    }

    /**
     * 获取报名信息
     */
    public MemberCourseDto getEnroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        return CopyUtil.copy(memberCourse, MemberCourseDto.class);
    }

}

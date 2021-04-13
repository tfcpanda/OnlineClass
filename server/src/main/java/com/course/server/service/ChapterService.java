package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 列表查询
     * @param chapterPageDto，继承了PageDto类，继承了里面的属性和方法。
     *  参数：page：当前页码，前端给的
     *       size:每页条数，前端给的
     *       total：总条数，后端查询得到
     *       List<>：查询到一个list泛型，里面放chapter类型,大章的名字。
     *       courseId外键：前端查询得到。
     *
     *                      传进来的参数。
     *         //前端传递数据，page当前页码
     *         page: page,
     *         //每页的多少数据，vue语法，获取子组件的值。
     *         size: _this.$refs.pagination.size,
     *         //对应课程的id
     *         courseId: _this.course.id
     *
     */
    public void list(ChapterPageDto chapterPageDto) {
        //分页属性，
        //pagehelper插件，getpage第几页，pagesize每页的条数。SQl语句加了一个LIMIT，控制查询的条数。
        //pagehelper遇到的第一个sql语句，会被加上LIMIT。
        PageHelper.startPage(chapterPageDto.getPage(), chapterPageDto.getSize());
        //mybatis的数据库查询
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        //如果chapterPageDto的CourseId的值不为空，
        if (!StringUtils.isEmpty(chapterPageDto.getCourseId())){
            //查询chapter中有courseId的值
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }

        //得到大章的数据，Chapter是实体里面的数据。运行这个实例。
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        //把大章的数据放入pageInfo中，PageInfo是pagehelper中的方法。
        /*
        *private List<T> list; //对象记录结果集
        *private int total = 0; // 总记录数
        *private int pageSize = 20; // 每页显示记录数
        *private int pages = 1; // 总页数
        *private int pageNum = 1; // 当前页
        *private boolean isFirstPage=false;        //是否为第一页
        *private boolean isLastPage=false;         //是否为最后一页
        *private boolean hasPreviousPage=false;   //是否有前一页
        *private boolean hasNextPage=false;       //是否有下一页
        */
        //把所有的查询结果都交给了，pageInfo
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        //前端的到数据，然后set总行数。
        chapterPageDto.setTotal(pageInfo.getTotal());
        //从数据库实体拷贝到传输层Dto实体里面，copy整个list
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        //set主要内容。
        chapterPageDto.setList(chapterDtoList);

    }

    /**
     *保存
     * @param chapterDto
     */
    public void save(ChapterDto chapterDto) {
        //把Dto的值，给chapter，用SQL把数据存进实体类中。
        //对方赋值，用的是copyProperties方法。
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if (StringUtils.isEmpty(chapterDto.getId())) {
            this.insert(chapter);
        } else {
            this.update(chapter);
        }

    }

    /**
     * 插入
     * @param chapter
     */
    private void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);

    }

    /**
     * 更新
     * @param chapter
     */
    private void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }


    /**
     * 查询某一课程下的所有章
     */
    public List<ChapterDto> listByCourse(String courseId) {
        //MyBatis查询
        ChapterExample example = new ChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<Chapter> chapterList = chapterMapper.selectByExample(example);
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        return chapterDtoList;
    }

}

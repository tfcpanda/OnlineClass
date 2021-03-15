package com.course.server.mapper;

import com.course.server.domain.chapter;
import com.course.server.domain.chapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface chapterMapper {
    long countByExample(chapterExample example);

    int deleteByExample(chapterExample example);

    int deleteByPrimaryKey(String id);

    int insert(chapter record);

    int insertSelective(chapter record);

    List<chapter> selectByExample(chapterExample example);

    chapter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") chapter record, @Param("example") chapterExample example);

    int updateByExample(@Param("record") chapter record, @Param("example") chapterExample example);

    int updateByPrimaryKeySelective(chapter record);

    int updateByPrimaryKey(chapter record);
}
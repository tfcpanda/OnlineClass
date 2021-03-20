package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * @author 田付成
 * @date 2021/3/20 12:59
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}

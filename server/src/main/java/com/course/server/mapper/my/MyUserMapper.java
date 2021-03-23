package com.course.server.mapper.my;

import com.course.server.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/3/24 0:09
 */
public interface MyUserMapper {
    List<ResourceDto> findResources(@Param("userId") String userId);
}

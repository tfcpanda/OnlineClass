package com.course.server.service;

import com.course.server.domain.Resource;
import com.course.server.domain.ResourceExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResourceDto;
import com.course.server.mapper.ResourceMapper;
import com.course.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<ResourceDto> resourceDtoList = new ArrayList<ResourceDto>();
//        for (int i = 0, l = resourceList.size(); i < l; i++) {
//            Resource resource = resourceList.get(i);
//            ResourceDto resourceDto = new ResourceDto();
//            BeanUtils.copyProperties(resource, resourceDto);
//            resourceDtoList.add(resourceDto);
//        }
        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resourceList, ResourceDto.class);
        pageDto.setList(resourceDtoList);

    }

    /**
     *保存
     * @param resourceDto
     */
    public void save(ResourceDto resourceDto) {
        Resource resource = CopyUtil.copy(resourceDto, Resource.class);
        if (StringUtils.isEmpty(resourceDto.getId())) {
            this.insert(resource);
        } else {
            this.update(resource);
        }

    }

    /**
     * 新增
     * @param resource
     */
    private void insert(Resource resource) {

        resource.setId("1");

        resourceMapper.insert(resource);

    }


    /**
     * 更新
     * @param resource
     */
    private void update(Resource resource) {
        resourceMapper.updateByPrimaryKey(resource);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);

    }


}

package com.course.server.service;

import com.course.server.domain.Role;
import com.course.server.domain.RoleExample;
import com.course.server.dto.RoleDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
//        for (int i = 0, l = roleList.size(); i < l; i++) {
//            Role role = roleList.get(i);
//            RoleDto roleDto = new RoleDto();
//            BeanUtils.copyProperties(role, roleDto);
//            roleDtoList.add(roleDto);
//        }
        List<RoleDto> roleDtoList = CopyUtil.copyList(roleList, RoleDto.class);
        pageDto.setList(roleDtoList);

    }

    /**
     *保存
     * @param roleDto
     */
    public void save(RoleDto roleDto) {
        Role role = CopyUtil.copy(roleDto, Role.class);
        if (StringUtils.isEmpty(roleDto.getId())) {
            this.insert(role);
        } else {
            this.update(role);
        }

    }

    /**
     * 新增
     * @param role
     */
    private void insert(Role role) {

        role.setId(UuidUtil.getShortUuid());

        roleMapper.insert(role);

    }


    /**
     * 更新
     * @param role
     */
    private void update(Role role) {
        roleMapper.updateByPrimaryKey(role);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);

    }


}

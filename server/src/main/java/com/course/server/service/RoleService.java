package com.course.server.service;

import com.course.server.domain.*;
import com.course.server.dto.PageDto;
import com.course.server.dto.RoleDto;
import com.course.server.mapper.RoleMapper;
import com.course.server.mapper.RoleResourceMapper;
import com.course.server.mapper.RoleUserMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleResourceMapper roleResourceMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

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

    /**
     * 按角色保存资源
     */
    @Transactional
    public void saveResource(RoleDto roleDto) {
        //传过来角色的id
        String roleId = roleDto.getId();
        //传过来的资源id
        List<String> resourceIds = roleDto.getResourceIds();
        // 清空库中所有的当前角色下的记录
        RoleResourceExample example = new RoleResourceExample();
        //查找角色roleid下面的值
        example.createCriteria().andRoleIdEqualTo(roleId);
        //先清空后增加。保持准确性
        roleResourceMapper.deleteByExample(example);

        // 保存角色资源，把id有的资源一条条的存进数据库中。
        for (int i = 0; i < resourceIds.size(); i++) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(UuidUtil.getShortUuid());
            //一个角色对应很多资源
            roleResource.setRoleId(roleId);
            //循环全部放进资源表里面
            roleResource.setResourceId(resourceIds.get(i));
            //添加进去
            roleResourceMapper.insert(roleResource);
        }
    }

    /**
     * 按角色加载资源
     * @param roleId
     */
    public List<String> listResource(String roleId) {
        RoleResourceExample example = new RoleResourceExample();
        //按照角色id查询。
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(example);
        //查找在次角色id下的所有id值
        //变成一个String列
        List<String> resourceIdList = new ArrayList<>();
        for (int i = 0, l = roleResourceList.size(); i < l; i++) {
            //把全部的该角色id下的所有资源的值给前端。
            resourceIdList.add(roleResourceList.get(i).getResourceId());
        }
        return resourceIdList;
    }


    /**
     * 按角色保存用户
     */

    public void saveUser(RoleDto roleDto) {
        //得到角色的id值
        String roleId = roleDto.getId();
        //得到用户id值，放进list中，列表中
        List<String> userIdList = roleDto.getUserIds();
        // 清空库中所有的当前角色下的记录
        RoleUserExample example = new RoleUserExample();
        //角色的id值和传进来的id值相同的，都删除
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleUserMapper.deleteByExample(example);

        // 保存角色用户，循环赋值
        for (int i = 0; i < userIdList.size(); i++) {
            RoleUser roleUser = new RoleUser();
            roleUser.setId(UuidUtil.getShortUuid());
            roleUser.setRoleId(roleId);
            roleUser.setUserId(userIdList.get(i));
            roleUserMapper.insert(roleUser);
        }
    }

    /**
     * 按角色加载用户
     * @param roleId
     */
    public List<String> listUser(String roleId) {
        RoleUserExample example = new RoleUserExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleUser> roleUserList = roleUserMapper.selectByExample(example);
        List<String> userIdList = new ArrayList<>();
        for (int i = 0, l = roleUserList.size(); i < l; i++) {
            userIdList.add(roleUserList.get(i).getUserId());
        }
        return userIdList;
    }
}

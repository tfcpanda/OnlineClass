package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.domain.UserExample;
import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.UserMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<UserDto> userDtoList = new ArrayList<UserDto>();
//        for (int i = 0, l = userList.size(); i < l; i++) {
//            User user = userList.get(i);
//            UserDto userDto = new UserDto();
//            BeanUtils.copyProperties(user, userDto);
//            userDtoList.add(userDto);
//        }
        List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
        pageDto.setList(userDtoList);

    }

    /**
     *保存
     * @param userDto
     */
    public void save(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(userDto.getId())) {
            this.insert(user);
        } else {
            this.update(user);
        }

    }

    /**
     * 新增
     * @param user
     */
    private void insert(User user) {

        user.setId(UuidUtil.getShortUuid());

        userMapper.insert(user);

    }


    /**
     * 更新
     * @param user
     */
    private void update(User user) {
        userMapper.updateByPrimaryKey(user);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);

    }


}

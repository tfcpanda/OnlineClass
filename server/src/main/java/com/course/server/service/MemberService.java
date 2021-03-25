package com.course.server.service;

import com.course.server.domain.Member;
import com.course.server.domain.MemberExample;
import com.course.server.dto.MemberDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.MemberMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    /**
     * 列表查询
     *
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberExample memberExample = new MemberExample();
        List<Member> memberList = memberMapper.selectByExample(memberExample);
        PageInfo<Member> pageInfo = new PageInfo<>(memberList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<MemberDto> memberDtoList = new ArrayList<MemberDto>();
//        for (int i = 0, l = memberList.size(); i < l; i++) {
//            Member member = memberList.get(i);
//            MemberDto memberDto = new MemberDto();
//            BeanUtils.copyProperties(member, memberDto);
//            memberDtoList.add(memberDto);
//        }
        List<MemberDto> memberDtoList = CopyUtil.copyList(memberList, MemberDto.class);
        pageDto.setList(memberDtoList);

    }

    /**
     * 保存
     *
     * @param memberDto
     */
    public void save(MemberDto memberDto) {
        Member member = CopyUtil.copy(memberDto, Member.class);
        if (StringUtils.isEmpty(memberDto.getId())) {
            this.insert(member);
        } else {
            this.update(member);
        }

    }

    /**
     * 新增
     *
     * @param member
     */
    private void insert(Member member) {
        Date now = new Date();

        member.setId(UuidUtil.getShortUuid());
        member.setRegisterTime(now);

        memberMapper.insert(member);

    }


    /**
     * 更新
     *
     * @param member
     */
    private void update(Member member) {
        memberMapper.updateByPrimaryKey(member);

    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void delete(String id) {
        memberMapper.deleteByPrimaryKey(id);

    }


}

package com.course.server.service;

import com.course.server.domain.Sms;
import com.course.server.domain.SmsExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.SmsDto;
import com.course.server.mapper.SmsMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsService {

    @Resource
    private SmsMapper smsMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SmsExample smsExample = new SmsExample();
        List<Sms> smsList = smsMapper.selectByExample(smsExample);
        PageInfo<Sms> pageInfo = new PageInfo<>(smsList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<SmsDto> smsDtoList = new ArrayList<SmsDto>();
//        for (int i = 0, l = smsList.size(); i < l; i++) {
//            Sms sms = smsList.get(i);
//            SmsDto smsDto = new SmsDto();
//            BeanUtils.copyProperties(sms, smsDto);
//            smsDtoList.add(smsDto);
//        }
        List<SmsDto> smsDtoList = CopyUtil.copyList(smsList, SmsDto.class);
        pageDto.setList(smsDtoList);

    }

    /**
     *保存
     * @param smsDto
     */
    public void save(SmsDto smsDto) {
        Sms sms = CopyUtil.copy(smsDto, Sms.class);
        if (StringUtils.isEmpty(smsDto.getId())) {
            this.insert(sms);
        } else {
            this.update(sms);
        }

    }

    /**
     * 新增
     * @param sms
     */
    private void insert(Sms sms) {

        sms.setId(UuidUtil.getShortUuid());

        smsMapper.insert(sms);

    }


    /**
     * 更新
     * @param sms
     */
    private void update(Sms sms) {
        smsMapper.updateByPrimaryKey(sms);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        smsMapper.deleteByPrimaryKey(id);

    }


}

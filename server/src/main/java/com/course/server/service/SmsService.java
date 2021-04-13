package com.course.server.service;

import com.course.server.domain.Sms;
import com.course.server.domain.SmsExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.SmsDto;
import com.course.server.ecxeption.BusinessException;
import com.course.server.ecxeption.BusinessExceptionCode;
import com.course.server.enums.SmsStatusEnum;
import com.course.server.mapper.SmsMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SmsService {

    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);


    @Resource
    private SmsMapper smsMapper;

    /**
     * 列表查询
     *
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
     * 保存
     *
     * @param smsDto
     */
    public void save(SmsDto smsDto) {
        //把Dto的数据复制到实体里面。
        Sms sms = CopyUtil.copy(smsDto, Sms.class);
        if (StringUtils.isEmpty(smsDto.getId())) {
            this.insert(sms);
        } else {
            this.update(sms);
        }

    }

    /**
     * 新增
     *
     * @param sms
     */
    private void insert(Sms sms) {

        sms.setId(UuidUtil.getShortUuid());

        smsMapper.insert(sms);

    }


    /**
     * 更新
     *
     * @param sms
     */
    private void update(Sms sms) {
        smsMapper.updateByPrimaryKey(sms);

    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void delete(String id) {
        smsMapper.deleteByPrimaryKey(id);

    }

    /**
     * 发送短信验证码
     * 同手机号同操作1分钟内不能重复发送短信
     *
     * @param smsDto
     */
    public void sendCode(SmsDto smsDto) {
        //复杂sql查询
        SmsExample example = new SmsExample();
        SmsExample.Criteria criteria = example.createCriteria();
        // 查找1分钟内有没有同手机号同操作发送记录且没被用过
        criteria.andMobileEqualTo(smsDto.getMobile())
                .andUseEqualTo(smsDto.getUse())
                .andStatusEqualTo(SmsStatusEnum.NOT_USED.getCode())
                .andAtGreaterThan(new Date(new Date().getTime() - 1 * 60 * 1000));
        //创建一个列
        List<Sms> smsList = smsMapper.selectByExample(example);
        //如果列等于空就代表1分钟内有没有同手机号同操作发送记录且没被用过
        if (smsList == null || smsList.size() == 0) {
            //生成验证码
            saveAndSend(smsDto);
        } else {
            //如果发送频繁就不发送
            LOG.warn("短信请求过于频繁, {}", smsDto.getMobile());
            throw new BusinessException(BusinessExceptionCode.MOBILE_CODE_TOO_FREQUENT);
        }
    }

    /**
     * 保存并发送短信验证码
     *
     * @param smsDto
     */
    private void saveAndSend(SmsDto smsDto) {
        // 生成6位数字
        String code = String.valueOf((int) (((Math.random() * 9) + 1) * 100000));
        //创建时间
        smsDto.setAt(new Date());
        //设置成没有使用过，返回code
        smsDto.setStatus(SmsStatusEnum.NOT_USED.getCode());
        //把验证码保存。
        smsDto.setCode(code);
        //在调用save方法，把这条数据保存到数据库
        this.save(smsDto);


    }

    /**
     * 验证码5分钟内有效，且操作类型要一致
     *
     * @param smsDto
     */
    public void validCode(SmsDto smsDto) {
        SmsExample example = new SmsExample();
        SmsExample.Criteria criteria = example.createCriteria();
        // 查找5分钟内同手机号同操作发送记录
        criteria.andMobileEqualTo(smsDto.getMobile()).andUseEqualTo(smsDto.getUse()).andAtGreaterThan(new Date(new Date().getTime() - 1 * 60 * 1000));
        //查询他的数据库
        List<Sms> smsList = smsMapper.selectByExample(example);
        //查询结果不为空或者值大与0
        if (smsList != null && smsList.size() > 0) {
            //得到他的数据的code
            Sms smsDb = smsList.get(0);
            //如果数据库的code和传过来的code不相同
            if (!smsDb.getCode().equals(smsDto.getCode())) {
                //验证不正确
                LOG.warn("短信验证码不正确");
                throw new BusinessException(BusinessExceptionCode.MOBILE_CODE_ERROR);
            } else {
                //如果相同就把他改成用过了。
                smsDb.setStatus(SmsStatusEnum.USED.getCode());
                //并把它用过了的消息更新到数据库中。
                smsMapper.updateByPrimaryKey(smsDb);
            }
            //如果为空就直接返回没有值
        } else {
            LOG.warn("短信验证码不存在或已过期，请重新发送短信");
            throw new BusinessException(BusinessExceptionCode.MOBILE_CODE_EXPIRED);
        }
    }


}

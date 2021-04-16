package com.course.business.controller.web;

import com.course.server.dto.*;
import com.course.server.ecxeption.BusinessException;
import com.course.server.enums.SmsUseEnum;
import com.course.server.service.MemberService;
import com.course.server.service.SmsService;
import com.course.server.util.UuidUtil;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("webMemberController")
@RequestMapping("/web/member")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    public static final String BUSINESS_NAME = "会员";

    @Resource
    private MemberService memberService;


    @Resource
    private SmsService smsService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/register")
    public ResponseDto register(@RequestBody MemberDto memberDto) {
        // 保存校验
        ValidatorUtil.require(memberDto.getMobile(), "手机号");
        ValidatorUtil.length(memberDto.getMobile(), "手机号", 11, 11);
        ValidatorUtil.require(memberDto.getPassword(), "密码");
        ValidatorUtil.length(memberDto.getName(), "昵称", 1, 50);
        ValidatorUtil.length(memberDto.getPhoto(), "头像url", 1, 200);
        // 密码加密
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));
        // 校验短信验证码
        SmsDto smsDto = new SmsDto();
        //得到这个人的手机号码
        smsDto.setMobile(memberDto.getMobile());
        //得到他的验证码
        smsDto.setCode(memberDto.getSmsCode());
        //把验证码改成已经过
        smsDto.setUse(SmsUseEnum.REGISTER.getCode());
        //校验他的验证码
        smsService.validCode(smsDto);
        LOG.info("短信验证码校验通过");

        ResponseDto responseDto = new ResponseDto();
        memberService.save(memberDto);
        responseDto.setContent(memberDto);
        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody MemberDto memberDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        //Dto存密码的时候加密
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
        String imageCode = (String) request.getSession().getAttribute(memberDto.getImageCodeToken());

        //如果验证码是空的，就显示过期
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        //把验证码全部转换成小写对比，如果不相同就提示验证码不正确。
        if (!imageCode.toLowerCase().equals(memberDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 其他情况就实现验证码正确。验证通过后，移除验证码
            request.getSession().removeAttribute(memberDto.getImageCodeToken());

        }
        //跳转到
        LoginMemberDto loginMemberDto = memberService.login(memberDto);
        //生成一个token
        String token = UuidUtil.getShortUuid();
        //保存token
        loginMemberDto.setToken(token);
        //保存为登录账号
        request.getSession().setAttribute(Constants.LOGIN_USER,loginMemberDto);
        //返回数据放到Dto中。
        responseDto.setContent(loginMemberDto);
        return responseDto;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();
        request.getSession().removeAttribute(Constants.LOGIN_USER);
        return responseDto;
    }


    /**
     * 校验手机号是否存在
     * 存在则success=true，不存在则success=false
     * 传递mobile手机号
     */
    @GetMapping(value = "/is-mobile-exist/{mobile}")
    public ResponseDto isMobileExist(@PathVariable(value = "mobile") String mobile) throws BusinessException {
        //前端得到手机号
        LOG.info("查询手机号是否存在开始");
        ResponseDto responseDto = new ResponseDto();
        //根据手机号查询member
        MemberDto memberDto = memberService.findByMobile(mobile);
        if (memberDto == null) {
            //如果没注册过写false
            responseDto.setSuccess(false);
        } else {
            //如果注册过写true
            responseDto.setSuccess(true);
        }
        return responseDto;
    }



    @PostMapping("/reset-password")
    public ResponseDto resetPassword(@RequestBody MemberDto memberDto) throws BusinessException {
        LOG.info("会员密码重置开始:");
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));
        ResponseDto<MemberDto> responseDto = new ResponseDto();
        // 校验短信验证码
        SmsDto smsDto = new SmsDto();
        smsDto.setMobile(memberDto.getMobile());
        smsDto.setCode(memberDto.getSmsCode());
        smsDto.setUse(SmsUseEnum.FORGET.getCode());
        smsService.validCode(smsDto);
        LOG.info("短信验证码校验通过");
        // 重置密码
        memberService.resetPassword(memberDto);
        return responseDto;
    }


}

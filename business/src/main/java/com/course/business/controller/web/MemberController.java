package com.course.business.controller.web;

import com.course.server.dto.Constants;
import com.course.server.dto.LoginMemberDto;
import com.course.server.dto.MemberDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberService;
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
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
       // String imageCode = (String) redisTemplate.opsForValue().get(memberDto.getImageCodeToken());
        String imageCode = (String) request.getSession().getAttribute(memberDto.getImageCodeToken());

        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(memberDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
            //redisTemplate.delete(memberDto.getImageCodeToken());
            request.getSession().removeAttribute(memberDto.getImageCodeToken());

        }

        LoginMemberDto loginMemberDto = memberService.login(memberDto);
        String token = UuidUtil.getShortUuid();
        loginMemberDto.setToken(token);
        request.getSession().setAttribute(Constants.LOGIN_USER,loginMemberDto);

        //redisTemplate.opsForValue().set(token, JSON.toJSONString(loginMemberDto), 3600, TimeUnit.SECONDS);
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


}

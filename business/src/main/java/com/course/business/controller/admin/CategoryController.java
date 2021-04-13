package com.course.business.controller.admin;

import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *   2.@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，
 *   返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，
 *   就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 *
 *
 *   RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    //增加日志语句
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";
    @Resource
    private CategoryService categoryService;

    /**
     * 列表查询
     * @param pageDto
     * @return
     *
     * 1.@RequestBody接受的是一个json格式的字符串，一定是一个json对象字符串。
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto:{}", pageDto);
        //ResponseDto是自定义的返回类型，用来测试接口返回正确还是错误，可以自定义返回码。
        ResponseDto responseDto = new ResponseDto();
        //
        categoryService.list(pageDto);
        //可以自定义返回类型，content字段是一个泛型
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 列表查询
     * 查询全部
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    /**
     *保存id，id有值的时候更新，没有值的时候增加
     * @param categoryDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {
        ResponseDto responseDto = new ResponseDto();
        //后端校验填入信息
        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    /**
     *删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("id:{}", id);
        categoryService.delete(id);
        return responseDto;
    }
}

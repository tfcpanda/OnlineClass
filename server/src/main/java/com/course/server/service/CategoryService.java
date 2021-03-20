package com.course.server.service;

import com.course.server.domain.Category;
import com.course.server.domain.CategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
//        for (int i = 0, l = categoryList.size(); i < l; i++) {
//            Category category = categoryList.get(i);
//            CategoryDto categoryDto = new CategoryDto();
//            BeanUtils.copyProperties(category, categoryDto);
//            categoryDtoList.add(categoryDto);
//        }
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setList(categoryDtoList);

    }

    /**
     * 列表查询ALL
     * @param 返回全部
     */
    public  List<CategoryDto> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        return categoryDtoList;

    }

    /**
     *保存
     * @param categoryDto
     */
    public void save(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if (StringUtils.isEmpty(categoryDto.getId())) {
            this.insert(category);
        } else {
            this.update(category);
        }

    }

    /**
     * 新增
     * @param category
     */
    private void insert(Category category) {

        category.setId(UuidUtil.getShortUuid());

        categoryMapper.insert(category);

    }


    /**
     * 更新
     * @param category
     */
    private void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        categoryMapper.deleteByPrimaryKey(id);

    }


}

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
import org.springframework.transaction.annotation.Transactional;
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
        //分页，
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        //mybatis查询
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //分页信息放到pageInfo
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        //得到放置总页数。
        pageDto.setTotal(pageInfo.getTotal());
        //把查询到的数据复制给Dto层。
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setList(categoryDtoList);

    }

    /**
     * 列表查询ALL
     * @param
     */
    public  List<CategoryDto> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //复制给Dto层。
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
    @Transactional
    public void delete(String id) {
        deleteChildren(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除子分类
     * @param id
     */
    public void deleteChildren(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if ("00000000".equals(category.getParent())) {
            // 如果是一级分类，需要删除其下的二级分类
            CategoryExample example = new CategoryExample();
            //查询Parent等于categoryid值的
            example.createCriteria().andParentEqualTo(category.getId());
            categoryMapper.deleteByExample(example);
        }
    }

}

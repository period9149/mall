package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.Category;
import com.example.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品类别表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


//    @GetMapping("/index")
//    public Result index() {
//        Category category = categoryService.getById(1L);
//        return Result.succ(200, "操作成功", category);
//    }

    @GetMapping("/categories")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 10);
        IPage pageData = categoryService.page(page, new QueryWrapper<Category>());
        return Result.succ(pageData);
    }

    @GetMapping("/categories/all")
    public Result listAll(){
        // categoryService.getMap(new QueryWrapper<Category>());
        return Result.succ(categoryService.list());
    }

    @GetMapping("/categories/{categoryId}")
    public Result detail(@PathVariable(name = "categoryId") Long categoryId){
        Category category = categoryService.getById(categoryId);
        Assert.notNull(category, "该商品类别被删除了");
        return Result.succ(category);
    }

    @PostMapping("/categories/edit")
    public Result edit(@Validated @RequestBody Category category) {

        Category temp = null;

        if(category.getCategoryId() != null){
            temp = categoryService.getById((category.getCategoryId()));
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Category();
        }

        BeanUtils.copyProperties(category, temp);
        categoryService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    @PostMapping("/categories/delete")
    public Result delete(@RequestBody Category category){
        categoryService.removeById(category.getCategoryId());
        return Result.succ(null);
    }

    @GetMapping("/searchCategory")
    public Result search(@RequestParam(defaultValue = "1") Integer currentPage, String info){
        Page page = new Page(currentPage, 100);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.like("category_name", info);
        IPage pageData = categoryService.page(page, queryWrapper);
        return Result.succ(pageData);
    }
}

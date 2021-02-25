package com.example.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController

public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 10);
        IPage pageData = productService.page(page, new QueryWrapper<Product>());
        return Result.succ(pageData);
    }

    @GetMapping("/products/{productId}")
    public Result detail(@PathVariable(name = "productId") Long productId){
        Product product = productService.getById(productId);
        Assert.notNull(product, "该商品被删除了");
        return Result.succ(product);
    }

    @PostMapping("/products/edit")
    public Result edit(@Validated @RequestBody Product product) {

        Product temp = null;

        if(product.getProductId() != null){
            temp = productService.getById((product.getProductId()));
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Product();
        }

        BeanUtils.copyProperties(product, temp);
        productService.saveOrUpdate(temp);

        return Result.succ(null);
    }

}

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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /* 获取所有商品 */
    @GetMapping("/products")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 10);
        IPage pageData = productService.page(page, new QueryWrapper<Product>());
        return Result.succ(pageData);
    }

    /* 根据id找商品 */
    @GetMapping("/products/{productId}")
    public Result detail(@PathVariable(name = "productId") Long productId){
        Product product = productService.getById(productId);
        Assert.notNull(product, "该商品被删除了");
        return Result.succ(product);
    }

    /* 商品添加、修改 */
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

    /* 商品删除 */
    @PostMapping("/products/delete")
    public Result delete(@RequestBody Product product){
        productService.removeById(product.getProductId());
        return Result.succ(null);
    }

    /* 上传接口 */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }

        // 获取文件存储路径（绝对路径）
        String path = "/Users/period9149/Desktop/Projects/mall/uploads";

        // 设置文件名
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String fileName = formatter.format(date) + "_"+ file.getOriginalFilename();

        // 创建文件实例
        File filePath = new File(path, fileName);

        // 写入文件
        file.transferTo(filePath);
        return fileName;
    }

    /* 商品搜索 */
    @GetMapping("/searchProduct")
    public Result search(@RequestParam(defaultValue = "1") Integer currentPage, String info){
        Page page = new Page(currentPage, 100);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>();
        queryWrapper.like("product_title", info)
                .or().
                like("product_id", info)
                .or()
                .like("product_details", info);
        IPage pageData = productService.page(page, queryWrapper);
        return Result.succ(pageData);
    }

    /* 根据分类找商品 */
    @GetMapping("/searchProductInCategory")
    public Result searchInCategory(String id){
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>();
        queryWrapper.like("product_category", id);
        List list = productService.listMaps(queryWrapper);
        return Result.succ(list);
    }
}

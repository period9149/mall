package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.Model;
import com.example.service.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品型号表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController

public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping("/models")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 10);
        IPage pageData = modelService.page(page, new QueryWrapper<Model>());
        return Result.succ(pageData);
    }

    @GetMapping("/models/{modelId}")
    public Result detail(@PathVariable(name = "modelId") Long productId){
        Model model = modelService.getById(productId);
        Assert.notNull(model, "该商品被删除了");
        return Result.succ(model);
    }

    @PostMapping("/models/edit")
    public Result edit(@Validated @RequestBody Model model) {

        Model temp = null;

        if(model.getModelId() != null){
            temp = modelService.getById((model.getModelId()));
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Model();
        }

        BeanUtils.copyProperties(model, temp);
        modelService.saveOrUpdate(temp);

        return Result.succ(null);
    }
}

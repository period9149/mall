package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.Order;
import com.example.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController

public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 10);
        IPage pageData = orderService.page(page, new QueryWrapper<Order>());
        return Result.succ(pageData);
    }

    @GetMapping("/orders/{orderId}")
    public Result detail(@PathVariable(name = "orderId") Long orderId){
        Order order = orderService.getById(orderId);
        Assert.notNull(order, "该订单被删除了");
        return Result.succ(order);
    }

    @PostMapping("/orders/edit")
    public Result edit(@Validated @RequestBody Order order) {

        Order temp = null;

        if(order.getOrderId() != null){
            temp = orderService.getById((order.getOrderId()));
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Order();
        }

        BeanUtils.copyProperties(order, temp);
        orderService.saveOrUpdate(temp);

        return Result.succ(null);
    }
}

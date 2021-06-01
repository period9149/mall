package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Cart;
import com.example.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 购物车表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/cart/add")
    public Result edit(@Validated @RequestBody Cart cart) {

        Cart temp = new Cart();

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.and(i -> i
                .eq("cart_product", cart.getCartProduct())
                .eq("cart_user", cart.getCartUser())
                .eq("cart_model", cart.getCartModel()));

        Cart tag = cartService.getOne(queryWrapper);

        if(tag != null){

            temp.setCartId(tag.getCartId());
            temp.setCartCount(tag.getCartCount() + cart.getCartCount());
            cartService.updateById(temp);

        }else{

            BeanUtils.copyProperties(cart, temp);
            cartService.saveOrUpdate(temp);

        }

        return Result.succ(null);

    }

    /* 根据用户找购物车 */
    @GetMapping("/searchCartsByUserId")
    public Result searchCartsByUserId(String userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.like("cart_user", userId);
        List list = cartService.listMaps(queryWrapper);
        return Result.succ(list);
    }

}

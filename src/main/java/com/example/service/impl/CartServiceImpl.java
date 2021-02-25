package com.example.service.impl;

import com.example.entity.Cart;
import com.example.mapper.CartMapper;
import com.example.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表
 服务实现类
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}

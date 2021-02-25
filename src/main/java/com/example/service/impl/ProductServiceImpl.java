package com.example.service.impl;

import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表
 服务实现类
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}

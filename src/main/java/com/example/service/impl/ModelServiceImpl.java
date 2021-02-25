package com.example.service.impl;

import com.example.entity.Model;
import com.example.mapper.ModelMapper;
import com.example.service.ModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品型号表
 服务实现类
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

}

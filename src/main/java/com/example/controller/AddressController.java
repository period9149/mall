package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.example.common.lang.Result;
import com.example.entity.Address;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/addresses/all")
    public Result listAll(){
        return Result.succ(addressService.list());
    }

    @GetMapping("/addresses/{addressId}")
    public Result detail(@PathVariable(name = "addressId") Long addressId){
        Address address = addressService.getById(addressId);
        Assert.notNull(address, "该商品被删除了");
        return Result.succ(address);
    }
}

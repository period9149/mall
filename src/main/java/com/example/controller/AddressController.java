package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Address;
import com.example.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /* 收货地址列表 */
    @GetMapping("/addresses/all")
    public Result listAll(){
        return Result.succ(addressService.list());
    }

    /* 根据id找收货地址 */
    @GetMapping("/addresses/{addressId}")
    public Result detail(@PathVariable(name = "addressId") Long addressId){
        Address address = addressService.getById(addressId);
        Assert.notNull(address, "该地址被删除了");
        return Result.succ(address);
    }

    /* 根据用户id找默认收货地址 */
    @GetMapping("/getDefaultAddress/{userId}")
    public Result getDefaultAddress(@PathVariable(name = "userId") Long userId){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.eq("address_user", userId).eq("address_isDefault", 1);
        List list = addressService.listMaps(queryWrapper);
        return Result.succ(list);
    }

    /* 地址添加、修改 */
    @PostMapping("/addresses/edit")
    public Result edit(@Validated @RequestBody Address address) {

        Address temp = null;

        if(address.getAddressId() != null){
            temp = addressService.getById(address.getAddressId());
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Address();
        }

        BeanUtils.copyProperties(address, temp);
        addressService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    /* 根据用户id找收货地址 */
    @GetMapping("/searchAddressByUserId")
    public Result searchAddressByUserId(String userId){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.like("address_user", userId);
        List list = addressService.listMaps(queryWrapper);
        return Result.succ(list);
    }

    /* 删除收货地址 */
    @PostMapping("/addresses/delete")
    public Result delete(@RequestBody Address address){
        addressService.removeById(address.getAddressId());
        return Result.succ(null);
    }
}

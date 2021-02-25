package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mall_address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "address_id", type = IdType.AUTO)
    private Long addressId;

    /**
     * 用户id
     */
    private Long addressUser;

    /**
     * 收货姓名
     */
    private String addressReceiver;

    /**
     * 收货地区（省）
     */
    private String addressProvince;

    /**
     * 收货地区（市）
     */
    private String addressCity;

    /**
     * 收货地区（区，县）
     */
    private String addressCountry;

    /**
     * 具体地址
     */
    private String addressAddress;

    /**
     * 收货电话
     */
    private String addressPhone;

    /**
     * 是否为默认地址
     */
    @TableField("address_isDefault")
    private Integer addressIsdefault;


}

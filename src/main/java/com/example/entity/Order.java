package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单表

 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mall_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 用户id
     */
    private Long orderUser;

    /**
     * 收货地址id
     */
    private Long orderAddress;

    /**
     * 订单状态（未付款0，未发货1，运输中2，已签收3）
     */
    private Integer orderStatus;

    /**
     * 所购商品id
     */
    private Long orderProduct;

    /**
     * 所购商品型号id
     */
    private Long orderModel;

    /**
     * 所购商品数
     */
    private Integer orderCount;

    /**
     * 实际付款
     */
    private String orderPay;

    /**
     * 快递单号
     */
    private String orderExpress;

}

package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 商品表

 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mall_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 商品类别ID
     */
    private Long productCategory;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 商品标题
     */
    @NotBlank(message = "标题不能为空")
    private String productTitle;

    /**
     * 商品售价
     */
    private String productPrice;

    /**
     * 商品成本
     */
    private String productCost;

    /**
     * 商品详情
     */
    private String productDetails;

    /**
     * 商品已售
     */
    private Integer productSold;


}

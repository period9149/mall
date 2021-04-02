package com.example.controller;


import com.example.common.lang.Result;
import com.example.entity.Swipe;
import com.example.service.SwipeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 轮播图表 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-03-25
 */
@RestController

public class SwipeController {

    @Autowired
    SwipeService swipeService;

    /* 轮播图列表 */
    @GetMapping("/swipes/all")
    public Result listAll(){
        return Result.succ(swipeService.list());
    }

    /* 轮播图删除 */
    @PostMapping("/swipes/delete")
    public Result delete(@RequestBody Swipe swipe){
        swipeService.removeById(swipe.getSwipeId());
        return Result.succ(null);
    }

    /* 轮播图添加、修改 */
    @PostMapping("/swipes/edit")
    public Result edit(@Validated @RequestBody Swipe swipe) {

        Swipe temp = null;

        if(swipe.getSwipeId() != null){
            temp = swipeService.getById((swipe.getSwipeId()));
            // Assert.isTrue(temp.getProductId().longValue() == ShiroUtil.getProfile().getUserId().longValue(), "niu");
        }else{
            temp = new Swipe();
        }

        BeanUtils.copyProperties(swipe, temp);
        swipeService.saveOrUpdate(temp);

        return Result.succ(null);
    }

}

package com.example.controller;


import com.example.common.lang.Result;
import com.example.service.SwipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}

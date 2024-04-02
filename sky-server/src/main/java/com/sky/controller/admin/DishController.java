/**
 * @Description TODO 新增菜品
 * @Classname DishController
 * @Date 2024/4/2 16:49
 * @Created by Mingkai Feng
 */
package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @ApiOperation("新增菜品")
    @PostMapping
    public Result addDishWithFlavor(@RequestBody DishDTO dishDTO) {
        dishService.addDishWithFlavor(dishDTO);
        return Result.success();
    }
}

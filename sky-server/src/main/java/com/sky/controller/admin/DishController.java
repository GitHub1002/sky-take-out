/**
 * @Description TODO 新增菜品
 * @Classname DishController
 * @Date 2024/4/2 16:49
 * @Created by Mingkai Feng
 */
package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * @author Mingkai Feng
     * @date 2024/4/4 18:39
     * @Description ToDo 新增菜品
     * @param dishDTO
     * @return Result
     */
    @ApiOperation("新增菜品")
    @PostMapping
    public Result addDishWithFlavor(@RequestBody DishDTO dishDTO) {
        dishService.addDishWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * @author Mingkai Feng
     * @date 2024/4/4 18:39
     * @Description ToDo 菜品分页查询
     * @param dishPageQueryDTO
     * @return Result<PageResult>
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page (DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageQuery (dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping()
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品，id为：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}

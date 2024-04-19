/**
 * @Description 套餐管理类
 * @Classname SetMealController
 * @Date 2024/4/9 11:53
 * @Created by Mingkai Feng
 */
package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.service.impl.SetMealServiceImpl;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "套餐管理")
@RequestMapping("/admin/setmeal")
@RestController
@Slf4j
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    @PostMapping()
    @ApiOperation("新增套餐")
    public Result save (@RequestBody SetmealDTO setMealDTO){
        setMealService.saveWithDish(setMealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    // 前端传入的是一个实体，因此用无注解方式接收，即不需要添加@RequestBody等注解，
    // 但注意这种无注解方式接收时，前端传入的参数名必须和实体类中的属性名一致，否则会报错，
    // 而且只能用于Get传输方式
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询参数：{}", setmealPageQueryDTO);
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping()
    public Result delete (@RequestParam List<Long> ids){
        log.info("套餐批量删除参数：{}", ids);
        setMealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id查询套餐，用于修改页面回显数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setMealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐
     *
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        setMealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        setMealService.startOrStop(status, id);
        return Result.success();
    }
}

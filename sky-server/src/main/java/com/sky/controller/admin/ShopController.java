/**
 * @Description 营业状态管理
 * @Classname ShopController
 * @Date 2024/4/19 15:09
 * @Created by Mingkai Feng
 */
package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "营业状态管理")
public class ShopController {
    public static final String SHOP_STATUS = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author Mingkai Feng
     * @date 2024/4/19 15:14
     * @Description 设置店铺营业状态
     * @param status
     * @return Result
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus (@PathVariable Integer status){
        log.info("设置营业状态: {}", status == 1 ? "营业中" : "打烊了");
        redisTemplate.opsForValue().set(SHOP_STATUS, status);
        return Result.success();
    }

    /**
     * @author Mingkai Feng
     * @date 2024/4/19 15:17
     * @Description 获取店铺营业状态
     * @return Result<Integer>
     */
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getStatus (){
        Integer status = (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
        log.info("获取营业状态: {}", status == 1 ? "营业中" : "打烊了");
        return Result.success(status);
    }
}

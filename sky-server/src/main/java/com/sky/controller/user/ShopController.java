/**
 * @Description 营业状态管理
 * @Classname ShopController
 * @Date 2024/4/19 15:09
 * @Created by Mingkai Feng
 */
package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "营业状态管理")
public class ShopController {
    public static final String SHOP_STATUS = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author Mingkai Feng
     * @date 2024/4/19 15:17
     * @Description 获取店铺营业状态
     * @return Result<Integer>
     */
    @PutMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus (){
        Integer status = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);
        log.info("获取营业状态: {}", status == 1 ? "营业中" : "打烊了");
        return Result.success(status);
    }
}

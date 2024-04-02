/**
 * @Description TODO
 * @Classname DishService
 * @Date 2024/4/2 16:54
 * @Created by Mingkai Feng
 */
package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.result.Result;

public interface DishService {

    public void addDishWithFlavor(DishDTO dishDTO);
}

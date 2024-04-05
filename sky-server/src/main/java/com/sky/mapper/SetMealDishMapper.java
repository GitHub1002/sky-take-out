/**
 * @Description TODO
 * @Classname SetMealDishMapper
 * @Date 2024/4/5 15:10
 * @Created by Mingkai Feng
 */
package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 15:16
     * @Description ToDo 根据菜品ID查询套餐ID
     * @param dishIds
     * @return List<Long>
     */
    // select setmeal_id from setmeal_dish where dish_id in (1,2,3,4)
    List<Long> getSetMealIdsByDishId(List<Long> dishIds);
}

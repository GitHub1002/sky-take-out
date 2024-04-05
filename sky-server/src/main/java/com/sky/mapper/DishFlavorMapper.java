/**
 * @Description TODO
 * @Classname DishFlavorMapper
 * @Date 2024/4/2 19:40
 * @Created by Mingkai Feng
 */
package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * @author Mingkai Feng
     * @date 2024/4/2 19:44
     * @Description ToDo
     * @param flavors 批量插入口味
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 16:31
     * @Description ToDo    根据主键删除对应的口味数据
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 16:39
     * @Description ToDo 根据主键批量删除对应的口味数据
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);
}

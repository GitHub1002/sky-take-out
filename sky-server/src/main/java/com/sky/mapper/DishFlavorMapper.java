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
}

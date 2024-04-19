/**
 * @Description TODO
 * @Classname SetMealDishMapper
 * @Date 2024/4/5 15:10
 * @Created by Mingkai Feng
 */
package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * @author Mingkai Feng
     * @date 2024/4/9 15:15
     * @Description ToDo   批量插入套餐_菜品信息
     * @param setmealDishes
     */
    @AutoFill(value = OperationType.INSERT)
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * @author Mingkai Feng
     * @date 2024/4/10 13:49
     * @Description 分页查询
     * @param setmealPageQueryDTO
     * @return Page<SetmealVO>
     */
    // select s.* from setmeal s left join category c
    // on s.category_id = c.id
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id查询套餐和菜品的关联关系
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}

package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.result.Result;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * @author Mingkai Feng
     * @date 2024/4/2 17:26
     * @Description ToDo 插入菜品信息
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * @author Mingkai Feng
     * @date 2024/4/4 18:53
     * @Description ToDo 菜品分页查询
     * @param dishPageQueryDTO
     * @return Page<DishVO>
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 16:31
     * @Description ToDo 根据主键删除菜品数据
     * @param id
     */
    @Delete("delete * from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 16:34
     * @Description ToDo    根据菜品id集合删除菜品
     * @param ids
     */
    void deleteByIds(List<Long> ids);
}

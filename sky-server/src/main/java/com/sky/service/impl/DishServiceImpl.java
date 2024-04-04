/**
 * @Description TODO
 * @Classname DishServiceImpl
 * @Date 2024/4/2 16:54
 * @Created by Mingkai Feng
 */
package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * @author Mingkai Feng
     * @date 2024/4/2 17:12
     * @Description ToDo 新增菜品和对应口味
     * @param dishDTO
     */
    @Override
    @Transactional  // 因为涉及到两张表的操作，所以需要开启事务
    public void addDishWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 向菜品插入 1 条数据
        dishMapper.insert(dish);
        // 获取 insert 语句生成的主键值
        Long dishId = dish.getId();

        // 获取并遍历 flavors 数组给口味表插入 dishId
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(flavor -> {
                flavor.setDishId(dishId);
            });
            // 向口味表插入 n 条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * @author Mingkai Feng
     * @date 2024/4/4 18:42
     * @Description ToDo    菜品分页查询
     * @param dishPageQueryDTO
     * @return PageResult
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        return pageResult;
    }
}

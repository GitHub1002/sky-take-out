/**
 * @Description
 * @Classname DishServiceImpl
 * @Date 2024/4/2 16:54
 * @Created by Mingkai Feng
 */
package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
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

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * @author Mingkai Feng
     * @date 2024/4/2 17:12
     * @Description 新增菜品和对应口味
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
     * @Description   菜品分页查询
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

    /**
     * @author Mingkai Feng
     * @date 2024/4/5 14:57
     * @Description   批量删除菜品
     * @param ids
     */
    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 判断当前菜品是否能够删除 -- 是否存在起售中的菜品？
        for (Long id: ids){
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 判断当前菜品是否能够删除 -- 是否被套餐关联？
        List<Long> setMealIdsByDishId = setMealDishMapper.getSetMealIdsByDishId(ids);
        if (setMealIdsByDishId != null && setMealIdsByDishId.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        // 删除菜品关联的口味数据
        /*for (Long id : ids) {
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteByDishId(id);
        }*/
        // 根据菜品id集合批量删除菜品数据
        dishMapper.deleteByIds(ids);
        // 根据菜品id批量删除关联的口味数据
        dishFlavorMapper.deleteByDishIds(ids);
    }

    /**
     * @author Mingkai Feng
     * @date 2024/4/7 10:51
     * @Description   根据菜品id查询菜品信息和对应的口味信息
     * @param id
     * @return DishVO
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        // 1. 查询菜品信息
        Dish dish = dishMapper.getById(id);
        // 2. 查询菜品对应的口味信息
        List<DishFlavor> flavors = dishFlavorMapper.getByDishId(id);
        // 3. 封装数据
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    /**
     * @Description 根据菜品id查询菜品信息和对应的口味信息
     * @author Mingkai Feng
     * @date 2024/4/7 14:14
     * @param dishDTO
     */
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        // 1. 更新菜品基本信息
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);
        // 2. 删除对应口味信息
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        // 3. 添加新的口味信息
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishDTO.getId());
            }
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * @author Mingkai Feng
     * @date 2024/4/9 15:25
     * @Description 根据 categoryId 查询菜品列表
     * @param categoryId
     * @return List<Dish>
     */
    @Override
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        List<Dish> dishList = dishMapper.list(dish);
        return dishList;
    }
}

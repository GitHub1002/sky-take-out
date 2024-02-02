package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /**
     *  添加员工
     * @param employeeDTO
     * @return
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工信息分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 员工账号启用/禁用
     * @param status
     * @param id
     * @return
     */
    void startOrStop(Integer status, Long id);

    /**
     * @author Mingkai Feng
     * @date 2024/2/2 14:03
     * @Description ToDo  根据id查询员工信息
     * @param id

     */
    Employee getById(Integer id);

    /**
     * @author Mingkai Feng
     * @date 2024/2/2 14:03
     * @Description ToDo  更新员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}

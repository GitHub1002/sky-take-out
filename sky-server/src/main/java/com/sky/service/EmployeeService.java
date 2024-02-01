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
}

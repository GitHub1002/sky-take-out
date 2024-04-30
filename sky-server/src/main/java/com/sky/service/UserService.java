/**
 * @Description 微信用户业务接口
 * @Classname UserService
 * @Date 2024/4/23 19:16
 * @Created by Mingkai Feng
 */
package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);
}

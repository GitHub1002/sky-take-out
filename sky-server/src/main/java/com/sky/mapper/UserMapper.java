/**
 * @Description 用户接口
 * @Classname UserMapper
 * @Date 2024/4/30 15:01
 * @Created by Mingkai Feng
 */
package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * @author Mingkai Feng
     * @date 2024/4/30 15:05
     * @Description ToDo    根据 openid 查询用户
     * @param openid
     * @return User
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User user);
}

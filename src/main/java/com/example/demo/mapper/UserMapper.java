package com.example.demo.mapper;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select *from user where id = #{id}")
    User getUserById(int id);

    @Update("update user set name = #{name} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);

    @Insert("insert into user values(#{id}, #{name})")
    void insertUser(User user);
}

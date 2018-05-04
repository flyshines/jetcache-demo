package com.us.example.mapper;

import java.util.List;
import com.us.example.model.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserDao {

     @Insert(value = "insert into user (name, create_time) values (#{name,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})")
     int insert(User record);
     @Update(value = "update user set name = #{name,jdbcType=VARCHAR} where id = #{id,jdbcType=INTEGER}")
     int update(User record);

     @Select(value = "select id, name, create_time from user where id = #{id,jdbcType=INTEGER}")
     @Results(value = { @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP) })
     User selectByPrimaryKey(Integer id);

     @Select(value = "select * from user")
     List<User> selectAll();

}

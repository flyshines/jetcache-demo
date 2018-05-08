package com.us.example.mapper;

import java.util.List;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.us.example.model.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserDao {

     @Insert(value = "insert into user (name, create_time) values (#{name,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})")
     int insert(User record);
     @Update(value = "update user set name = #{name,jdbcType=VARCHAR} where user_id = #{userId,jdbcType=INTEGER}")
     @CacheInvalidate(name="userCache.", key = "#user.userId")
     int update(User user);

     @Select(value = "select user_id, name, create_time from user where user_id = #{userId,jdbcType=INTEGER}")
     @Results(value = { @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP) ,@Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR)})
     //@Cached(name = "userCache.", key = "#userId", expire = 3600, cacheType = CacheType.REMOTE)
     User selectByPrimaryKey(Integer userId);

     @Select(value = "select * from user")
     List<User> selectAll();

}

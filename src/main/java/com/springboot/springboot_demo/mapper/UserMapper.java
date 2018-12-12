package com.springboot.springboot_demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.springboot.springboot_demo.entity.User;

@Mapper
public interface UserMapper {
    List<User> findUserByName(String name);

    public List<User> ListUser();

    public User insertUser(User user);

    public int delete(int id);

    public int Update(User user);
}

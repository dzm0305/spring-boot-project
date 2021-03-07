package com.duanzm.test.mapper;

import com.duanzm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    List<User> queryUserList2();

    void addUser(User user);
}

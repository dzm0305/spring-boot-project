package com.duanzm.test.service;

import com.duanzm.entity.User;
import com.duanzm.framework.datasource.annotation.DataSource;
import com.duanzm.framework.datasource.enums.DataSourceType;
import com.duanzm.test.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUserList(){
        List<User> list = userMapper.queryUserList();
        return list;
    }

    /**
     * 切换数据源
     * @return
     */
    @Transactional
    @DataSource(DataSourceType.SLAVE)
    public List<User> queryUserList2(){
        List<User> list = userMapper.queryUserList2();
        return list;
    }
}

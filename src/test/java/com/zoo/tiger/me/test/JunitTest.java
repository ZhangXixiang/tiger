package com.zoo.tiger.me.test;

import com.zoo.tiger.me.mapper.SysUserMapper;
import com.zoo.tiger.me.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Tiger
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class JunitTest {

    // 静态实例的注入
    static SysUserMapper mapper;

    @Autowired
    public void setMapper(SysUserMapper mapper) {
        JunitTest.mapper = mapper;
    }

    @MockBean
    private SysUserMapper mock;

    @Test
    public void insert() {
        SysUser sysUser = new SysUser();
        sysUser.setNick_name("zxx");
        sysUser.setUser_id(1L);
        sysUser.setAccount("a");
        sysUser.setPassword("b");
        sysUser.setSex("m");
        mapper.insertSelective(sysUser);
    }


}

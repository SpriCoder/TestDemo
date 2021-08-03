package com.njuzhy.testdemo;

import com.alibaba.fastjson.JSONObject;
import com.njuzhy.testdemo.constant.MyResponse;
import com.njuzhy.testdemo.constant.ResponseCode;
import com.njuzhy.testdemo.dao.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author stormbroken
 * Create by 2021/08/03
 * @Version 1.0
 **/

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MockMvcTest {
    @Autowired
    private MyRequestTemplate myRequestTemplate;

    @Test
    @DisplayName("测试根据名称成功获取管理员")
    void getByNameSuccess() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "admin");
        MyResponse myResponse = myRequestTemplate.getTemplate("/test/getByName", params, ResponseCode.OK, null);
        User user = ((JSONObject) myResponse.getData()).toJavaObject(User.class);
        assertEquals("admin", user.getUsername());
        assertEquals("admin", user.getPassword());
    }

    @Test
    @DisplayName("测试根据名称查找不存在用户")
    void getByNameFail() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "adminFail");
        MyResponse myResponse = myRequestTemplate.getTemplate("/test/getByName", params, ResponseCode.Error, null);
        assertEquals("NOT FIND!", myResponse.getData());
    }

    @Test
    @DisplayName("测试添加用户成功")
    void addUserSuccess() throws Exception {
        User user = new User("test", "test");
        MyResponse myResponse = myRequestTemplate.postTemplate("/test/addUser", user, ResponseCode.OK, null);
        assertEquals("ADD SUCCESS", myResponse.getData());
    }

    @Test
    @DisplayName("测试添加用户失败")
    void addUserFail() throws Exception {
        User user = new User("admin", "admin");
        MyResponse myResponse = myRequestTemplate.postTemplate("/test/addUser", user, ResponseCode.Error, null);
        assertEquals("NO ACCESS!", myResponse.getData());
    }

}

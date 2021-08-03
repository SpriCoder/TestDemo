package com.njuzhy.testdemo.controller;

import com.njuzhy.testdemo.constant.MyResponse;
import com.njuzhy.testdemo.dao.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2021/08/03
 * @Version 1.0
 **/

@RestController
@RequestMapping("/test")
public class TestController {
    List<User> users = new ArrayList<>(Arrays.asList(new User("admin", "admin")));

    @PostMapping("/addUser")
    public MyResponse addUser(@RequestBody User user){
        if(user.getUsername().equals("admin")){
            return MyResponse.error("NO ACCESS!");
        }
        users.add(user);
        return MyResponse.ok("ADD SUCCESS");
    }

    @GetMapping("/getByName")
    public MyResponse getByName(@RequestParam String username){
        for(User user: users){
            if(user.getUsername().equals(username)){
                return MyResponse.ok(user);
            }
        }
        return MyResponse.error("NOT FIND!");
    }

}

package com.njuzhy.testdemo;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author stormbroken
 * Create by 2021/08/03
 * @Version 1.0
 **/

public class MockitoSelfTest {

    @Test
    @DisplayName("使用Mock验证List.add")
    public void actionVerify(){
        // 创建mock对象，mock一个List接口
        List mockedList = mock(List.class);
        // 向mock对象调用add
        mockedList.add("one");
        // 验证调用情况
        verify(mockedList).add("one");
    }

    @Test
    @DisplayName("使用Mock制作测试桩")
    public void mockStub(){
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException("MyRuntime"));
        assertEquals("first", mockedList.get(0));
        Throwable throwable = assertThrows(RuntimeException.class,()->mockedList.get(1));
        assertEquals("MyRuntime", throwable.getMessage());
    }
}

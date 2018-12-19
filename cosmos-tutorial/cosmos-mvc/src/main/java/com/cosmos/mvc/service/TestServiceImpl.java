package com.cosmos.mvc.service;

import com.cosmos.mvc.annotation.Service;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 14:28
 * @Modified By：
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String show() {

        return "Hello,MVC";
    }
}

package com.cosmos.dubbo;

import com.cosmos.dubbo.client.RpcProxy;
import com.cosmos.dubbo.service.IProductService;
import com.cosmos.dubbo.service.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 模拟客户端启动
 * @Date: Create in 2018-12-21 13:16
 * @Modified By：
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-client.xml")
public class ClientApplication {
    @Autowired
    private RpcProxy rpcProxy;

    private IProductService productService;

    @Before
    public void init() {
        productService = rpcProxy.getInstance(IProductService.class);
    }


    @Test
    public void testSave() throws Exception {
        productService.save(new Product(2L, "002", "内衣", BigDecimal.TEN));
    }

    @Test
    public void testDelete() throws Exception {
        productService.deleteById(2L);
    }

    @Test
    public void testUpdate() throws Exception {
        productService.update(new Product(2L, "002", "内衣", BigDecimal.ONE));
    }

    @Test
    public void testGet() throws Exception {
        Product product = productService.get(1L);
        System.out.println("获取到的产品信息为:" + product);
    }
}

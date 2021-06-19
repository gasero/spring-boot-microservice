package com.ibm.project.service.container;

import com.ibm.project.service.container.repositories.ContainerRepository;
import lombok.Data;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Data
@EnableFeignClients
public abstract class SpringIntegrationTest {

    @Autowired
    private ContainerRepository containerRepository;

    public SpringIntegrationTest() {
    }
}

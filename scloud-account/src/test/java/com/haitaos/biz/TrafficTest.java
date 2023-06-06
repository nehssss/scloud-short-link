package com.haitaos.biz;

import com.haitaos.mapper.TrafficMapper;
import com.haitaos.model.TrafficDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@Slf4j
public class TrafficTest {

    @Autowired
    private TrafficMapper trafficMapper;
    @Test
    public void testSaveTraffic() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TrafficDO trafficDO = new TrafficDO();
            trafficDO.setAccountNo(Long.valueOf(random.nextInt(100)));
            trafficMapper.insert(trafficDO);
        }
    }
}

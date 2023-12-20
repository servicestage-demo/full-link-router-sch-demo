package com.huaweicloud.servicestage.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * controller
 *
 * @author provenceee
 * @since 2023-07-25
 */
@RestController
public class ProviderController {
    @Value("${spring.application.name}")
    private String name;

    @Value("${SERVICECOMB_INSTANCE_PROPS:}")
    private String props;

    @Value("${SPRING_CLOUD_SERVICECOMB_DISCOVERY_DATACENTER_AVAILABLEZONE:}")
    private String availableZone;

    /**
     * 测试方法
     *
     * @return msg
     */
    @GetMapping("unit-provider/hello")
    public Map<String, Object> hello() {
        Map<String, String> msg = new HashMap<>();
        msg.put("SERVICECOMB_INSTANCE_PROPS", props);
        if (StringUtils.hasText(availableZone)) {
            msg.put("AVAILABLE_ZONE", availableZone);
        }
        Map<String, Object> map = new HashMap<>();
        map.put(name, msg);
        return map;
    }
}

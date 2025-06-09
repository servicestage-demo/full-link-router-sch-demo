package com.huaweicloud.servicestage.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
public class Controller {
    private static final String CONSUMER_URL = "http://unit-consumer/unit-consumer/hello";

    @Value("${spring.application.name}")
    private String name;

    @Value("${SERVICECOMB_INSTANCE_PROPS:}")
    private String props;

    @Value("${SPRING_CLOUD_SERVICECOMB_DISCOVERY_DATACENTER_AVAILABLEZONE:}")
    private String availableZone;

    @Value("${SHOW_ENVS:}")
    private String showEnvs;

    @Autowired
    private InetUtils inetUtils;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试方法
     *
     * @return msg
     */
    @GetMapping("unit-controller/hello")
    public Map<String, Object> hello() {
        Map<String, String> msg = new HashMap<>();
        msg.put("SERVICECOMB_INSTANCE_PROPS", props);
        if (StringUtils.hasText(availableZone)) {
            msg.put("AVAILABLE_ZONE", availableZone);
        }
        if (StringUtils.hasText(showEnvs)) {
            msg.put("SHOW_ENVS", showEnvs);
        }
        msg.put("ip", inetUtils.findFirstNonLoopbackHostInfo().getIpAddress());
        Map<String, Object> map = new HashMap<>(restTemplate.getForObject(CONSUMER_URL, Map.class));
        map.put(name, msg);
        return map;
    }
}

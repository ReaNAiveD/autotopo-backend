package cn.svecri.autotopo.service;

import cn.svecri.autotopo.util.TelnetClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootConfiguration
public class TelnetClientConfiguration {

    @Bean
    public TelnetClient routerA(
            @Value("${router-a.ip}") String ip,
            @Value("${router-a.pwd}") String password
    ) {
        log.info("Configuring RouteA");
        var client = new TelnetClient("VT220","#");
//        if (!client.login(ip, 23, password)) {
//            log.error("Login to " + ip + ":" + 23 + " Error!");
//        }
        return client;
    }

    @Bean
    public TelnetClient routerB(
            @Value("${router-b.ip}") String ip,
            @Value("${router-b.pwd}") String password
    ) {
        log.info("Configuring RouteB");
        var client = new TelnetClient("VT220","#");
//        if (!client.login(ip, 23, password)) {
//            log.error("Login to " + ip + ":" + 23 + " Error!");
//        }
        return client;
    }

    @Bean
    public TelnetClient routerC(
            @Value("${router-c.ip}") String ip,
            @Value("${router-c.pwd}") String password
    ) {
        log.info("Configuring RouteC");
        var client = new TelnetClient("VT220","#");
//        if (!client.login(ip, 23, password)) {
//            log.error("Login to " + ip + ":" + 23 + " Error!");
//        }
        return client;
    }

}

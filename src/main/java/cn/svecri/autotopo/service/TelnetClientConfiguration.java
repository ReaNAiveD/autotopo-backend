package cn.svecri.autotopo.service;

import cn.svecri.autotopo.util.TelnetClient;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

@Log4j2
@SpringBootConfiguration
public class TelnetClientConfiguration {

    @Bean
    public TelnetClient routerA(
            @Value("${router-a.ip}") String ip,
            @Value("${router-a.pwd}") String password
    ) {
        log.info("Configuring RouterA");
        var client = new TelnetClient("VT220","#","RouterA");
        if (!client.login(ip, 23, password)) {
            log.error("Login to " + ip + ":" + 23 + " Error!");
        }
        client.sendCommand("term len 400");
        return client;
    }

    @Bean
    public TelnetClient routerB(
            @Value("${router-b.ip}") String ip,
            @Value("${router-b.pwd}") String password
    ) {
        log.info("Configuring RouterB");
        var client = new TelnetClient("VT220","#","RouterB");
        if (!client.login(ip, 23, password)) {
            log.error("Login to " + ip + ":" + 23 + " Error!");
        }
        client.sendCommand("term len 400");
        return client;
    }

    @Bean
    public TelnetClient routerC(
            @Value("${router-c.ip}") @Nullable String ip,
            @Value("${router-c.pwd}") @Nullable String password
    ) {
        log.info("Configuring RouterC");
        var client = new TelnetClient("VT220","#","RouterC");
        if (!client.login(ip, 23, password)) {
            log.error("Login to " + ip + ":" + 23 + " Error!");
        }
        client.sendCommand("term len 400");
        return client;
    }

}

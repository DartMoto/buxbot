package com.homework.buxbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.xml.stream.XMLStreamException;
import java.io.File;

@Singleton
@Slf4j
@Data
public class ApplicationConfig {
    private Configuration innerconfig;
    private double buyPrice;
    private double upLimitSell;
    private double lowLimitSell;
    private double delta;
    private String productId;
    private String wsUrl;
    private String openUrl;
    private String closeUrl;

    public ApplicationConfig(){
        try {
            parseApplicationConfig();
        } catch (ConfigurationException e) {
            log.error("Failed to load config file", e);
        }
    }

    private void parseApplicationConfig() throws ConfigurationException {
        Configurations configs = new Configurations();
        innerconfig = configs.properties(new File("config.properties"));
        buyPrice = innerconfig.getDouble("buy.price");
        upLimitSell = innerconfig.getDouble("up.limit.price");
        lowLimitSell = innerconfig.getDouble("low.limit.price");
        delta = innerconfig.getDouble("delta.equality.price", 0.01);
        productId = innerconfig.getString("product.id");
        wsUrl = innerconfig.getString("websoket.url");
        openUrl = innerconfig.getString("open.position.url");
        closeUrl = innerconfig.getString("close.position.url");
    }
}

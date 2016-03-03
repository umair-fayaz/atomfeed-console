package org.ict4h.service;

import org.ict4h.domain.AppConfig;
import org.ict4h.domain.AppConfigs;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.sql.SQLException;

@Component
public class AppConfiguration {
    public static final String DEFAULT_APP_CONFIG_FILE = "applicationConfig.yml";
    private AppConfigs appconfigs = new AppConfigs();

    public AppConfiguration() throws SQLException{
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_APP_CONFIG_FILE);
        Constructor constructor = new Constructor(AppConfig.class);
        Yaml yaml = new Yaml(constructor);

        for (Object data : yaml.loadAll(in)) {

            AppConfig obj = (AppConfig) data;
            appconfigs.add(obj);
        }

    }



    public AppConfigs getAppConfigs() {
        return appconfigs;
    }

    public AppConfig getAppConfigForApp(String appName) {
        return appconfigs.getForApp(appName);
    }
}
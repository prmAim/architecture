package ru.geekbrains.config;

import java.io.IOException;
import java.util.Properties;

/**
 * Убираем тип видимости!!! Видим только в пакете.
 */
class ConfigFromFile implements ServerConfig {

    private final String www;

    private final int port;

    ConfigFromFile(String filename) {
        System.out.println("LOG: Getting config from config file");

        Properties prop = new Properties();
        try {
            // загрузка данных
            prop.load(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.www = prop.getProperty("www.home");
        this.port = Integer.parseInt(prop.getProperty("port"));
    }

    @Override
    public String getWww() {
        return this.www;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}

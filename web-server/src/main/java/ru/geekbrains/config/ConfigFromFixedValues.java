package ru.geekbrains.config;

/**
 * Убираем тип видимости!!! Видим только в пакете.
 */
class ConfigFromFixedValues implements ServerConfig {

    @Override
    public String getWww() {
        return "web-server/www";
    }

    @Override
    public int getPort() {
        return 8088;
    }
}





package ru.geekbrains.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестирования загрзки параметров из файла конфигурации
 */
public class ConfigFromFileTest {

  public ServerConfig config;

  @Test
  public void testConfigFromFile(){
    // передача файла параметров. После компеляции он будет находиться resources => в корне
    config = new ConfigFromFile("../../../server.properties");
    Assert.assertEquals("/homepath", config.getWww());
    Assert.assertEquals(1234, config.getPort());
  }
}

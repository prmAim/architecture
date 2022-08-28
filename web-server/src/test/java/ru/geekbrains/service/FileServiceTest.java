package ru.geekbrains.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестирования класса FileService
 */
public class FileServiceTest {

  private static final FileServiceImpl fileService = new FileServiceImpl("../../../");

  @Test
  public void testReadFile() {
    Assert.assertEquals("This file is test!", fileService.readFile("test.txt"));
  }
}

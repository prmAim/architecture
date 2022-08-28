package ru.geekbrains.service;

public interface FileService {

  /**
   * Проверка на существование файла
   */
  boolean exists(String filename);

  /**
   * Проверка, что фаил - это папка
   */
  boolean isDirectory(String filename);

  /**
   * Чтение данных из файла
   */
  String readFile(String filename);
}

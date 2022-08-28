package ru.geekbrains.service;

/**
 * Pattern Factory для сервиса FileService
 */
public final class FileServiceFactory {

  public static FileService create(String rootDir) {
    return new FileServiceImpl(rootDir);
  }
}

package ru.geekbrains;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFiles {

  // Корневой католог
  private final String folder;
  private final String lineInput;

  public PathFiles(String folder, String lineInput){
    this.folder = folder;
    this.lineInput = lineInput;
  }

  /**
   * Получить путь к файлу
   */
  public Path getPathFile(){
    String[] parts = lineInput.split(" ");
    Path path = Paths.get(folder, parts[1]);
    System.out.println("LOG: " + lineInput);
    return path;
  }

  /**
   * проверка на существование файла
   */
  public boolean isExistsFile(){
    return !Files.exists(this.getPathFile());
  }
}

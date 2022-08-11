package ru.geekbrains.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    private final String rootDir;

    public FileService(String rootDir) {
        this.rootDir = rootDir;
    }

    public boolean exists(String filename){
        return Files.exists(Path.of(rootDir, filename));
    }

    public boolean isDirectory(String filename){
        return Files.isDirectory(Path.of(rootDir, filename));
    }

    public String readFile(String filename){
        StringBuilder dateFromFile = new StringBuilder();
        try {
            Files.readAllLines(Path.of(rootDir, filename)).forEach(dateFromFile::append);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        return dateFromFile.toString();
    }
}

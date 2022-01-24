package ru.lanit.at.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    /**
     * поиск файла по заданному пути и имени файла
     * <br/>если нет ни одного, то ошибка. если более одного, то тоже ошибка
     *
     * @param path     путь
     * @param fileName наименование файла
     * @return возвращает один файл
     */
    public static File searchFileInDirectory(String path, String fileName) {
        List<Path> paths;
        try {
            paths = Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (path1, basicFileAttributes) -> path1.toFile().getName().equals(fileName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileSystemNotFoundException("Файл не найден");
        }

        if (paths.size() > 1) {
            throw new AssertionError("В каталоге более 1 файла с именем " + fileName);
        } else if (paths.size() == 0) {
            throw new FileSystemNotFoundException("Файл не найден");
        } else {
            return paths.get(0).toFile();
        }
    }

    /**
     * метод для составления полного пути до директории или файла
     *
     * @param packages массив наименований папок или файла (в конце)
     * @return полный путь до директории или файла
     */
    public static StringBuilder getParentPath(String... packages) {
        StringBuilder pathBuilder = new StringBuilder();
        String separator = System.getProperty("file.separator");
        if (!System.getProperty("project.dir", "").isEmpty()) {
            pathBuilder.append(System.getProperty("project.dir"));
        } else {
            pathBuilder.append(System.getProperty("user.dir"));
        }
        for (String packageName : packages) {
            if (pathBuilder.toString().endsWith(separator)) {
                pathBuilder.append(packageName);
            } else {
                pathBuilder.append(separator).append(packageName);
            }
        }

        return pathBuilder;
    }

    /**
     * Считывает и возвращает содержимый текст файла
     *
     * @param fileName -   название файла
     * @param packages -   директории, в которых лежит файл
     * @return -   содержимый текст файла
     */
    private static String readBodyFromFile(String fileName, String... packages) {
        File file = searchFileInDirectory(getParentPath(packages).toString(), fileName);
        try {
            return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Считывает json-файл из пакеты по пути: "resources", "json"
     * Путь часто используется, поэтому вынесен в отдельным метод
     *
     * @param fileName -   название файла
     * @return -   содержание файла
     */
    public static String readBodyFromJsonDir(String fileName) {
        return readBodyFromFile(fileName, "src", "test", "resources", "json");
    }
}

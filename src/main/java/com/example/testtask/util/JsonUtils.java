package com.example.testtask.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {
    public static String getFromFile(String pathToJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathToJson)));
    }
}

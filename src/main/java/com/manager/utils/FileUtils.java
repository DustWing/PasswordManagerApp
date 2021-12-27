package com.manager.utils;

import java.io.*;
import java.util.Collection;
import java.util.function.Function;

public class FileUtils {

    public static <T> void readByLine(final String filePath,
                                      final Collection<T> collection,
                                      final Function<String, T> function)
            throws IOException {

        try (
                BufferedReader reader = new BufferedReader(new FileReader(filePath))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {

                collection.add(function.apply(currentLine));

            }
        }

    }

    public static String read(final String filePath)
            throws IOException {

        StringBuilder resultStringBuilder = new StringBuilder();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(filePath))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static void write(final String filePath, final String value) throws IOException {
        try (FileWriter myWriter = new FileWriter(filePath)) {
            myWriter.write(value);
        }
    }

}

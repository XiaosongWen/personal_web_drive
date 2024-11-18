package com.xiaosong.immichdemo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    public static void copyFile(Path sourcePath, Path targetPath) {
        try {
            Files.createDirectories(targetPath.getParent());
            Path finalTargetPath = getUniqueTargetPath(targetPath);
            Files.copy(sourcePath, finalTargetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error occurred during file copy: " + e.getMessage());
        }
    }
    // Method to get a unique file path by renaming if the file exists
    private static Path getUniqueTargetPath(Path targetPath) throws IOException {
        if (!Files.exists(targetPath)) {
            return targetPath;
        }

        // If file exists, generate a new name by appending _1, _2, etc.
        String fileName = targetPath.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        int counter = 1;
        Path newTargetPath = targetPath;
        while (Files.exists(newTargetPath)) {
            String newFileName = baseName + "_" + counter + extension;
            newTargetPath = targetPath.getParent().resolve(newFileName);
            counter++;
        }

        return newTargetPath;
    }

}

package ru.itis.restoke.servlets.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNameHelper {
    public static String getNewFullFileNames() {
        int currentCount = 0;
        int counter = 1;
        int filesCount = 1;
        String fullFileNames = null;

        String fullFileName = "img.jpg";
        File file = new File(fullFileName);
        while (currentCount < filesCount) {
            boolean fileExists = file.exists();
            String previousFullFileName = fullFileName;

            counter++;
            fullFileName = "img (" + counter + ")" + ".jpg";
            file = new File(fullFileName);

            if (!fileExists) {
                fullFileNames = previousFullFileName;
                ++currentCount;
            }
        }
        return fullFileNames;
    }
}

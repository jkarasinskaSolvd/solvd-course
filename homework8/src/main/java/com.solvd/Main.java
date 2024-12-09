package com.solvd;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.lang3.StringUtils.countMatches;

public class Main {
    public static final void main(String[] args) {
        try {
            String fileContent =  readFileToString(new File("turtle.txt"), Charset.defaultCharset());
            fileContent = StringUtils.replaceChars(fileContent, "\n.,\r", " ");
            Set<String> words = new HashSet<>(Arrays.asList(StringUtils.split(fileContent, " ")));
            File resultFile = new File("result.txt");
            writeStringToFile(resultFile, "", Charset.defaultCharset(), false);
            for(String word : words) {
                writeStringToFile(resultFile, word + ": " + countMatches(fileContent, word) + "\n", Charset.defaultCharset(), true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
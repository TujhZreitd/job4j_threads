package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    final private File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    private String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() {
        return getContent(ch -> ch < 0x80);
    }

    public String getContentWithoutCondition() {
        return getContent(ch -> true);
    }
}

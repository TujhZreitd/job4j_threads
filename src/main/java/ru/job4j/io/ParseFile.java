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
        String output = "";
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() {
        return getContent(ch -> ch < 0x80);
    }

    public String getContentWithoutCondition() {
        return getContent(ch -> true);
    }
}

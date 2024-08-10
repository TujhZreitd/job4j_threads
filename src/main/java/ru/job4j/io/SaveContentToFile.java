package ru.job4j.io;

import java.io.*;

public class SaveContentToFile implements SavingContent {
    final private File file;

    public SaveContentToFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    @Override
    public void saveContent(String content) {
        try (OutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

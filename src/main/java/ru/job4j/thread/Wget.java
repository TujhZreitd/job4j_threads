package ru.job4j.thread;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        long downloadTime;
        String file = null;
        try {
            file = new URL(url).getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        file = file.substring(file.lastIndexOf("/") + 1);

        try (InputStream input = new URL(url).openStream();
             OutputStream output = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead = 1;
            int countBytes = 0;
            downloadTime = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                countBytes++;
                if (countBytes == speed) {
                    long time = System.currentTimeMillis() - downloadTime;
                    if (time < 1000) {
                        try {
                            Thread.sleep(1000 - time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean urlValid(String url) {
        boolean result;
        try {
            new URL(url).toURI();
            result = true;
        } catch (URISyntaxException e) {
            result = false;
        } catch (MalformedURLException e) {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Uncorrected arguments");
        }
        if (!urlValid(args[0])) {
            throw new IllegalArgumentException("The URL is not valid");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed don't be 0 or less 0");
        }
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}

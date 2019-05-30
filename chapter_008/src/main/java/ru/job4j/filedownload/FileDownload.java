package ru.job4j.filedownload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownload {

    public File download(int speed, String link) {
        try {
            var url = new URL(link);
            var con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            var is = con.getInputStream();
            var inputStream = new CustomInputStream(is, speed);
            var buffer = new byte[2024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                System.out.println("downloaded : " + len);
                for (var v:buffer) {
                    System.out.println(v);
                }
                //save file
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class CustomInputStream extends InputStream {

        private final int maxSpeed;
        private final long oneSecond = 1000;
        private long downloadedWhithinOneSecond = 0L;
        private long lastTime;
        private InputStream inputStream;

        public CustomInputStream(InputStream inputStream, int speed) {
            this.inputStream = inputStream;
            lastTime = System.currentTimeMillis();
            this.maxSpeed = speed;
        }

        @Override
        public int read() throws IOException {
            long currentTime = System.currentTimeMillis();
            if (downloadedWhithinOneSecond >= maxSpeed
                    && ((currentTime - lastTime) < oneSecond)) {
                try {
                    Thread.sleep(oneSecond - (currentTime - lastTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downloadedWhithinOneSecond = 0;
                lastTime = System.currentTimeMillis();
            }
            int res = inputStream.read();
            if (res >= 0) {
                downloadedWhithinOneSecond++;
            }
            return res;
        }

        @Override
        public int available() throws IOException {
            return inputStream.available();
        }

        @Override
        public void close() throws IOException {
            inputStream.close();
        }
    }


    public static void main(String[] args) {
        var fileDownload = new FileDownload();
        fileDownload.download(50000, "https://speed.hetzner.de/100MB.bin");
    }
}

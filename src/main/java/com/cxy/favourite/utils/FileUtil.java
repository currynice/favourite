package com.cxy.favourite.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {

    /**
     * 读取到字节数组
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filePath) throws IOException {

        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

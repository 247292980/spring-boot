package com.lig.bigfile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BigFile {
    private static String input_path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "bigdata.txt";
    private static String output_path_format1 = System.getProperty("user.dir") + File.separator + "data" + File.separator + "part_1_%s.txt";
    private static String output_path_format2 = System.getProperty("user.dir") + File.separator + "data" + File.separator + "part_2_%s.txt";
    private static  int bufSize = 10 * 1024 * 1024;

    public static void main(String[] args) {
        try {
//            makeBigData();
            fileRead();
            bufferRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeBigData() throws IOException {
        FileWriter fw = new FileWriter(input_path);

        String line = "start ";
        for (int i = 0; i < 20000; i++) {
            line += i;
            fw.append(line + System.getProperty("line.separator"));
        }
        fw.flush();
        fw.close();
        System.out.println("end");
    }

    private static void fileRead() throws IOException {
        long time = System.currentTimeMillis();

        byte[] bs = new byte[bufSize];
        ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
        FileChannel channel = new RandomAccessFile(input_path, "r").getChannel();
        FileWriter fw = null;
        for (int i = 0; channel.read(byteBuf) != -1; i++) {
            byteBuf.rewind();
            int size = byteBuf.limit();
            byteBuf.get(bs);

            fw = new FileWriter(String.format(output_path_format1, i));
            String line = new String(bs, 0, size);
            fw.append(line + System.getProperty("line.separator"));
            fw.flush();
            byteBuf.clear();
        }
        fw.close();
        time = System.currentTimeMillis() - time;
        System.out.println("file read time = " + time);
    }

    private static void bufferRead() throws IOException {
        long time = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(input_path)));

        BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), bufSize);
        FileWriter fw = new FileWriter(String.format(output_path_format2, 0));

        for (int i = 0; in.ready(); i++) {
            if (i % 100 == 0) {
                fw = new FileWriter(String.format(output_path_format2, i / 100));
            }
            String line = in.readLine();
            fw.append(line + System.getProperty("line.separator"));
            if (i % 100 == 0) {
                fw.flush();
            }
        }
        in.close();
        fw.close();
        time = System.currentTimeMillis() - time;
        System.out.println("buffer read time = " + time);
    }


}


package com.suood.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class FileChannelDemo {
  public static final String path="";
  /**
   * 10毫秒可以加载2G文件。
   * @throws IOException
   */
  public static void displayFileChannel() throws IOException {
    String fileName = "data.txt";
    final FileChannel channel = new FileInputStream(fileName).getChannel();
    MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

// when finished
    
    System.out.println(buffer.asCharBuffer());
  }

  public static void main(String[] args) {
    try {
      displayFileChannel();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

//  public static void main(String[] s) throws IOException {
//    stream();
//    mem();
//  }

  public static void stream() throws FileNotFoundException, IOException {
    Long startTime = System.currentTimeMillis();
    BufferedReader reader = getReader(new File(path));

    String line;
    while ((line = reader.readLine()) != null) {
      // 空转
    }
    Long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.printf("stream Diff: %d ms\n", estimatedTime);

  }

  public static BufferedReader getReader(File f) throws FileNotFoundException, IOException {
    BufferedReader reader = null;
    if (f.getName().endsWith(".gz")) {
      reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(f))));
    } else {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    }
    return reader;
  }

  public static void mem() throws IOException {
    Long startTime = System.currentTimeMillis();
    FileChannel fc = new FileInputStream(path).getChannel();
    MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    //Charset charset = Charset.forName("US-ASCII");
    Charset charset = Charset.forName("iso-8859-1");
    CharsetDecoder decoder = charset.newDecoder();
    CharBuffer charBuffer = decoder.decode(byteBuffer);
    Scanner sc = new Scanner(charBuffer).useDelimiter(System.getProperty("line.separator"));
    while (sc.hasNext()) {
      sc.next();
    }
    fc.close();
    Long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.printf("mem Diff: %d ms", estimatedTime);
  }
}

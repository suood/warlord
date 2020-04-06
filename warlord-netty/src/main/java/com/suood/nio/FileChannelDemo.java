package com.suood.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

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
}

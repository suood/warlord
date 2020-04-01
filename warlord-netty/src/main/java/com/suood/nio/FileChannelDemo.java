package com.suood.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

  public void displayFileChannel() throws IOException {
    FileInputStream inputStream = new FileInputStream("data.txt");
    FileChannel channel = inputStream.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());

    channel.read(buffer);
    buffer.flip();//Restore buffer to position 0 to read it
    
    channel.close();
    inputStream.close();
//    return bytes;
  }

  public static void main(String[] args) {
    FileChannelDemo fileChannelDemo = new FileChannelDemo();
    try {
      fileChannelDemo.displayFileChannel();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

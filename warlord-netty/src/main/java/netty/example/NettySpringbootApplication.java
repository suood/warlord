package netty.example;

import netty.example.http.NettyHttpServerDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class NettySpringbootApplication {

  public static void main(String[] args) {
    NettyHttpServerDemo nettyHttpServerDemo = new NettyHttpServerDemo();
    nettyHttpServerDemo.start();
    SpringApplication.run(NettySpringbootApplication.class, args);
  }

}
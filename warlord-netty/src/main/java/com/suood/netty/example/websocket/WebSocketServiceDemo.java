package com.suood.netty.example.websocket;

import com.suood.netty.example.http.HttpRequestHandler;
import com.suood.netty.example.http.NettyHttpServerDemo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * @auther suguangqiang .
 * @date 2020-06-22 15:37.
 */
public class WebSocketServiceDemo {
  private ChannelFuture channel;
  // event loop   nio or epoll  lt or et ;
  private final EventLoopGroup masterGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(1) : new NioEventLoopGroup(1);

  private final EventLoopGroup slaveGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup(1);

  public void start() // #1
  {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));

    try {
      // #3
      final ServerBootstrap bootstrap = new ServerBootstrap()
          .group(masterGroup, slaveGroup)
          .channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
//      ChannelOutboundHandlerAdapter          ChannelInboundHandlerAdapter 分别是 出站handler 和 入站 handler 需要继承的类.
//      com.suood.netty 在调用时，会忽略反向的handler
          .childHandler(new ChannelInboundHandlerAdapter() {

          })
          .childHandler(new ChannelInitializer<SocketChannel>() // #4
          {
            @Override
            public void initChannel(final SocketChannel ch) {
              //有序的，莫要搞乱鸟
              ch.pipeline().addLast("codec", new HttpServerCodec());
              ch.pipeline().addLast(new ChunkedWriteHandler());
              ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
//              ch.pipeline().addLast("request", new HttpRequestHandler<FullHttpRequest>());
              ch.pipeline().addLast(new HttpResponseEncoder());
              ch.pipeline().addLast(new IdleStateHandler(10, 10, 15, TimeUnit.SECONDS));

            }
          })
          .option(ChannelOption.SO_BACKLOG, 128)
          .childOption(ChannelOption.SO_KEEPALIVE, true);
      channel = bootstrap.bind(8080).sync();
    } catch (final InterruptedException e) {
    }
  }

  public void shutdown() // #2
  {
    slaveGroup.shutdownGracefully();
    masterGroup.shutdownGracefully();

    try {
      channel.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new NettyHttpServerDemo().start();
  }
}

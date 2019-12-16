package netty.example.http;

import static io.netty.buffer.Unpooled.copiedBuffer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

public class NettyHttpServerDemo {

    //channel
    private ChannelFuture channel;
    // event loop   nio or epoll  lt or et ;
    private final EventLoopGroup masterGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

    private final EventLoopGroup slaveGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

    public void start() // #1
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));

        try {
            // #3
            final ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(masterGroup, slaveGroup)
                    .channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() // #4
                    {
                        @Override
                        public void initChannel(final SocketChannel ch) {
                            ch.pipeline().addLast("codec", new HttpServerCodec());
                            ch.pipeline().addLast("aggregator",
                                    new HttpObjectAggregator(512 * 1024));
                            ch.pipeline().addLast("request",
                                    new ChannelInboundHandlerAdapter() // #5
                                    {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg)
                                                throws Exception {
                                            if (msg instanceof FullHttpRequest) {
                                                final FullHttpRequest request = (FullHttpRequest) msg;
                                                final String responseMessage = "Hello from Netty!";
                                                FullHttpResponse response = new DefaultFullHttpResponse(
                                                        HttpVersion.HTTP_1_1,
                                                        HttpResponseStatus.OK,
                                                        copiedBuffer(responseMessage.getBytes())
                                                );
                                                if (HttpUtil.isKeepAlive(request)) {
                                                    response.headers().set(
                                                            HttpHeaderNames.CONNECTION,
                                                            HttpHeaderNames.KEEP_ALIVE
                                                    );
                                                }
                                                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                                                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseMessage.length());
                                                ctx.writeAndFlush(response);
                                            } else {
                                                super.channelRead(ctx, msg);
                                            }
                                        }

                                        @Override
                                        public void channelReadComplete(ChannelHandlerContext ctx) {
                                            ctx.flush();
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx,
                                                                    Throwable cause) {
                                            ctx.writeAndFlush(new DefaultFullHttpResponse(
                                                    HttpVersion.HTTP_1_1,
                                                    HttpResponseStatus.INTERNAL_SERVER_ERROR,
                                                    copiedBuffer(cause.getMessage().getBytes())
                                            ));
                                        }
                                    });
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
        }
    }

    public static void main(String[] args) {
        new NettyHttpServerDemo().start();
    }
}

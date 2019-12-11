package netty.example;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import java.util.function.Supplier;

/**
 * 实现工厂样式的 Supplier
 */
public class EventLoopGoupSupplier implements Supplier<EventLoopGroup> {


  @Override
  public EventLoopGroup get() {
    return Epoll.isAvailable()?new EpollEventLoopGroup():new NioEventLoopGroup();
  }
}

package reactor.example.flux;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {
  public static void fluxInt(){
    List <Integer> integerList = Lists.newArrayList();
    Flux.fromStream(IntStream.range(1,1001).boxed())
        .publishOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
//        .publishOn(Schedulers.elastic())
        .subscribe(System.out::println);
  }

  public static void main(String[] args) {
    FluxDemo.fluxInt();
  }
}

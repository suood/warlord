package reactor.example.flux;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


/**
 * 第一个flux 好东西啊
 * @author suguangqiang 
 */
public class FluxDemo {
  public static void fluxInt(){
    List <Integer> integerList = Lists.newArrayList();
    Flux.fromStream(IntStream.range(1,1001).boxed())
        .publishOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
//        .publishOn(Schedulers.elastic())
        .subscribe(System.out::println);
//    Flux.just(integerList);
  }
  public Mono<List<Integer>> getIntMono(){
   return Flux.range(1,1000).collectList();
  }
  public static void main(String[] args) {
    FluxDemo fluxDemo = new FluxDemo();
  }
}

package reactor.example.flux;

import reactor.core.publisher.Flux;

public class Common {
  public static void main(String[] args) {
    System.out.println(1);
    Flux.just(1).subscribe(System.out::println);
  }
  public synchronized void test(){
    
  }
}

package io.pivotal.literx;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Flux;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

//========================================================================================

	// TODO Return an empty Flux
	Flux<String> emptyFlux() {
		return Flux.just();
	}

//========================================================================================

	// TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
	Flux<String> fooBarFluxFromValues() {
		return Flux.just("foo","bar");
	}

//========================================================================================

	// TODO Create a Flux from a List that contains 2 values "foo" and "bar"
	Flux<String> fooBarFluxFromList() {
//		String [] array =  {"foo","bar"}  ;
//		Flux.fromArray(array);
		List<String> list = Arrays.asList("JAVA", "SAMPLE", "APPROACH", ".COM");
		return  Flux.fromIterable(list);

	}

//========================================================================================

	// TODO Create a Flux that emits an IllegalStateException
	Flux<String> errorFlux() {
		return Flux.error(()->new IllegalStateException("IllegalStateException"));
	}

//========================================================================================

		// TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
	Flux<Long> counter() {
//		return Flux.interval(Duration.ofMillis(100)).take(10);
		return Flux.range(0, 10).map(i -> Long.valueOf(i)).delayElements(Duration.ofMillis(100));
	}

	public static void main(String[] args) {
		Part01Flux part01Flux = new Part01Flux();
//		Flux<String> flux =part01Flux.emptyFlux();
		part01Flux.counter().subscribe(System.out::println);
		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

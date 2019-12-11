package com.suood.warlord.rest;

import com.suood.warlord.WarlordSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WarlordSpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTest {
  @Autowired
  private WebTestClient webTestClient;


  @Test
  public void demoTest(){
//    webTestClient.get().uri("/hi/100")
////        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .accept(MediaType.APPLICATION_JSON_UTF8)
////        .body(Mono.just(cityRelationRequest), CityRelationRequest.class)
//        .exchange()
//        .expectStatus().isOk()
//        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//        .expectBody().isEmpty();
    for (int i = 0; i <100 ; i++) {
      webTestClient.get().uri("/hi/100")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
          .accept(MediaType.APPLICATION_JSON_UTF8)
//        .body(Mono.just(cityRelationRequest), CityRelationRequest.class)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBody().isEmpty();
    }
  }
}

package com.suood.vertx;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
            .requestHandler(req -> {
                req.response().end("Hello from Vert.x");
            })
            .listen(8080);
    }

}
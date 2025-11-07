package com.maersk.booking.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;

import reactor.netty.http.server.HttpServer;

@Configuration
public class NettyServerConfig {

	
	    @Bean
	    public ReactiveWebServerFactory reactiveWebServerFactory() {
	        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory();

	        factory.addServerCustomizers(httpServer -> 
	            httpServer.httpRequestDecoder(decoder -> 
	                decoder.maxHeaderSize(16 * 1024) // 🔹 16 KB
	                        .maxInitialLineLength(4096)
	            )
	        );

	        return factory;
	    }
	}
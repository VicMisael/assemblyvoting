package com.example.assemblyvoting.handler.router;

import org.springframework.boot.info.JavaInfo;
import org.springframework.boot.info.OsInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class RootHandlerRouter {


    @Bean
    public RouterFunction<ServerResponse> rootRoutes() {
        final OsInfo osInfo = new OsInfo();
        final JavaInfo infoJava = new JavaInfo();
        final String systemData = osInfo.getArch() + " " + osInfo.getVersion() + " " + osInfo.getName();
        final String javaInfo = infoJava.getJvm().getName() + " " + infoJava.getVersion() + " ";
        return route().GET("/", serverRequest -> ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN).
                bodyValue("ASSEMBLY VOTING " + systemData + javaInfo)).build();
    }
}

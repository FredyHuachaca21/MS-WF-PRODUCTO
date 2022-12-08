package com.pe.fredgar.mswf.product.config;

import com.pe.fredgar.mswf.product.controller.CategoriaController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFuntionCategoriaConfig {
    @Bean
    public RouterFunction<ServerResponse> routesCategoria(CategoriaController controller){
        return route(POST("/api/v1/categoria"), controller::crearCategoria);
    }
}

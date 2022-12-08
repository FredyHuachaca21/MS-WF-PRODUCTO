package com.pe.fredgar.mswf.product.config;

import com.pe.fredgar.mswf.product.controller.ProductoController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFuntionProductoConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ProductoController controller){
        return route(GET("/api/v1/producto/lista").or(GET("/api/v2/productos")), controller::listarProductos)
                .andRoute(GET("/api/v1/producto/{id}"), controller::listarProductoPorId)
                .andRoute(POST("/api/v1/producto"), controller::crear)
                .andRoute(POST("/api/v1/producto/subirImagen/{id}"), controller::subirImagen)
                .andRoute(POST("/api/v1/producto/crearConFoto"), controller::crearProductoConFoto)
                .andRoute(PUT("/api/v1/producto/{id}"), controller::editarProducto)
                .andRoute(DELETE("/api/v1/producto/{id}"), controller::eliminarProducto)
                .andRoute(DELETE("/api/v1/producto/{id}"), controller::eliminarProductoPorID);


    }

}

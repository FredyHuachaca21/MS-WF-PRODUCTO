package com.pe.fredgar.mswf.product.controller;

import com.pe.fredgar.mswf.product.model.Producto;
import com.pe.fredgar.mswf.product.service.ProductoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ProductoController {

    private ProductoServiceImpl service;

    public Mono<ServerResponse> listarProductos(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.listar(), Producto.class);
    }

    public Mono<ServerResponse> listarProductoPorId(ServerRequest request){
        String id = request.pathVariable("id");
        return service.buscarPorId(id).flatMap(p -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(p)
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> crear(ServerRequest request){
        Mono<Producto> productoMono = request.bodyToMono(Producto.class);

        return productoMono.flatMap(p -> {
            if (p.getFechaCreacion() == null){
                p.setFechaCreacion(LocalDate.now());
            }
            return service.guardar(p);
        }).flatMap(p -> ServerResponse
                .created(URI.create("/api/v1/productos/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(p));
    }

    public Mono<ServerResponse> editarProducto(ServerRequest request){
        Mono<Producto> productoMono = request.bodyToMono(Producto.class);
        String id = request.pathVariable("id");

        Mono<Producto> productoDB = service.buscarPorId(id);

        return productoDB.zipWith(productoMono, (prodDB, prodMono) ->{
            prodDB.setNombre(prodMono.getNombre());
            prodDB.setPrecio(prodMono.getPrecio());
            prodDB.setCategoria(prodMono.getCategoria());
            return prodDB;
        }).flatMap(p -> ServerResponse.created(URI.create("/api/v1/productos/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.guardar(p), Producto.class)
                .switchIfEmpty(ServerResponse.notFound().build()));

    }

    public Mono<ServerResponse> eliminarProducto(ServerRequest request){
        String id = request.pathVariable("id");

        Mono<Producto> productoDB = service.buscarPorId(id);

        return productoDB.flatMap(p-> service.eliminar(p.).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> eliminarProductoPorID(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Producto> productoDB = service.buscarPorId(id);

        return productoDB.flatMap(p-> service.eliminarPorId(p.getId())
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}

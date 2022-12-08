package com.pe.fredgar.mswf.product.controller;

import com.pe.fredgar.mswf.product.model.Categoria;
import com.pe.fredgar.mswf.product.service.CategoriaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@AllArgsConstructor
public class CategoriaController {

    private CategoriaServiceImpl service;

    public Mono<ServerResponse> crearCategoria(ServerRequest request){
        Mono<Categoria> categoriaMono = request.bodyToMono(Categoria.class);

        return categoriaMono.flatMap(service::crear)
                .flatMap(cat -> ServerResponse
                .created(URI.create("/api/v1/categoria/".concat(cat.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cat));
    }
}

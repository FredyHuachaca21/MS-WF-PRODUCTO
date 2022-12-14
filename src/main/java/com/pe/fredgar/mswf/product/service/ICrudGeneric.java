package com.pe.fredgar.mswf.product.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICrudGeneric<T, ID> {

    public Flux<T> listar();

    public Mono<T> buscarPorId(String id);

    public Mono<T> guardar(T t);

    public Mono<T> editar(T t, String id);

    public Mono<T> crear(T t);

    public Mono<Void> eliminar(T t);

    public Mono<Void> eliminarPorId(String id);
}

package com.pe.fredgar.mswf.product.repository;

import com.pe.fredgar.mswf.product.model.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICategoriaRepository extends ReactiveMongoRepository<Categoria, String> {
}

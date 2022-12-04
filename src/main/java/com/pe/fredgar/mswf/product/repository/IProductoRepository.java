package com.pe.fredgar.mswf.product.repository;

import com.pe.fredgar.mswf.product.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductoRepository extends ReactiveMongoRepository<Producto, String> {
}

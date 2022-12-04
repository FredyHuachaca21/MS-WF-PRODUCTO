package com.pe.fredgar.mswf.product.service;

import com.pe.fredgar.mswf.product.model.Producto;
import com.pe.fredgar.mswf.product.repository.IProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements IProductoService{

    private IProductoRepository repository;

    @Override
    public Flux<Producto> listar() {
        return repository.findAll();
    }

    @Override
    public Mono<Producto> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Producto> guardar(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Mono<Producto> editar(Producto producto, String id) {
        return repository.findById(id).flatMap(p ->{
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setCategoria(producto.getCategoria());
            return repository.save(p);
        });
    }

    @Override
    public Mono<Producto> crear(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Mono<Void> eliminar(Producto producto) {
        return repository.delete(producto);
    }

    @Override
    public Mono<Void> eliminarPorId(String id) {
        return repository.deleteById(id);
    }
}

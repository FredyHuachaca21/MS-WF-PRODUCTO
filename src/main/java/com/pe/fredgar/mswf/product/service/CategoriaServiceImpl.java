package com.pe.fredgar.mswf.product.service;

import com.pe.fredgar.mswf.product.model.Categoria;
import com.pe.fredgar.mswf.product.repository.ICategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService{

    private ICategoriaRepository repository;

    @Override
    public Flux<Categoria> listar() {
        return repository.findAll();
    }

    @Override
    public Mono<Categoria> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Categoria> guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Mono<Categoria> editar(Categoria categoria, String id) {
        return repository.findById(id).flatMap(c ->{
            c.setNombre(categoria.getNombre());
            return repository.save(c);
        });
    }

    @Override
    public Mono<Categoria> crear(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Mono<Void> eliminar(Categoria categoria) {
        return repository.delete(categoria);
    }

    @Override
    public Mono<Void> eliminarPorId(String id) {
        return repository.deleteById(id);
    }
}

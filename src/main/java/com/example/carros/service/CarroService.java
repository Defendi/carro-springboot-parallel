
package com.example.carros.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    // Cadastro em lote de carros
    public List<Carro> saveAll(List<Carro> carros) {
        return carroRepository.saveAll(carros);
    }

    // Listagem de carros com paginação
    public Page<Carro> findAll(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    // Exclusão paralela de carros por lista de IDs
    public CompletableFuture<Void> deleteAllByIds(List<Long> ids) {
        return CompletableFuture.runAsync(() -> {
            ids.parallelStream().forEach(carroRepository::deleteById);
        });
    }
}

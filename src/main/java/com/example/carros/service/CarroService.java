
package com.example.carros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;
import com.google.common.collect.Lists;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    public List<Carro> saveAll(List<Carro> carros) {
        List<List<Carro>> batches = Lists.partition(carros, 50); // Dividir em lotes de 50
        List<Carro> savedCarros = new ArrayList<>();
        for (List<Carro> batch : batches) {
            savedCarros.addAll(carroRepository.saveAll(batch));
        }
        return savedCarros;
    }

    @Cacheable("carros")
    public Page<Carro> findAll(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    public CompletableFuture<Void> deleteAllByIds(List<Long> ids) {
        return CompletableFuture.runAsync(() -> {
            ids.parallelStream().forEach(id -> {
                try {
                    carroRepository.deleteById(id);
                } catch (Exception e) {
                    // Log e gerenciar erros de exclusão individuais
                }
            });
        }).orTimeout(10, TimeUnit.SECONDS);  // Timeout de 10 segundos
    }
       
}
package com.example.carros.service;

import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    public CompletableFuture<Carro> cadastrarCarro(Carro carro) {
        return CompletableFuture.supplyAsync(() -> carroRepository.save(carro));
    }

    @Transactional
    public CompletableFuture<Void> excluirCarro(Long id) {
        return CompletableFuture.runAsync(() -> carroRepository.deleteById(id));
    }
}

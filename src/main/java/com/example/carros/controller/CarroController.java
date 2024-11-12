package com.example.carros.controller;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public CompletableFuture<ResponseEntity<Carro>> cadastrarCarro(@RequestBody Carro carro) {
        return carroService.cadastrarCarro(carro)
                .thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> excluirCarro(@PathVariable Long id) {
        return carroService.excluirCarro(id)
                .thenApply(result -> ResponseEntity.noContent().build());
    }
}

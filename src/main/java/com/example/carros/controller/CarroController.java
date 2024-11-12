
package com.example.carros.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    // Cadastro em lote de carros
    @PostMapping("/batch")
    public ResponseEntity<List<Carro>> saveAll(@Valid @RequestBody List<Carro> carros) {
        List<Carro> savedCarros = carroService.saveAll(carros);
        return new ResponseEntity<>(savedCarros, HttpStatus.CREATED);
    }

    // Listagem de carros com paginação
    @GetMapping
    public ResponseEntity<Page<Carro>> findAll(Pageable pageable) {
        Page<Carro> carros = carroService.findAll(pageable);
        return ResponseEntity.ok(carros);
    }

    // Exclusão paralela de uma lista de carros por IDs
    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteAllByIds(@RequestBody List<Long> ids) {
        CompletableFuture<Void> deleteTask = carroService.deleteAllByIds(ids);
        deleteTask.join();  // Aguarda a conclusão da exclusão
        return ResponseEntity.noContent().build();
    }
}

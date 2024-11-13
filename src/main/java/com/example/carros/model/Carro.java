
package com.example.carros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Carro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A marca não pode estar em branco.")
    private String marca;

    @NotBlank(message = "O modelo não pode estar em branco.")
    private String modelo;

    @Min(value = 1886, message = "O ano deve ser maior ou igual a 1886.")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100.")
    private int ano;

    @NotBlank(message = "A cor não pode estar em branco.")
    private String cor;
    
    // Construtores, getters e setters
    public Carro() {}

    public Carro(String marca, String modelo, int ano, String cor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

}

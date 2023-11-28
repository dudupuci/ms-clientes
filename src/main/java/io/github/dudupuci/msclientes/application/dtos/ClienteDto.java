package io.github.dudupuci.msclientes.application.dtos;

import io.github.dudupuci.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteDto {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(cpf, nome, idade);
    }
}

package io.github.dudupuci.msclientes.services;

import io.github.dudupuci.msclientes.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceInterface {
    Cliente save(Cliente cliente);
    Cliente findById(Long id);
    List<Cliente> findAll();
    Optional<Cliente> findByCpf(String cpf);
}

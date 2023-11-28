package io.github.dudupuci.msclientes.services;

import io.github.dudupuci.msclientes.domain.Cliente;
import io.github.dudupuci.msclientes.infra.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Cliente> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return this.repository.findByCpf(cpf);
    }
}

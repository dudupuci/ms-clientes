package io.github.dudupuci.msclientes.application;

import io.github.dudupuci.msclientes.application.dtos.ClienteDto;
import io.github.dudupuci.msclientes.domain.Cliente;
import io.github.dudupuci.msclientes.domain.exceptions.CPFAlreadyExistsException;
import io.github.dudupuci.msclientes.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientesResource {

    private final ClienteService service;

    @GetMapping
    public String test() {
        log.info("obtendo status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClienteDto dto) {

        if (this.service.findByCpf(dto.getCpf()).isPresent()) {
            throw new CPFAlreadyExistsException("cpf_already_exists");
        }

        var cliente = dto.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Cliente> dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.findByCpf(cpf);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

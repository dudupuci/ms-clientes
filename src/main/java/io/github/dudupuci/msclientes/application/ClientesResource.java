package io.github.dudupuci.msclientes.application;

import io.github.dudupuci.msclientes.application.dtos.ClienteDto;
import io.github.dudupuci.msclientes.domain.Cliente;
import io.github.dudupuci.msclientes.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesResource {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClienteDto dto) {
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

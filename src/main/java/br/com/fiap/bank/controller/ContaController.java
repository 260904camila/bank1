package br.com.fiap.bank.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.apache.catalina.Contained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.repository.ContaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("conta")
@Slf4j
public class ContaController {

    @Autowired // Injeção de Dependência - Inversão de Controle
    ContaRepository repository;
    
    @GetMapping
    public List<Conta> index() {
        return repository.findAll();
    }

    @PostMapping

    @ResponseStatus(CREATED)
    public Conta create(@RequestBody @Valid Conta conta){
        log.info("Cadastrando conta {}", conta);
        return repository.save(conta);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("buscando conta com id {}", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("{/cpf}")
    public ResponseEntity<Conta> show(@PathVariable String cpf){
        log.info("buscando conta com cpf {}", cpf);

        return repository
                .findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("{agencia}/{numero}")
    public ResponseEntity<Conta> show(@PathVariable String numero, @PathVariable String agencia){
        log.info("buscando conta com agencia e numero {}", numero, agencia);

        return repository
                .findByNumeroAndAgencia(numero, agencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("apagando empresa {}", id);
        verificarSeEmpresaExiste(id);
        repository.deleteById(id);
        
    }

    @PutMapping("{id}")
    public Conta update(@PathVariable Long id, @RequestBody Conta empresa){
        log.info("Atualiza empresa {} para {}", id, empresa);

        verificarSeEmpresaExiste(id);
        empresa.setId(id);
        return repository.save(empresa);
    }

    private void verificarSeEmpresaExiste(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe empresa com o id informado"));
    }

    
}

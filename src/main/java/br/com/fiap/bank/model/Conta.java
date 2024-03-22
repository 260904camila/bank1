package br.com.fiap.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Persistent;

import br.com.fiap.bank.validation.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String agencia;
    private String ativa;
    private String numero;


    @NotBlank(message = "{conta.nome.notblank}")
    @Size(min = 3, max = 255)
    private String nome;

    @Pattern(regexp = "/^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/")
    private String cpf;

    @Past(message = "conta.data.past")
    @NotNull
    private LocalDate data_abertura;

    @Positive
    private BigDecimal saldo_inicial;

    @Tipo (message = "{conta.tipo.tipo}")
    private String tipo; // CORRENTE | POUPANÇA | SALÁRIO
    
}




    
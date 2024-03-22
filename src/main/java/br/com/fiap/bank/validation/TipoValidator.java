package br.com.fiap.bank.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoValidator implements ConstraintValidator<Tipo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Adicione aqui os tipos de serviço de casamento que deseja validar
        return value.equals("CORRENTE") || value.equals("POUPANÇA") || value.equals("SALÁRIO");
    }

}

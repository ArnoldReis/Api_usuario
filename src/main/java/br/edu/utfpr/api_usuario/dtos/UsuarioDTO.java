package br.edu.utfpr.api_usuario.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        Long id,

        @NotNull(message = "Nome é obrigatório")
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotNull(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        @NotNull(message = "Senha é obrigatória")
        @Size(min = 6,max = 10, message = "Senha deve ter entre 6 a 10 caracteres")
        String senha
){}
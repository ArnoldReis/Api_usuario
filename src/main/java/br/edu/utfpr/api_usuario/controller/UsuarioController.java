package br.edu.utfpr.api_usuario.controller;

import br.edu.utfpr.api_usuario.model.Usuario;
import br.edu.utfpr.api_usuario.repositories.UsuarioRepository;
import br.edu.utfpr.api_usuario.dtos.UsuarioDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //criando um novo usuario
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(usuarioDTO.nome());
        novoUsuario.setEmail(usuarioDTO.email());
        novoUsuario.setSenha(usuarioDTO.senha());
        novoUsuario.setCreatedAt(LocalDateTime.now());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.created(null).body(novoUsuario);
    }

    //Listando todos os usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> listaDTOs = new ArrayList<>();

        for (Usuario usuario : this.usuarioRepository.findAll()) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha()
            );
            listaDTOs.add(usuarioDTO);
        }

        return ResponseEntity.ok(listaDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getId(@PathVariable(name = "id") Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        
        if (user.isPresent()){
            Usuario usuario = user.get();
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
        return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}


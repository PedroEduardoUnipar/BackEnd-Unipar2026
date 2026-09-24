package br.unipar.backend.minhaapi.controller;

import br.unipar.backend.minhaapi.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    List<Usuario> bancoDados = new ArrayList<>();

    @GetMapping("/hello/{nome}")
    public String helloWorld(@PathVariable String nome){ return "Hello World!!! "+nome; }

    @GetMapping("/hello2")
    public String helloWorld2(@RequestParam(required = false) String nome){ return nome == null ? "Hello World!!" : "Hello World!! "+nome; }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Usuario>> listarTodos(){ return ResponseEntity.ok(bancoDados); }

    @PostMapping("/gravar")
    public String salvarUsuario(@RequestBody(required = false) Usuario usuario){
        if (usuario == null) return "Hello World!!";
        bancoDados.add(usuario);
        return "Hello World!! "+usuario.getNome();
    }

    @PutMapping("/editar/{index}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable int index, @RequestBody(required = false) Usuario usuario){
        if (usuario == null) return ResponseEntity.noContent().build();
        Usuario u = new Usuario();
        u.setId(usuario.getId());
        u.setEmail(usuario.getEmail());
        u.setNome(usuario.getNome());
        bancoDados.set(index,u);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/deletar/{index}")
    public ResponseEntity<String> deletarUsuario(@PathVariable int index){
        bancoDados.remove(index);
        return ResponseEntity.ok("usuario"+index+"deletado");
    }
}

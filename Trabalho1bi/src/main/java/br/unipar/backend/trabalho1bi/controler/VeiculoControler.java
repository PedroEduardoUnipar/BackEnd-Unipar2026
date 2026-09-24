package br.unipar.backend.trabalho1bi.controler;

import br.unipar.backend.trabalho1bi.model.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControler {
    List<Veiculo> bancoDados = new ArrayList<>();

    @GetMapping("/{veiculo}")
    public String veiculo(@PathVariable String veiculo) { return "Hello World!!! " + veiculo; }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Veiculo>> listarTodos() { return ResponseEntity.ok(bancoDados); }

    @PostMapping("/gravar")
    public String salvarVeiculo(@RequestBody(required = false) Veiculo veiculo) {
        if (veiculo == null) return "Veiculo Invalido";
        bancoDados.add(veiculo);
        return veiculo.getNome();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Veiculo> listarID(@PathVariable Long id) {
        for (Veiculo veiculo : bancoDados) if (veiculo.getId() == id) return ResponseEntity.ok(veiculo);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Veiculo> editarVeiculo(@PathVariable int id, @RequestBody(required = false) Veiculo veiculo) {
        if (veiculo == null) return ResponseEntity.noContent().build();
        for (Veiculo u : bancoDados) {
            if (u.getId() == id) {
                u.setNome(veiculo.getNome()); u.setMarca(veiculo.getMarca()); u.setModelo(veiculo.getModelo()); u.setAno(veiculo.getAno());
                return ResponseEntity.ok(u);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Long id) {
        for (int i = 0; i < bancoDados.size(); i++) {
            if (bancoDados.get(i).getId() == id) { bancoDados.remove(i); return ResponseEntity.ok("Veiculo " + id + " deletado"); }
        }
        return ResponseEntity.notFound().build();
    }
}

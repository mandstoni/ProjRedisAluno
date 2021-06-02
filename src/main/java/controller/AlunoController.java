package controller;


import model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AlunoService;

import java.util.List;

@RestController
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/api/aluno")
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @PostMapping("/api/aluno")
    public void save(Aluno aluno){
        aluno.setMedia(calcularMedia(aluno.getP1(), aluno.getP2(), aluno.getP3()));
        alunoService.save(aluno);
    }

    @GetMapping("/api/aluno/{id}")
    @Cacheable("aluno")
    public Aluno findById(@PathVariable("id") String id){
        System.out.println("Find Information.. " + id);
        return alunoService.findById(id);
    }

    public float calcularMedia(float p1, float p2, float p3){
        return ((p1 + p2 + p3) / 3);
    }
}
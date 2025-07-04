package br.com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.web.app.model.Aluno;
import br.com.web.app.model.Nota;
import br.com.web.app.repository.AlunoRepository;

@Controller
public class NotaController {
	
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping("/nota/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") String id, Model model) {
		Aluno aluno = repository.obterAlunoPorId(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("nota", new Nota());
		return "nota/cadastrar";
	}
	
	@PostMapping("/nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
	  Aluno aluno = repository.obterAlunoPorId(id);
	  repository.salvar(aluno.adicionar(aluno, nota));
	  return "redirect:/aluno/listar";
	}
	
	@GetMapping("/nota/iniciarpesquisa")
	public String iniciarPesquisa() {
	  return "nota/pesquisar";
	}
	
	@GetMapping("/nota/pesquisar")
	public String pesquisarPor(@RequestParam("classificacao") String classificacao, @RequestParam("notacorte") String notaCorte, Model model) {
	  List<Aluno> alunos = repository.pesquisarPorClassificacaoENota(classificacao, Double.parseDouble(notaCorte));
	  model.addAttribute("alunos", alunos);
	  return "nota/pesquisar";
	}
}
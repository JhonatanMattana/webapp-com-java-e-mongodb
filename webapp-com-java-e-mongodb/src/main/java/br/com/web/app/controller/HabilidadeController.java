package br.com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.web.app.model.Aluno;
import br.com.web.app.model.Habilidade;
import br.com.web.app.repository.AlunoRepository;

@Controller
public class HabilidadeController {
	
	@Autowired
	private AlunoRepository repository;

	@GetMapping("/habilidade/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") String id, Model model) {
		model.addAttribute("aluno", repository.obterAlunoPorId(id));
		model.addAttribute("habilidade", new Habilidade());
		return "habilidade/cadastrar";
	}
	
	@PostMapping("/habilidade/salvar/{id}")
	public String salvar(@PathVariable("id") String id, @ModelAttribute Habilidade habilidade) {
		Aluno aluno = repository.obterAlunoPorId(id);
		repository.salvar(aluno.adicionar(aluno, habilidade));
		return "redirect:/aluno/listar";
	}
}
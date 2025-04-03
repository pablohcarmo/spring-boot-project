package br.com.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.dao.DaoAluno;
import br.com.projeto.pojo.Aluno;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	private DaoAluno dao = new DaoAluno();

	@GetMapping
	public String showAlunos() {
		return "alunos";
	}

	@GetMapping("/new")
	public String showInsertForm(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "create-aluno";
	}

	@PostMapping("/new")
	public String createAluno(@ModelAttribute Aluno aluno) {
		dao.insert(aluno);
		return "redirect:/alunos";
	}
}
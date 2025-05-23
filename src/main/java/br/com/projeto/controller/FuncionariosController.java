package br.com.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.dao.DaoFuncionario;
import br.com.projeto.pojo.Funcionario;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {
	private DaoFuncionario dao = new DaoFuncionario();

	@GetMapping
	public String showFuncionarios() {
		return "funcionarios";
	}

	@GetMapping("/new")
	public String showInsertForm(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		return "create-funcionario";
	}

	@PostMapping("/new")
	public String createFuncionario(@ModelAttribute Funcionario funcionario) {
		dao.insert(funcionario);
		return "redirect:/funcionarios";
	}
}
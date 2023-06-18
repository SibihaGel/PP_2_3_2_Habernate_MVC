package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.dao.DAO;

import javax.validation.Valid;


@Controller
@RequestMapping()
public class UserController {

	private DAO dao;

	@Autowired
	public UserController(DAO dao) {
		this.dao = dao;
	}

	@GetMapping()
	public String allUser(Model model) {
		model.addAttribute("user", dao.AllUser());
		return "/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", dao.getUserById(id));
		return "/show";
	}

	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		dao.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", dao.getUserById(id));
		return "/edit";
	}

	@PostMapping("/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "/edit";
		dao.updateUser(id, user);
		return "redirect:/";

	}

	@PostMapping(value = "/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		dao.delete(id);
		return "redirect:/";
	}
}




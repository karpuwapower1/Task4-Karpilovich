package by.task4.karpilovich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.task4.karpilovich.service.UserService;

@Controller
public class MainController {

	private static final String USERS_ATTRIBUTE = "users";
	private static final String USER_ID_PARAMETER = "userId";
	private static final String REDIRECT_TO_USERS_PAGE = "redirect:/users";

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String getMainPage(Model model) {
		model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
		return USERS_ATTRIBUTE;
	}

	@PostMapping(path = "/users", params = "action=delete")
	public String deleteUsers(@RequestParam(value = USER_ID_PARAMETER, required = false) Long[] userId) {
		if (userId != null) {
			for (Long id : userId) {
				userService.deleteUser(id);
			}
		}
		return REDIRECT_TO_USERS_PAGE;
	}

	@PostMapping(path = "/users", params = "action=disable")
	public String disableUsers(@RequestParam(value = USER_ID_PARAMETER, required = false) Long[] userId) {
		if (userId != null) {
			for (Long id : userId) {
				userService.disableUser(id);
			}
		}
		return REDIRECT_TO_USERS_PAGE;
	}

	@PostMapping(path = "/users", params = "action=enable")
	public String enableUsers(@RequestParam(value = USER_ID_PARAMETER, required = false) Long[] userId) {
		if (userId != null) {
			for (Long id : userId) {
				userService.enableUser(id);
			}
		}
		return REDIRECT_TO_USERS_PAGE;
	}
}
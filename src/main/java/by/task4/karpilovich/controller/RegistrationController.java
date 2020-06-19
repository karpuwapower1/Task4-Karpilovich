package by.task4.karpilovich.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import by.task4.karpilovich.entity.User;
import by.task4.karpilovich.service.impl.UserServiceImpl;

@Controller
public class RegistrationController {

	private static final String USER_ATTRIBUTE = "user";
	private static final String PASSWORD_ERROR_ATTRIBUTE = "passwordError";
	private static final String PASSWORD_ERROR_MESSAGE = "Passwords must be equal";
	private static final String USERNAME_ERROR_ATTRIBUTE = "usernameError";
	private static final String USERNAME_ERROR_MESSAGE = "User with the name presents";
	private static final String REDIRECT_TO_USERS_PAGE = "redirect:/users";
	private static final String RETURN_TO_REGISTRATION_PAGE = "registration";

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute(USER_ATTRIBUTE, new User());
		return RETURN_TO_REGISTRATION_PAGE;
	}

	@PostMapping("/registration")
	public String registerUser(@ModelAttribute(USER_ATTRIBUTE) @Valid User user, BindingResult bindingResult, Model model) {
		return !checkBindingResultHasError(bindingResult) && checkPasswordsEquality(user, model) && save(user, model)
				? REDIRECT_TO_USERS_PAGE
				: RETURN_TO_REGISTRATION_PAGE;
	}

	private boolean checkBindingResultHasError(BindingResult bindingResult) {
		return bindingResult.hasErrors();
	}

	private boolean checkPasswordsEquality(User user, Model model) {
		if (!user.getPassword().equals(user.getPasswordConfirm())) {
			setPasswordErrorMessage(model);
			return false;
		}
		return true;
	}

	private boolean save(User user, Model model) {
		if (!userService.saveUser(user)) {
			setUsernameErroMessage(model);
			return false;
		}
		return true;
	}

	private void setPasswordErrorMessage(Model model) {
		model.addAttribute(PASSWORD_ERROR_ATTRIBUTE, PASSWORD_ERROR_MESSAGE);
	}

	private void setUsernameErroMessage(Model model) {
		model.addAttribute(USERNAME_ERROR_ATTRIBUTE, USERNAME_ERROR_MESSAGE);
	}
}
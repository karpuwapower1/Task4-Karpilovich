package by.task4.karpilovich.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import by.task4.karpilovich.entity.Role;
import by.task4.karpilovich.entity.User;
import by.task4.karpilovich.repository.RoleRepository;
import by.task4.karpilovich.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private SessionRegistry registry;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	public User findUserById(Long userId) {
		Optional<User> userFromDb = userRepository.findById(userId);
		return userFromDb.orElse(new User());
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public boolean saveUser(User user) {
		User userFromDB = userRepository.findByUsername(user.getUsername());
		if (userFromDB == null) {
			save(user);
			return true;
		}
		return false;
	}

	public void deleteUser(Long userId) {
		if (userRepository.findById(userId).isPresent()) {
			userRepository.deleteById(userId);
			terminateUserSession(userId);
		}
	}

	public void blockUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			updateUserActivity(optionalUser.get(), false);
			terminateUserSession(userId);
		}
	}

	public void unblockUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			updateUserActivity(optionalUser.get(), true);
		}
	}

	public void updateUserLoginDate(String username) {
		User user = userRepository.findByUsername(username);
		user.setLastLoginDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		updateUser(user);
	}

	private void updateUserActivity(User user, boolean activity) {
		user.setNonLocked(activity);
		updateUser(user);
	}

	private void save(User user) {
		user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRegistrationDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		user.setNonLocked(true);
		updateUser(user);
	}

	private void updateUser(User user) {
		userRepository.save(user);
	}

	private void terminateUserSession(Long id) {
		final List<Object> allPrincipals = registry.getAllPrincipals();
		for (Object principal : allPrincipals) {
			if (isPrincipalUser(principal) && ((User) principal).getId().equals(id)) {
				terminateUserSessions(registry.getAllSessions(principal, false));
			}
		}
	}

	private boolean isPrincipalUser(Object principal) {
		return principal instanceof User;
	}

	private void terminateUserSessions(List<SessionInformation> sessionInformations) {
		for (SessionInformation sessionInformation : sessionInformations) {
			sessionInformation.expireNow();
			sessionInformation.refreshLastRequest();
		}
	}
}
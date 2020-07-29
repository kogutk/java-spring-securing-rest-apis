package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
		this.resolutions = resolutions;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));
		this.resolutions.save(new Resolution("1 Read War and Peace", "user1"));
		this.resolutions.save(new Resolution("1 Free Solo the Eiffel Tower", "user1"));

		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		admin.grantAuthority("ROLE_ADMIN");
		admin.grantAuthority("resolution:read");
		admin.grantAuthority("resolution:write");
		this.users.save(admin);



		User user = new User("user"
				, "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		user.grantAuthority("resolution:read");
		user.grantAuthority("resolution:write");
		this.users.save(user);

		User user1 = new User("user1"
				, "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		user1.grantAuthority("resolution:read");
		user1.grantAuthority("resolution:write");
		this.users.save(user1);

		User hasread = new User();
		hasread.setUsername("hasread");
		hasread.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User();
		haswrite.setUsername("haswrite");
		haswrite.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);

	}
}

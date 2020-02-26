package co.worker.board;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity superUser = new UserEntity();
        superUser.setUserId("super");
        superUser.setName("super");
        superUser.setPassword(passwordEncoder.encode("super"));
        userRepository.save(superUser);
    }
}

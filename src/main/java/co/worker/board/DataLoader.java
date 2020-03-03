package co.worker.board;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.board.repository.BoardRepository;
import co.worker.board.configuration.enums.AuthorityType;
import co.worker.board.user.model.UserEntity;
import co.worker.board.user.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private BoardRepository boardRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, BoardRepository boardRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==============================DataLoader Start==============================");
        UserEntity superUser = new UserEntity();
        superUser.setUserId("super");
        superUser.setName("super");
        superUser.setPassword(passwordEncoder.encode("super"));
        superUser.setRole(AuthorityType.ROLE_ADMIN.getValue());
        superUser.setSavedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        userRepository.save(superUser);

        //유저 삽입 (2번, 3번 유저)
        userRepository.save(UserEntity.builder().userId("doqndnf").name("유저").password(passwordEncoder.encode("tjdghks1!")).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build());
        userRepository.save(UserEntity.builder().userId("doqndnf2").name("유저2").password(passwordEncoder.encode("tjdghks2@")).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build());

        //게시판 삽입(2번 3번 유저가 작성한 글 20개)
        for(int i =1; i<=20; i++){
            Optional<UserEntity> user = null;
            if(i%2==0){
                user = userRepository.findById(2L);
            }else{
                user = userRepository.findById(3L);
            }
            BoardEntity boardEntity = BoardEntity.builder().content("내용"+i).title("제목"+i).userEntity(user.get()).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
            boardRepository.save(boardEntity);
        }

        System.out.println("==============================DataLoader End================================");
    }
}

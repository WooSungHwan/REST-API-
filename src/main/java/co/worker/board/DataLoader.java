package co.worker.board;

import co.worker.board.domain.board.model.BoardEntity;
import co.worker.board.domain.board.repository.BoardRepository;
import co.worker.board.configuration.enums.AuthorityType;
import co.worker.board.domain.favorite.model.FavoriteEntity;
import co.worker.board.domain.favorite.repository.FavoriteRepository;
import co.worker.board.domain.reply.model.ReplyEntity;
import co.worker.board.domain.reply.repository.ReplyRepository;
import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.domain.user.repository.UserRepository;
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
    private ReplyRepository replyRepository;
    private FavoriteRepository favoriteRepository;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, BoardRepository boardRepository, ReplyRepository replyRepository, FavoriteRepository favoriteRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.favoriteRepository = favoriteRepository;
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
            Optional<UserEntity> user;
            ReplyEntity replyEntity;
            BoardEntity boardEntity;
            if(i%2==0){
                user = userRepository.findById(2L);
                boardEntity = BoardEntity.builder().content("내용"+i).title("제목"+i).userEntity(user.get()).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
                replyEntity = ReplyEntity.builder().content("댓글입니다..."+i).user(user.get()).saved_time(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).boardSeq(2L).build();
            }else{
                user = userRepository.findById(3L);
                boardEntity = BoardEntity.builder().content("내용"+i).title("제목"+i).userEntity(user.get()).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
                replyEntity = ReplyEntity.builder().content("댓글입니다..."+i).user(user.get()).saved_time(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).boardSeq(3L).build();
            }
            boardRepository.save(boardEntity);
            replyRepository.save(replyEntity);
        }

        favoriteRepository.save(FavoriteEntity.builder().boardSeq(4L).userSeq(2L).build());
        favoriteRepository.save(FavoriteEntity.builder().boardSeq(4L).userSeq(3L).build());
        favoriteRepository.save(FavoriteEntity.builder().boardSeq(5L).userSeq(3L).build());

        System.out.println("==============================DataLoader End================================");
    }
}

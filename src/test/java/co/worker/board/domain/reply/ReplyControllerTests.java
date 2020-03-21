package co.worker.board.domain.reply;

import co.worker.board.domain.reply.model.ReplyEntity;
import co.worker.board.domain.reply.model.ReplyParam;
import co.worker.board.domain.reply.repository.ReplyRepository;
import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.domain.user.model.UserParam;
import co.worker.board.domain.user.repository.UserRepository;
import co.worker.board.util.TypeChange;
import co.worker.board.util.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReplyControllerTests {

    MockMvc mockMvc;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ReplyRepository replyRepository;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    private RestDocumentationResultHandler document;

    @Before
    public void insert(){
        this.document = document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document).build();

        Optional<UserEntity> user;
        for(int i = 1; i<=20; i++){
            ReplyEntity replyEntity;
            if(i%2==0){
                user = userRepository.findById(2L);
                replyEntity = ReplyEntity.builder().content("댓글입니다..."+i).user(user.get()).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).boardSeq(2L).build();
            }else{
                user = userRepository.findById(3L);
                if(user.isPresent())
                    replyEntity = ReplyEntity.builder().content("댓글입니다..."+i).user(user.get()).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).boardSeq(3L).build();
                else
                    continue;
            }
            replyRepository.save(replyEntity);
        }
    }

    @Test
    public void add() throws Exception {
        UserEntity userEntity = userRepository.findById(2L).get();
        UserParam param = UserParam.builder()
                .name(userEntity.getName()).password(userEntity.getPassword()).userId(userEntity.getUserId())
                .savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).role(userEntity.getRole()).seq(userEntity.getSeq()).build();

        ReplyParam replyParam = ReplyParam.builder().content("댓글추가합니다.").boardSeq(5L).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .user(param).build();

        mockMvc.perform(post("/api/replies/add")
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8")
                .content(objectMapper.writeValueAsString(replyParam)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        ReplyParam replyParam = TypeChange.sourceToDestination(replyRepository.findById(4L).get(), new ReplyParam());
        replyParam.setContent("수정된 댓글");
        replyParam.setSavedTime(LocalDateTime.now(ZoneId.of(Word.KST)));

        mockMvc.perform(put("/api/replies/edit")
                .accept("application/json;charset=utf-8")
                .contentType("application/json;charset=utf-8")
                .content(objectMapper.writeValueAsString(replyParam)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteNotExistOne() throws Exception {
        mockMvc.perform(delete("/api/replies/{seq}", 200)
                .accept("application/json;charset=utf-8")
                .contentType("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteExistOne() throws Exception {
        mockMvc.perform(delete("/api/replies/{seq}", 2)
                .accept("application/json;charset=utf-8")
                .contentType("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getRepliesByBoardSeq() throws Exception {

        mockMvc.perform(get("/api/replies/board/2/0/10")
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

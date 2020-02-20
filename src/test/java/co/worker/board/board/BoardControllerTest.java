package co.worker.board.board;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.board.model.BoardParam;
import co.worker.board.board.repository.BoardRepository;
import co.worker.board.user.model.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardControllerTest {
    MockMvc mockMvc;
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Rule public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(); // (1)
    @Autowired private WebApplicationContext context;
    private RestDocumentationResultHandler document;



    @Before
    public void setUpAndInsertBoard(){
        this.document = document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document).build();

        for(int i =1; i<=20; i++){
            UserEntity user = UserEntity.builder().id("doqndnf"+i).name("유저"+i).password("tjdghks"+i+"!").build();
            BoardEntity boardEntity = BoardEntity.builder().content("내용"+i).title("제목"+i).userEntity(user).build();
            boardRepository.save(boardEntity);
        }
    }

    @Test
    public void addBoard() throws Exception {
        UserEntity user = UserEntity.builder().id("addId").name("유저추가").password("비밀번호").build();

        BoardParam param = BoardParam.builder()
                .content("추가내용")
                .title("추가제목")
                .user(user)
                .build();

        mockMvc.perform(post("/api/boards/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        this.getBoard();
    }

    @Test
    public void editBoard() throws Exception{
        UserEntity user = UserEntity.builder().id("addId").name("유저추가").password("비밀번호").build();

        BoardParam param = BoardParam.builder()
                .content("수정내용")
                .title("수정제목")
                .user(user)
                .build();

        mockMvc.perform(put("/api/boards/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
        this.getBoard();
    }

    @Test
    public void getBoard() throws Exception {
        mockMvc.perform(get("/api/boards/list/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBoardOne1() throws Exception {
        mockMvc.perform(get("/api/boards/{seq}", 1)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBoardOne2() throws Exception {
        mockMvc.perform(get("/api/boards/{seq}", 1)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("seq").description("Board`s seq")
                        ),
                        responseFields(
                                fieldWithPath("code").description("The HttpStatus code"),
                                fieldWithPath("message").description("The Http`s message"),
                                fieldWithPath("result.seq").description("The Board`s seq"),
                                fieldWithPath("result.content").description("The Board`s content"),
                                fieldWithPath("result.title").description("The Board`s title"),
                                fieldWithPath("result.user.seq").description("The Board`s user seq"),
                                fieldWithPath("result.user.id").description("The Board`s user id"),
                                fieldWithPath("result.user.name").description("The Board`s user name"),
                                fieldWithPath("result.user.savedTime").description("The Board`s user regdate"),
                                fieldWithPath("result.savedTime").description("The Board`s regdate")
                        )
                ))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.result.content", is(notNullValue())))
                .andExpect(jsonPath("$.result.user.seq", is(notNullValue())))
                .andExpect(jsonPath("$.result.user.id", is(notNullValue())))
                .andExpect(jsonPath("$.result.user.name", is(notNullValue())))
                .andExpect(jsonPath("$.result.user.savedTime", is(notNullValue())))
                .andExpect(jsonPath("$.result.title", is(notNullValue())))
                .andExpect(jsonPath("$.result.savedTime", is(notNullValue())))
        ;
    }

    @Test
    public void deleteBoardOne() throws Exception{
        mockMvc.perform(delete("/api/boards/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        this.getBoard();
    }

    //Bad_Request 테스트
    @Test //add
    public void board_BadRequest_add() throws Exception{
        //BoardParam param = BoardParam.builder().content("test").title("test").build(); //username not null

        UserEntity user = UserEntity.builder().id("addId").name("유저추가").password("비밀번호").build();
        BoardParam param = BoardParam.builder().title("title").content("").user(user).build(); // content not empty

        mockMvc.perform(post("/api/boards/add")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void board_BadRequest_getOne() throws Exception{ // 문제 수정해야됨.
        // 400이 나와야하는데 200이 나옴. -> ok메소드를 실행해서 그럼 -> 오류 -> 400이 나오도록 수정.
        mockMvc.perform(get("/api/boards/-1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void board_BadRequest_edit() throws Exception {
        BoardParam param = BoardParam.builder().content("test").title("test").build(); //  username null
        //BoardParam param = BoardParam.builder().content("test").title("test").user(user).build(); // seq min 0
        //UserEntity user = UserEntity.builder().id("addId").name("유저추가").password("비밀번호").build();
        mockMvc.perform(put("/api/boards/0")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
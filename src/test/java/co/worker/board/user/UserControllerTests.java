package co.worker.board.user;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserParam;
import co.worker.board.user.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

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

        for(int i =1; i<=10; i++){
            UserEntity entity = UserEntity.builder().password("비밀번호"+i).name("이름"+i).id("아이디"+i).savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
            userRepository.save(entity);
        }
    }

    @Test
    public void add() throws Exception {
        UserParam param = UserParam.builder().id("아이디추가").name("이름추가").password("비밀번호추가").savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();

        mockMvc.perform(post("/api/users/add")
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8")
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                    requestFields(
                            fieldWithPath("seq").type(JsonFieldType.NULL).description("유저 시퀀스"),
                            fieldWithPath("id").type(JsonFieldType.STRING).description("유저 아이디"),
                            fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                            fieldWithPath("password").type(JsonFieldType.STRING).description("유저 비밀번"),
                            fieldWithPath("savedTime").type(JsonFieldType.STRING).description("유저 가입일").attributes(new Attributes.Attribute("format","yyyy-MM-dd HH:mm:ss"))
                    ),
                    responseFields(
                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("result").type(JsonFieldType.NULL).description("결과 JSON")
                    )))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
        ;
    }

    @Test
    public void edit() throws Exception{
        UserParam param = UserParam.builder().id("아이디수정").name("이름수정").password("비밀번호수정").savedTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();

        mockMvc.perform(put("/api/users/edit/{seq}", 3)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8")
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("seq").description("유저 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("Http 상태값"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("성공유무 메시지"),
                                fieldWithPath("result").type(JsonFieldType.NULL).description("결과값")
                        )))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
        ;
    }

    @Test
    public  void getUserOne() throws Exception {
        mockMvc.perform(get("/api/users/{seq}", 1)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("seq").description("유저 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("Http 상태값"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("성공유무 메시지"),
                                fieldWithPath("result.seq").type(JsonFieldType.NUMBER).description("유저 시퀀스"),
                                fieldWithPath("result.id").type(JsonFieldType.STRING).description("유저 시퀀스"),
                                fieldWithPath("result.name").type(JsonFieldType.STRING).description("유저 시퀀스"),
                                fieldWithPath("result.password").type(JsonFieldType.STRING).description("유저 시퀀스"),
                                fieldWithPath("result.savedTime").type(JsonFieldType.STRING).description("유저 시퀀스").attributes(new Attributes.Attribute("format", "yyyy-MM-dd HH:mm:ss"))
                        )))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.result.seq", is(notNullValue())))
                .andExpect(jsonPath("$.result.id", is(notNullValue())))
                .andExpect(jsonPath("$.result.name", is(notNullValue())))
                .andExpect(jsonPath("$.result.password", is(notNullValue())))
                .andExpect(jsonPath("$.result.savedTime", is(notNullValue())))
        ;
    }

    @Test
    public void getUserAll() throws Exception{
        mockMvc.perform(get("/api/users/all")
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception{
        mockMvc.perform(delete("/api/users/{seq}", 4)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("seq").description("유저 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("Http 상태값"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("성공유무 메시지"),
                                fieldWithPath("result").type(JsonFieldType.NULL).description("결과값")

                        )
                ))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
        ;
    }

    @Test
    public void deleteUser_not_seq_exist() throws Exception{
        mockMvc.perform(delete("/api/users/{seq}", 4000)
                .contentType("application/json;charset=utf-8")
                .accept("application/json;charset=utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andDo(document.document(
                        pathParameters(
                                parameterWithName("seq").description("유저 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("Http 상태값"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("성공유무 메시지")
                        )
                ))
                .andExpect(jsonPath("$.code", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(notNullValue())))
        ;
    }
}
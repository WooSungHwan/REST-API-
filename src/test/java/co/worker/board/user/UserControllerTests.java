package co.worker.board.user;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserParam;
import co.worker.board.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Before
    public void insert(){
        for(int i =1; i<=10; i++){
            UserEntity entity = UserEntity.builder().password("비밀번호"+i).name("이름"+i).id("아이디"+i).build();
            userRepository.save(entity);
        }
    }

    @Test
    public void add() throws Exception {
        UserParam param = UserParam.builder().id("아이디추가").name("이름추가").password("비밀번호추가").build();

        mockMvc.perform(post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception{
        UserParam param = UserParam.builder().id("아이디수정").name("이름수정").password("비밀번호수정").build();

        mockMvc.perform(put("/api/users/edit/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public  void getUserOne() throws Exception {
        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserAll() throws Exception{
        mockMvc.perform(get("/api/users/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception{
        mockMvc.perform(delete("/api/users/4")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        this.getUserAll();
    }
}
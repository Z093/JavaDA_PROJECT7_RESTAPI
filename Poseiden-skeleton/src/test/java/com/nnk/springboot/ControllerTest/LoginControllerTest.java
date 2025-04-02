package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        Mockito.when(userRepository.findAll()).thenReturn(java.util.Collections.emptyList());
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void login_shouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Please sign in")));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void articleDetails_shouldReturnUserListView() throws Exception {
        mockMvc.perform(get("/secure/article-details"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("user/list"));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void error_shouldReturn403ViewWithMessage() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorMsg"))
                .andExpect(view().name("403"));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void accessDenied_shouldReturn403View() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"));
    }
}

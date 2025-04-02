package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RuleNameController.class)
@WithMockUser
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    private RuleName rule;

    @BeforeEach
    void setup() {
        rule = new RuleName();
        rule.setId(1);
        rule.setName("Rule Test");
        rule.setDescription("Test description");
        rule.setJson("json");
        rule.setTemplate("template");
        rule.setSqlStr("SELECT * FROM test");
        rule.setSqlPart("WHERE x = 1");
    }

    @Test
    void home_shouldReturnListView() throws Exception {
        Mockito.when(ruleNameService.findAll()).thenReturn(Arrays.asList(rule));
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"));
    }

    @Test
    void addForm_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validate_shouldRedirectWhenValid() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                        .param("name", "Rule Test")
                        .param("description", "Description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "SELECT *")
                        .param("sqlPart", "WHERE id = 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void updateForm_shouldReturnUpdateView() throws Exception {
        Mockito.when(ruleNameService.findById(1)).thenReturn(Optional.of(rule));

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    void update_shouldRedirectWhenValid() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .with(csrf())
                        .param("name", "Updated Rule")
                        .param("description", "Updated Description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "SELECT *")
                        .param("sqlPart", "WHERE id = 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void delete_shouldRedirect() throws Exception {
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
}


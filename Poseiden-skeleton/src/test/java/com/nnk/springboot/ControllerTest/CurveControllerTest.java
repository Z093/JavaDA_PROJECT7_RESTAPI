package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = com.nnk.springboot.controllers.CurveController.class)
@WithMockUser(username = "user", roles = "USER")
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    private CurvePoint curvePoint;

    @BeforeEach
    void setup() {
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(20.0);
        curvePoint.setValue(30.0);
    }

    @Test
    void home_shouldReturnListView() throws Exception {
        when(curvePointService.findAll()).thenReturn(Arrays.asList(curvePoint));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"))
                .andExpect(model().attributeExists("username"));
    }

    @Test
    void addForm_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validate_shouldRedirectOnSuccess() throws Exception {
        when(curvePointService.save(any())).thenReturn(curvePoint);

        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("curveId", "10")
                        .param("term", "20.0")
                        .param("value", "30.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void validate_shouldReturnToAddOnError() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("curveId", "") // invalid
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void showUpdateForm_shouldReturnUpdateView() throws Exception {
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    void updateBid_shouldRedirectOnSuccess() throws Exception {
        when(curvePointService.save(any())).thenReturn(curvePoint);

        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf())
                        .param("id", "1")
                        .param("curveId", "10")
                        .param("term", "20.0")
                        .param("value", "30.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void updateBid_shouldReturnToUpdateOnError() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf())
                        .param("curveId", "") // invalid
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void deleteBid_shouldRedirectToList() throws Exception {
        doNothing().when(curvePointService).deleteById(1);

        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }
}

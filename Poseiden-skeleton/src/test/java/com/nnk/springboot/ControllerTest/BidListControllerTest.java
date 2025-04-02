package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    private BidList bid;

    @BeforeEach
    void setUp() {
        bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("TestAccount");
        bid.setType("TestType");
        bid.setBidQuantity(10.0);
    }

    @WithMockUser
    @Test
    void home_shouldReturnBidListView() throws Exception {
        when(bidListService.findAll()).thenReturn(Arrays.asList(bid));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));
    }

    @WithMockUser
    @Test
    void addBidForm_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @WithMockUser
    @Test
    void validate_shouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "Account")
                        .param("type", "Type")
                        .param("bidQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(ArgumentMatchers.any(BidList.class));
    }

    @WithMockUser
    @Test
    void validate_shouldReturnToAddOnError() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "") // Invalid
                        .param("type", "")
                        .param("bidQuantity", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @WithMockUser
    @Test
    void showUpdateForm_shouldReturnUpdateView() throws Exception {
        when(bidListService.findById(1)).thenReturn(Optional.of(bid));

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }

    @WithMockUser
    @Test
    void updateBid_shouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "UpdatedAccount")
                        .param("type", "UpdatedType")
                        .param("bidQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(ArgumentMatchers.any(BidList.class));
    }

    @WithMockUser
    @Test
    void updateBid_shouldReturnToUpdateOnError() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "") // Invalid
                        .param("type", "")
                        .param("bidQuantity", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @WithMockUser
    @Test
    void deleteBid_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).deleteById(1);
    }
}

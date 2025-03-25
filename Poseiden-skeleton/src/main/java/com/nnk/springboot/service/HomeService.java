package com.nnk.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getHomeView() {
        return "home";
    }

    public String getAdminHomeRedirect() {
        return "redirect:/bidList/list";
    }
}

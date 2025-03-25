package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;

import com.nnk.springboot.service.BidListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BidListController {

    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.save(bid);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id: " + id));
        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }

        bidList.setBidListId(id);
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}

package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Controller for managing BidList entities.
 */
@Controller
public class BidListController {

    private final BidListService bidListService;

    @Autowired
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * Display a list of all BidList entities.
     *
     * @param model the Model to use for rendering the view
     * @return the view to render
     */
    @RequestMapping("/bidList/list")
    public String home(Model model, HttpServletRequest request)
    {
        model.addAttribute("bidLists", bidListService.findAll());
        model.addAttribute("username", request.getRemoteUser());
        return "bidList/list";
    }

    /**
     * Display a form for creating a new BidList entity.
     *
     * @param bid the BidList entity to use for rendering the form
     * @return the view to render
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Process the creation of a new BidList entity.
     *
     * @param bid the BidList entity to create
     * @param result the BindingResult to use for validation
     * @param model the Model to use for rendering the view
     * @return the view to render
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bid);
        return "redirect:/bidList/list";
    }

    /**
     * Display a form for updating an existing BidList entity.
     *
     * @param id the ID of the BidList entity to update
     * @param model the Model to use for rendering the view
     * @return the view to render
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<BidList> bidList = bidListService.findById(id);
        if (bidList.isPresent()) {
            model.addAttribute("bidList", bidList.get());
            return "bidList/update";
        }
        return "redirect:/bidList/list";
    }

    /**
     * Process the update of an existing BidList entity.
     *
     * @param id the ID of the BidList entity to update
     * @param bidList the BidList entity to update
     * @param result the BindingResult to use for validation
     * @param model the Model to use for rendering the view
     * @return the view to render
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Delete an existing BidList entity.
     *
     * @param id the ID of the BidList entity to delete
     * @param model the Model to use for rendering the view
     * @return the view to render
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}

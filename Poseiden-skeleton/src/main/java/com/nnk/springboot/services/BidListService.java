package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BidListService {

    private final BidListRepository bidListRepository;

    @Autowired
    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}

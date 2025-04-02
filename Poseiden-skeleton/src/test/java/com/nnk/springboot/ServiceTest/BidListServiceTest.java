package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    private BidList bid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10.0);
    }

    @Test
    void findAll_shouldReturnAllBidLists() {
        List<BidList> bids = Arrays.asList(bid);
        when(bidListRepository.findAll()).thenReturn(bids);

        List<BidList> result = bidListService.findAll();

        assertThat(result).hasSize(1);
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void save_shouldReturnSavedBidList() {
        when(bidListRepository.save(bid)).thenReturn(bid);

        BidList result = bidListService.save(bid);

        assertThat(result).isEqualTo(bid);
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    void findById_shouldReturnOptionalBidList() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        Optional<BidList> result = bidListService.findById(1);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bid);
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        doNothing().when(bidListRepository).deleteById(1);

        bidListService.deleteById(1);

        verify(bidListRepository, times(1)).deleteById(1);
    }
}


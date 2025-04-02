package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    private RatingRepository ratingRepository;
    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingRepository = mock(RatingRepository.class);
        ratingService = new RatingService(ratingRepository);
    }

    @Test
    void findAll_shouldReturnAllRatings() {
        // Given
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        // When
        List<Rating> result = ratingService.findAll();

        // Then
        assertThat(result).hasSize(2);
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void save_shouldSaveRating() {
        // Given
        Rating rating = new Rating();
        rating.setMoodysRating("Aaa");
        rating.setOrderNumber(1);

        when(ratingRepository.save(rating)).thenReturn(rating);

        // When
        Rating saved = ratingService.save(rating);

        // Then
        assertThat(saved).isEqualTo(rating);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void findById_shouldReturnRating() {
        // Given
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        // When
        Optional<Rating> result = ratingService.findById(1);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    void deleteById_shouldDeleteRating() {
        // Given
        Integer id = 1;

        // When
        ratingService.deleteById(id);

        // Then
        verify(ratingRepository, times(1)).deleteById(id);
    }
}

package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CurvePointServiceTest {

    private CurvePointRepository curvePointRepository;
    private CurvePointService curvePointService;

    @BeforeEach
    void setUp() {
        curvePointRepository = mock(CurvePointRepository.class);
        curvePointService = new CurvePointService(curvePointRepository);
    }

    @Test
    void findAll_shouldReturnAllCurvePoints() {
        // Given
        CurvePoint cp1 = new CurvePoint();
        CurvePoint cp2 = new CurvePoint();
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(cp1, cp2));

        // When
        List<CurvePoint> result = curvePointService.findAll();

        // Then
        assertThat(result).hasSize(2);
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void save_shouldSaveCurvePoint() {
        // Given
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(100.0);

        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        // When
        CurvePoint saved = curvePointService.save(curvePoint);

        // Then
        assertThat(saved).isEqualTo(curvePoint);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void findById_shouldReturnCurvePoint() {
        // Given
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        // When
        Optional<CurvePoint> result = curvePointService.findById(1);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        // Given
        Integer idToDelete = 1;

        // When
        curvePointService.deleteById(idToDelete);

        // Then
        verify(curvePointRepository, times(1)).deleteById(idToDelete);
    }
}

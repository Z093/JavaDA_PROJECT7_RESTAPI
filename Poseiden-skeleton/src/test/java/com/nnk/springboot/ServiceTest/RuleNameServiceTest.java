package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RuleNameServiceTest {

    private RuleNameRepository ruleNameRepository;
    private RuleNameService ruleNameService;

    @BeforeEach
    void setUp() {
        ruleNameRepository = mock(RuleNameRepository.class);
        ruleNameService = new RuleNameService(ruleNameRepository);
    }

    @Test
    void findAll_shouldReturnAllRuleNames() {
        // Given
        RuleName rule1 = new RuleName();
        RuleName rule2 = new RuleName();
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(rule1, rule2));

        // When
        List<RuleName> result = ruleNameService.findAll();

        // Then
        assertThat(result).hasSize(2);
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void save_shouldSaveRuleName() {
        // Given
        RuleName rule = new RuleName();
        rule.setName("Rule 1");
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        // When
        RuleName saved = ruleNameService.save(rule);

        // Then
        assertThat(saved).isEqualTo(rule);
        verify(ruleNameRepository, times(1)).save(rule);
    }

    @Test
    void findById_shouldReturnRuleName() {
        // Given
        RuleName rule = new RuleName();
        rule.setId(1);
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        // When
        Optional<RuleName> result = ruleNameService.findById(1);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void deleteById_shouldDeleteRuleName() {
        // Given
        Integer id = 1;

        // When
        ruleNameService.deleteById(id);

        // Then
        verify(ruleNameRepository, times(1)).deleteById(id);
    }
}

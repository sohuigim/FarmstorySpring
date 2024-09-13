package com.farmstory.service;

import com.farmstory.dto.TermsDTO;
import com.farmstory.entity.Terms;
import com.farmstory.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TermsService {
    private final TermsRepository termsRepository;

    public List<TermsDTO> selectTerms(){
        List<Terms> termsall = termsRepository.findAll();
        List<TermsDTO> termsDTO = termsall
                .stream()
                .map(entity -> entity.toDTO())
                .collect(Collectors.toList());
        return termsDTO;
    }
}

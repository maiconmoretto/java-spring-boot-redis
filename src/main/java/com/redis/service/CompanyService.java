package com.redis.service;

import br.com.emmanuelneri.controller.exceptions.EntityNotFoundException;
import br.com.emmanuelneri.model.Company;
import br.com.emmanuelneri.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class CompanyService {
 
    @Autowired
    private CompanyRepository companyRepository;
 
    @Cacheable(cacheNames = "Company", key="#root.method.name")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    
    @Cacheable(cacheNames = "Company", key="#identifier")
    public Company findbyIdentifier(final String identifier) {
        return companyRepository.findById(identifier)
                .orElseThrow(() -> new EntityNotFoundException("Identifier not found: " + identifier));
    }
 

}
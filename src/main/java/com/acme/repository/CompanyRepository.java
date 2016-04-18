package com.acme.repository;

import com.acme.types.Company;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdraraujo on 16-04-18.
 */
public interface CompanyRepository extends JpaRepository<Company, String> {
}

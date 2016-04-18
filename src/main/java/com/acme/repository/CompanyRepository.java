package com.acme.repository;

import com.acme.types.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bdraraujo on 16-04-18.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}

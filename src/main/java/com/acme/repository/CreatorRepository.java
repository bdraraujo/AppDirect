package com.acme.repository;

import com.acme.types.Creator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bdraraujo on 16-04-16.
 */
@Repository
public interface CreatorRepository extends JpaRepository<Creator, String> {
}

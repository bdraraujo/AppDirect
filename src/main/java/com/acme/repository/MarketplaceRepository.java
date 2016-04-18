package com.acme.repository;

import com.acme.types.Marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bdraraujo on 16-04-16.
 */
@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, String> {
}

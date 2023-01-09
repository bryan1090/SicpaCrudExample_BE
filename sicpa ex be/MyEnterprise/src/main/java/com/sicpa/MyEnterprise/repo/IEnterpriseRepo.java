package com.sicpa.MyEnterprise.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicpa.MyEnterprise.model.Enterprise;
@Repository
public interface IEnterpriseRepo extends JpaRepository<Enterprise,BigInteger>{

}

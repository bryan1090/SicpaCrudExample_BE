package com.sicpa.MyEnterprise.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicpa.MyEnterprise.model.Employee;
import com.sicpa.MyEnterprise.model.Enterprise;
@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,BigInteger>{

}

package com.expense.service.repository;

import com.expense.service.entities.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
    List<ExpenseEntity> findByUserId(String id);
    Optional<ExpenseEntity> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime , Timestamp endTime);
    Optional<ExpenseEntity> findByUserIdAndExternalId(String userId , String externalId);


}

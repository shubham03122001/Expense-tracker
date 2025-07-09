package com.expense.service.service;


import com.expense.service.dto.ExpenseDto;
import com.expense.service.entities.ExpenseEntity;
import com.expense.service.repository.ExpenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto){
        setCurrency(expenseDto);
        try{
            expenseRepository.save(objectMapper.convertValue(expenseDto , ExpenseEntity.class));
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateExpense(ExpenseDto expenseDto){
        Optional<ExpenseEntity> expenseFoundOptional = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(), expenseDto.getExternalId());
        if(expenseFoundOptional.isEmpty()){
            return false;
        }
        ExpenseEntity expenseEntity = expenseFoundOptional.get();

        expenseEntity.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())?expenseDto.getCurrency():expenseEntity.getCurrency());
        expenseEntity.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())?expenseDto.getMerchant():expenseEntity.getMerchant());
        expenseEntity.setAmount(expenseDto.getAmount());
        expenseRepository.save(expenseEntity);
        return true;


    }

    public List<ExpenseDto> getExpenses(String userId){
//        Optional<ExpenseEntity> userIdOptional = expenseRepository.findByUserId(userId);
//        if(userIdOptional.isEmpty()){
//            System.out.println("User with this ID not found ");
//        }
//
//        if(userIdOptional.isPresent()){
//            ExpenseEntity expenseEntity = userIdOptional.get();
//            return List<ExpenseEntity>
//        }

        List<ExpenseEntity> expenseList = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseList , new TypeReference<List<ExpenseDto>>(){});

    }

    public void setCurrency(ExpenseDto expenseDto){
        if(Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("INR");
        }
    }




}

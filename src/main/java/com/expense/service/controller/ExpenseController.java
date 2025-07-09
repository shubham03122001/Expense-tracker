package com.expense.service.controller;


import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ExpenseController {
     private final ExpenseService expenseService;

     ExpenseController(ExpenseService expenseService){
         this.expenseService = expenseService;
     }

     @GetMapping("/expense/v1")
    public ResponseEntity<List<ExpenseDto>> getExpenses(@PathParam("user_id") @NonNull String userId){
         try{
             List<ExpenseDto> expenses = expenseService.getExpenses(userId);
             return new ResponseEntity<>(expenses , HttpStatus.OK);

         } catch (Exception e) {
             System.out.println("Exception occured while getting the expenses"+e);
             return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            // throw new RuntimeException(e);
         }
     }

     @PostMapping("/expense/v1/createExpence")
     public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){
         try{
             boolean expense = expenseService.createExpense(expenseDto);
             if(expense){
                 return new ResponseEntity<>(expenseDto , HttpStatus.OK);
             }else {
                 return new ResponseEntity<>(null , HttpStatus.UNPROCESSABLE_ENTITY);
             }

         }catch (Exception e){
             log.error("Failed to create the expense",e);
             return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

         }

     }
     @PutMapping("/expense/v1/updateExpense")
    public ResponseEntity<?> updateExpense(@RequestBody ExpenseDto expenseDto){
         boolean b = expenseService.updateExpense(expenseDto);
         if(b){
             return new ResponseEntity<>("User updated successfully",HttpStatus.OK);
         }else{
             return new ResponseEntity<>("Failed to update the user",HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }



}

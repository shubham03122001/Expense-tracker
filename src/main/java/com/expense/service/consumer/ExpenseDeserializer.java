package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public ExpenseDto deserialize(String topic, byte[] argument1){
        ExpenseDto expenseDto = null;
        try {
            System.out.println("Deserializing message from topic: " + topic);

            expenseDto = objectMapper.readValue(argument1 , ExpenseDto.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseDto;
    }



    @Override
    public void close() {
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
}

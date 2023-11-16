package com.example.bankmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankSystem {
    private String id;
    private String userName;
    private double balance;
}

package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.BankSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/bank")
public class BankController {
    public ArrayList<BankSystem> bankAccounts = new ArrayList<>();

@GetMapping("/get")
    public ArrayList<BankSystem> getCustomers(){
    return bankAccounts;
}

@PostMapping("/add")
    public ApiResponse createAccount(@RequestBody BankSystem account){
    bankAccounts.add(account);
    return new ApiResponse("Account crated",200);
}

@PutMapping("/update/{index}")
    public ApiResponse updateAccount(@PathVariable int index, @RequestBody BankSystem account){
    bankAccounts.set(index,account);
    return new ApiResponse("Account updated",200);}

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteAccount(@PathVariable int index){
        bankAccounts.remove(index);
        return new ApiResponse("Account Removed",200);
    }

    @PutMapping("/deposit")
    public ApiResponse deposit(@RequestBody BankSystem account){
        for (BankSystem bankAccount : bankAccounts) {
            if (account.getId().equals(bankAccount.getId())) {
                if (account.getUserName().equals(bankAccount.getUserName())) {
                    bankAccount.setBalance(bankAccount.getBalance() + account.getBalance());
                    return new ApiResponse("Amount: " + account.getBalance() + " deposited from account successfully", 200);
                }
            } else continue;
        }
        return new ApiResponse("Invalid id or username",400);
    }


    @PutMapping("/withdraw")
    public ApiResponse withdraw(@RequestBody BankSystem account){
        for (BankSystem bankAccount : bankAccounts) {
            if (account.getId().equals(bankAccount.getId())) {
                if (account.getUserName().equals(bankAccount.getUserName())) {
                    if (account.getBalance() <= bankAccount.getBalance() && account.getBalance() > 0) {
                        bankAccount.setBalance(bankAccount.getBalance() - account.getBalance());
                        return new ApiResponse("Amount: " + account.getBalance() + " withdrawn from account successfully", 200);
                    }
                }
            }
            else continue;
        }
        return new ApiResponse("Invalid id or username",400);
    }





}



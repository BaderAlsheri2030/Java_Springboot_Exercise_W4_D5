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
    public ApiResponse updateAccount(@PathVariable int index, BankSystem account){
    bankAccounts.set(index,account);
    return new ApiResponse("Account updated",200);}

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteAccount(@PathVariable int index){
        bankAccounts.remove(index);
        return new ApiResponse("Account Removed",200);
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index,@PathVariable double amount){
    bankAccounts.get(index).setBalance(amount+bankAccounts.get(index).getBalance());
        return new ApiResponse("Amount deposited successfully",200);
    }


    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index,@PathVariable double amount){
        if (amount <= bankAccounts.get(index).getBalance() && amount > 0) {
            bankAccounts.get(index).setBalance(bankAccounts.get(index).getBalance()-amount);
            return new ApiResponse("Amount withdrawn successfully",200);
        }
        return new ApiResponse("Faild to withdraw from account",400);
    }





}



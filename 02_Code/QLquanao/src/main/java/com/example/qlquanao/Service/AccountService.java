package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.AccountDAO;
import com.example.qlquanao.DAO.ProductDAO;
import com.example.qlquanao.Model.Account;
import com.example.qlquanao.Model.Product;
import com.example.qlquanao.Model.Supplier;

import java.util.ArrayList;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {

        this.accountDAO = new AccountDAO();
    }

    public ArrayList<Account> FINDALL() {

        return this.accountDAO.findAll();
    }
    public Account findByAccountName(String username) {

        return accountDAO.findByAccountName(username);
    }
    public Account login(String username, String password) {
        return accountDAO.login(username, password);
    }

    public void insert(Account account) {

        accountDAO.insert(account);
    }

}

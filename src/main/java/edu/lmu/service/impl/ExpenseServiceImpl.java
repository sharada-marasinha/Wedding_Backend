package edu.lmu.service.impl;

import edu.lmu.entity.Expense;
import edu.lmu.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Override
    public Expense createExpense(Expense expense) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return List.of();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return null;
    }

    @Override
    public Expense updateExpense(Long id, Expense updatedExpense) {
        return null;
    }

    @Override
    public void deleteExpense(Long id) {

    }
}

package edu.lmu.service;

import edu.lmu.entity.Budget;

import java.util.List;

public interface BudgetService {
    Budget createBudget(Budget budget);

    List<Budget> getAllBudgets();

    Budget getBudgetById(Long id);

    Budget updateBudget(Long id, Budget updatedBudget);

    void deleteBudget(Long id);
}

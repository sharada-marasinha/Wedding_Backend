package edu.lmu.service.impl;

import edu.lmu.entity.Budget;
import edu.lmu.service.BudgetService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BudgetServiceImpl implements BudgetService {
    @Override
    public Budget createBudget(Budget budget) {
        return null;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return List.of();
    }

    @Override
    public Budget getBudgetById(Long id) {
        return null;
    }

    @Override
    public Budget updateBudget(Long id, Budget updatedBudget) {
        return null;
    }

    @Override
    public void deleteBudget(Long id) {

    }
}

package edu.lmu.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "budgets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_budget")
    private Double totalBudget;

    @Column(name = "remaining_budget")
    private Double remainingBudget;

//    @OneToOne(mappedBy = "budgetDetails")
//    private Wedding wedding;
//
//    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Expense> expenses;

}
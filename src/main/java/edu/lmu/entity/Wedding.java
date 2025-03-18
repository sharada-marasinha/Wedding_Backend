package edu.lmu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weddings")
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wedding_id")
    private Integer weddingId;

    @Column(name = "wedding_name", nullable = false)
    private String weddingName;

    @Column(name = "couple_names", nullable = false)
    private String coupleNames;

    @Column(name = "wedding_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date weddingDate;
    @OneToOne
    private Budget budgetDetails;

    @Column(name = "guest_count")
    private Integer guestCount;

    @Column(name = "theme")
    private String theme;

     @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Task> tasks;

     @ManyToMany
     @JoinTable(
         name = "wedding_vendors",
         joinColumns = @JoinColumn(name = "wedding_id"),
         inverseJoinColumns = @JoinColumn(name = "vendor_id")
     )
     private List<Vendor> vendors;

     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;


    // Getters and setters for potential relationships (uncommented if used)
    // public List<Task> getTasks() {
    //     return tasks;
    // }
    //
    // public void setTasks(List<Task> tasks) {
    //     this.tasks = tasks;
    // }
    //
    // public List<Vendor> getVendors() {
    //     return vendors;
    // }
    //
    // public void setVendors(List<Vendor> vendors) {
    //     this.vendors = vendors;
    // }
    //
    // public User getUser() {
    //     return user;
    // }
    //
    // public void setUser(User user) {
    //     this.user = user;
    // }
}

package edu.lmu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "rating")
    private Double rating;

//    @ManyToMany(mappedBy = "vendors")
//    private List<Wedding> weddings;

}

package com.mora.backendangkorliving.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable=false)
    private Room room;

    private double electricityFee;
    private double trashFee;
    private double vehicleFee;

    private int month;
    private int year;
}


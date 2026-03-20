package com.mora.backendangkorliving.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;      // ថ្ងៃត្រូវបង់
    private LocalDate paymentDate;  // ថ្ងៃបានបង់
    private Double amount;

    @Column(nullable=true)
    private String method; // CASH, QR

    @Column(nullable=false)
    private String status; // PAID, PENDING, LATE

    @Column(columnDefinition = "TEXT")
    private String qrCode; // KHQR string

    private String qrMd5;  // NBC md5 hash

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable=false)
    private Rental rental;
}

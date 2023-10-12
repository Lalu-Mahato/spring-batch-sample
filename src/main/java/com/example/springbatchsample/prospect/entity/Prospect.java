package com.example.springbatchsample.prospect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prospects")
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String loanAccountNumber;
    private String customerName;
    private String loanAmountDisbursed;
    private String loanDisbursementDate;
    private String emiDueDate;
    private String loanOutstanding;
    private String principalOutstanding;
    private String interestOutstanding;
    private String emiAmount;
    private String principalAmount;
    private String interestAmount;
    private String arrearAmount;
    private String principalArrear;
    private String interestArrear;
    private String otherCharges;
    private String totalAmountCollection;
    private String dpd;
    private String lastPaymentDate;
    private String lastPaidAmount;
    private String lastEmiDate;
    private String currentTenure;
    private String totalTenure;
    private String residualTenure;
    private String roi;
    private String unpaidInstallments;
    private String totalInstallments;
    private String productName;
    private String productId;
    private String mobileNumber;
    private String customerAddress;
    private String cifId;
}

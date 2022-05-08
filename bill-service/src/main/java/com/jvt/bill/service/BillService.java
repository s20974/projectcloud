package com.jvt.bill.service;

import com.jvt.bill.entity.Bill;
import com.jvt.bill.exception.BillNotFoundException;
import com.jvt.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long billId){
        return billRepository.findById(billId).orElseThrow(
                () -> new BillNotFoundException("bill not found")
        );
    }

    public Long createBill(Long accountId, BigDecimal amount,
                           Boolean isDefault, Boolean overdraftEnabled){
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), overdraftEnabled);
        return billRepository.save(bill).getBillId();
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount,
                           Boolean isDefault, Boolean overdraftEnabled){
        Bill bill = new Bill(accountId, amount, isDefault, overdraftEnabled);
        bill.setAccountId(accountId);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long billId){
        Bill deletedBill = getBillById(billId);
        billRepository.deleteById(billId);
        return deletedBill;
    }

    public List<Bill> getBillsByAccountId(Long accountId){
        return billRepository.getBillsByAccountId(accountId);
    }
}

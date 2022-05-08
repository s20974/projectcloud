package com.jvt.bill.repository;

import com.jvt.bill.entity.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {
    List<Bill> getBillsByAccountId(Long accountId);
}

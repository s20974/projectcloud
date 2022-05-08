package com.jvt.bill.repository;

import com.jvt.bill.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}

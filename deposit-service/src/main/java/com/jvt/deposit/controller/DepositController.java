package com.jvt.deposit.controller;

import com.jvt.deposit.controller.dto.DepositRequestDTO;
import com.jvt.deposit.controller.dto.DepositResponseDTO;
import com.jvt.deposit.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposits")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO){
        return depositService.deposit(depositRequestDTO.getAccountId(), depositRequestDTO.getBillId(), depositRequestDTO.getAmount());
    }
}

package com.jvt.sepositservice.service;

import com.jvt.deposit.controller.dto.DepositResponseDTO;
import com.jvt.deposit.exception.DepositServiceException;
import com.jvt.deposit.repository.DepositRepository;
import com.jvt.deposit.rest.AccountResponseDTO;
import com.jvt.deposit.rest.AccountServiceClient;
import com.jvt.deposit.rest.BillResponseDTO;
import com.jvt.deposit.rest.BillServiceClient;
import com.jvt.deposit.service.DepositService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @Mock
    private AccountServiceClient accountServiceClient;

    @Mock
    private BillServiceClient billServiceClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private DepositService depositService;

    @Test
    public void depositServiceTest_withBillId(){
        BillResponseDTO billResponseDTO = createBillResponseDTO();
        Mockito.when(billServiceClient.getBillById(ArgumentMatchers.anyLong())).thenReturn(billResponseDTO);
        Mockito.when(accountServiceClient.getAccountById(ArgumentMatchers.anyLong())).thenReturn(createAccountResponseDTO());
        DepositResponseDTO deposit = depositService.deposit(null, 1L, BigDecimal.valueOf(1000));
        Assertions.assertThat(deposit.getMail()).isEqualTo("lory@gmail.com");
    }

    @Test(expected = DepositServiceException.class)
    public void depositServiceTest_exception(){
        depositService.deposit(null, null, BigDecimal.valueOf(1000));
    }

    private AccountResponseDTO createAccountResponseDTO(){
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setAccountId(1L);
        accountResponseDTO.setBills(Arrays.asList(1L, 2L, 3L));
        accountResponseDTO.setCreationDate(OffsetDateTime.now());
        accountResponseDTO.setEmail("lory@gmail.com");
        accountResponseDTO.setName("Lory");
        accountResponseDTO.setPhone("+1234556");
        return accountResponseDTO;
    }

    private BillResponseDTO createBillResponseDTO(){
        BillResponseDTO billResponseDTO = new BillResponseDTO();
        billResponseDTO.setAccountId(1L);
        billResponseDTO.setAmount(BigDecimal.valueOf(1000));
        billResponseDTO.setBillId(1L);
        billResponseDTO.setCreationDate(OffsetDateTime.now());
        billResponseDTO.setIsDefault(true);
        billResponseDTO.setOverdraftEnabled(true);
        return billResponseDTO;
    }
}

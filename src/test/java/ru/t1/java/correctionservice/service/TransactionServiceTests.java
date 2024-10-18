package ru.t1.java.correctionservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import ru.t1.java.correctionservice.adapter.feign.FeignClientService;
import ru.t1.java.correctionservice.adapter.repository.TransactionErrorRepository;
import ru.t1.java.correctionservice.app.domain.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.app.domain.entity.TransactionError;
import ru.t1.java.correctionservice.app.mapper.TransactionErrorMapper;
import ru.t1.java.correctionservice.app.service.impl.TransactionServiceImpl;
import ru.t1.java.correctionservice.utils.exceptions.FeignBadRequestException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    @Mock
    private FeignClientService feignClientService;

    @Mock
    private TransactionErrorRepository transactionErrorRepository;

    @Mock
    private TransactionErrorMapper transactionErrorMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void unblockAccountShouldSaveTransactionError() {
        Long transactionId = 1L;
        TransactionErrorDto errorDto = new TransactionErrorDto();
        TransactionError transactionError = new TransactionError();

        FeignBadRequestException ex = new FeignBadRequestException(400, "Bad Request", errorDto);
        when(feignClientService.unblockAccount(transactionId)).thenThrow(ex);
        when(transactionErrorMapper.map(errorDto)).thenReturn(transactionError);

        transactionService.unblockAccount(transactionId);

        verify(transactionErrorRepository, times(1)).saveAndFlush(transactionError);
        verify(transactionErrorRepository, never()).deleteByTransactionId(transactionId);
    }

    @Test
    void unblockAccountShouldHandleDataIntegrityViolationException() {
        Long transactionId = 1L;
        TransactionErrorDto errorDto = new TransactionErrorDto();
        TransactionError transactionError = new TransactionError();

        FeignBadRequestException ex = new FeignBadRequestException(400, "Bad Request", errorDto);
        when(feignClientService.unblockAccount(transactionId)).thenThrow(ex);
        when(transactionErrorMapper.map(errorDto)).thenReturn(transactionError);
        doThrow(new DataIntegrityViolationException("Duplicate entry"))
                .when(transactionErrorRepository).saveAndFlush(transactionError);

        assertDoesNotThrow(() -> transactionService.unblockAccount(transactionId));
        verify(transactionErrorRepository, times(1)).saveAndFlush(transactionError);
    }
}

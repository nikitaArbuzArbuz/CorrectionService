package ru.t1.java.correctionservice.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.t1.java.correctionservice.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.entity.TransactionError;

@Mapper(componentModel = "spring")
@Slf4j
@RequiredArgsConstructor
public abstract class TransactionErrorMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "type", source = "type")
    public abstract TransactionError map(TransactionErrorDto transactionDto);
}

package com.credibanco.bankincback.persistence.mapper;

import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.persistence.entity.TransactionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CardMapper.class})
public interface TransactionMapper {
    @Mappings({
            @Mapping(source = "idTransaction",target = "transactionId"),
            @Mapping(source = "totalPrice",target = "price"),
    })
    Transaction toTransaction(TransactionEntity transactionEntity);
    List<Transaction> toTransactionsList(List<TransactionEntity> transactions);
    @InheritInverseConfiguration
    TransactionEntity toTransactionEntity(Transaction transaction);
}

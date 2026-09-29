package amigo.programador.hexagonal.infrastructure.adapter.out.mapper;

import amigo.programador.hexagonal.domain.model.Client;
import amigo.programador.hexagonal.domain.model.DebitCard;
import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.domain.model.TransactionType;
import amigo.programador.hexagonal.infrastructure.adapter.common.TransactionTypeInfrastructure;
import amigo.programador.hexagonal.infrastructure.adapter.out.dto.ClientDocument;
import amigo.programador.hexagonal.infrastructure.adapter.out.dto.DebitCardDocument;
import amigo.programador.hexagonal.infrastructure.adapter.out.dto.TransactionDocument;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TransactionDocumentMapper {

  public Transaction toDomain(TransactionDocument transactionDocument) {
    if(Objects.isNull(transactionDocument)) {
      return null;
    }

    return Transaction.builder()
      .id(transactionDocument.getId())
      .transactionAmount(transactionDocument.getTransactionAmount())
      .transactionCode(transactionDocument.getTransactionCode())
      .destination(this.toDomain(transactionDocument.getDestination()))
      .origin(this.toDomain(transactionDocument.getOrigin()))
      .transactionType(
        TransactionType.valueOf(transactionDocument.getTransactionTypeInfrastructure().name()))
      .transactionDate(transactionDocument.getTransactionDate())
      .build();
  }

  public TransactionDocument toDocument(Transaction transaction) {
    if(Objects.isNull(transaction)) {
      return null;
    }

    return TransactionDocument.builder()
      .id(transaction.getId())
      .transactionAmount(transaction.getTransactionAmount())
      .transactionCode(transaction.getTransactionCode())
      .destination(this.toDocument(transaction.getDestination()))
      .origin(this.toDocument(transaction.getOrigin()))
      .transactionTypeInfrastructure(
        TransactionTypeInfrastructure.valueOf(transaction.getTransactionType().name()))
      .transactionDate(transaction.getTransactionDate())
      .build();
  }

  public DebitCard toDomain(DebitCardDocument debitCardDocument) {
    if(Objects.isNull(debitCardDocument)) {
      return null;
    }

    return DebitCard.builder()
      .id(debitCardDocument.getId())
      .cardNumber(debitCardDocument.getCardNumber())
      .balance(debitCardDocument.getBalance())
      .expirationDate(debitCardDocument.getExpirationDate())
      .client(this.toDomain(debitCardDocument.getClient()))
      .build();
  }

  public DebitCardDocument toDocument(DebitCard debitCard) {
    if(Objects.isNull(debitCard)) {
      return null;
    }

    return DebitCardDocument.builder()
      .id(debitCard.getId())
      .cardNumber(debitCard.getCardNumber())
      .balance(debitCard.getBalance())
      .expirationDate(debitCard.getExpirationDate())
      .client(this.toDocument(debitCard.getClient()))
      .build();
  }

  public Client toDomain(ClientDocument clientDocument) {
    if(Objects.isNull(clientDocument)) {
      return null;
    }

    return Client.builder()
      .id(clientDocument.getId())
      .name(clientDocument.getName())
      .age(clientDocument.getAge())
      .dni(clientDocument.getDni())
      .build();
  }

  public ClientDocument toDocument(Client client) {
    if(Objects.isNull(client)) {
      return null;
    }

    return ClientDocument.builder()
      .id(client.getId())
      .name(client.getName())
      .age(client.getAge())
      .dni(client.getDni())
      .build();
  }

}

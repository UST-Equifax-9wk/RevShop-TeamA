package com.revature.RevShopServer.entities;

import com.revature.RevShopServer.enums.CardType;
import jakarta.persistence.Entity;

@Entity
public class Card {
    private Long cardId;

    private String cardNumber;

    private String cardHolderName;

    private String expirationDate;

    private CardType cardType;

    public Card() {
    }

    public Card(Long cardId, String cardNumber, String cardHolderName, String expirationDate, CardType cardType) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardType=" + cardType +
                '}';
    }
}

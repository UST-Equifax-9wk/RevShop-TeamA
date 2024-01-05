package com.revature.RevShopServer.entities;

import com.revature.RevShopServer.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String cardNumber;

    private String cardHolderName;

    @Size(min = 5, max = 5, message = "Expiration Date must be 5 characters")
    private String expirationDate;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToMany
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

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

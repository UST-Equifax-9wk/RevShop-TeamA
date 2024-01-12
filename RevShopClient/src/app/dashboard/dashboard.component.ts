import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { CardDto ,RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  cardNumber = '';
  expirationDate = '';
  cardHolderName = '';
  cardType = '';
  addCardFlag: boolean = false;
  cards: any[] = [];

  toggleAddCard() { this.addCardFlag = !this.addCardFlag;}

  constructor(private remoteService: RemoteService) {
    this.remoteService = remoteService;
  }

  addCardDetails() {
    const senderInfo = JSON.parse(localStorage.getItem('currentUser') || '{}');
    const accountId = senderInfo.account.id;
    const cardDto : CardDto = {
      cardNumber: this.cardNumber,
      expirationDate: this.expirationDate,
      cardHolderName: this.cardHolderName,
      cardType: this.cardType
    }
    console.log('cardDto: ', JSON.stringify(cardDto));
    this.remoteService.addCard(cardDto, accountId)
    .subscribe({
      next: (data) => {
        const body = JSON.stringify(data.body);
        alert("Successfully Added Card!")
        console.log(body);
      },
      error: (error: HttpErrorResponse) => {
        alert("Couldn't Add Card!")
        console.log(error)
      }
    })
    this.addCardFlag = false
  }

  fetchCardDetails() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    this.remoteService.getCardDetails(currentUser.account.id)
    .subscribe({
      next: (data) => {
        const cards = JSON.parse(JSON.stringify(data.body || {}));
        this.cards = cards.map((card: any) => ({
          ...card,
          cardNumber: this.maskCardNumber(card.cardNumber)
        }));
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching cards details:', error);
      }
    });
  }

  private maskCardNumber(cardNumber: string): string {
    return cardNumber.replace(/.(?=.{4})/g, '*');
  }

}

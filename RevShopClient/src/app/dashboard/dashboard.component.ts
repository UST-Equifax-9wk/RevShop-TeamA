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
  options: string[] = ['CREDIT', 'DEBIT'];

  firstName: string;
  lastName: string;
  username: string;
  email: string;
  phoneNumber: string;
  password: string;
  accountType: string;

  toggleAddCard() { this.addCardFlag = !this.addCardFlag;}

  constructor(private remoteService: RemoteService, private cookieService: CookieService) {
    this.firstName = "";
    this.lastName = "";
    this.username = "";
    this.email = "",
    this.password = "";
    this.phoneNumber = "";
    this.accountType = "";
    this.remoteService = remoteService;
    this.fetchUserInfo();
  }

  fetchUserInfo() {
    const currentUserToken = this.cookieService.get('token');
    let currentUser = this.remoteService.decodeToken(currentUserToken);
    if (typeof currentUser === 'string') {
      this.remoteService.getUserInfo(currentUser)
      .subscribe({
        next: (data) => {
          let userInfo = JSON.parse(JSON.stringify(data.body || {}));
          this.updateUserInfo(userInfo);
        },
        error: (error) => {
          alert("Couldn't Register")
          console.log(error.error)
        }
      });
    } else {
      console.error('Invalid username');
    }
    
  }

  updateUserInfo(userInfo: any) {
    this.username = userInfo.username;
    this.firstName = userInfo.firstName;
    this.lastName = userInfo.lastName;
    this.accountType = userInfo.accountType;
    this.phoneNumber = userInfo.phoneNumber;
    this.email = userInfo.email;
    localStorage.setItem('currentUser', JSON.stringify(userInfo));
  }

  addCardDetails() {
    const senderInfo = JSON.parse(localStorage.getItem('currentUser') || '{}');
    console.log("senderInfo", senderInfo);
    const accountId = senderInfo.accountId;
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
    this.remoteService.getCardDetails(currentUser.accountId)
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

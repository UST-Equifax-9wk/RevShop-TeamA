import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  username: string;
  accountType: string;
  specificId: number;

  constructor() { 
    this.username = "";
    this.accountType = "";
    this.specificId = NaN;
  }

  resetSession() {
    this.username = "";
    this.accountType = "";
    this.specificId = NaN;
  }

  // use the decoded token stored to store values
  updateSession(tokenData: Session) {
    this.username = tokenData.username;
    this.accountType = tokenData.accountType;
    this.specificId = tokenData.specificId;
  }

}

export interface Session {
  username: string;
  accountType: string;
  specificId: number;
}

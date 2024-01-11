import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth.service';
import { CookieService } from 'ngx-cookie-service';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterOutlet,RouterModule,CommonModule, RouterLink],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  constructor(public authService: AuthService, 
    public sessionService: SessionService,
    private cookieService: CookieService) {}

  logout(): void {
    alert("logged out!");
    localStorage.clear();
    this.authService.logout();
  }
}

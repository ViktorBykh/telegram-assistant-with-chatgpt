import { Component, EventEmitter, Output } from '@angular/core';
import { AxiosService } from 'src/app/services/axios.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Output() logoutEvent = new EventEmitter();

  constructor(private axiosService: AxiosService) { }

  onLogout(input: any): void {
    this.axiosService.setAuthToken(null);
    alert("You are outside the system");
  }
}

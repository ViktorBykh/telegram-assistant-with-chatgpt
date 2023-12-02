import { EventEmitter, Component, Output } from '@angular/core';
import { AxiosService } from 'src/app/services/axios.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

  constructor(
    private router: Router,
    private axiosService: AxiosService) { }

  active: string = "login";
  firstName: string = "";
  lastName: string = "";
  login: string = "";
  password: string = "";

  onLoginTab(): void {
    this.active = "login";
  }

  onRegisterTab(): void {
    this.active = "register";
  }

  onSubmitLogin(): void {
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: this.login,
        password: this.password
      }).then(
        response => {
          this.axiosService.setAuthToken(response.data.token);
          alert("Successful login");
          this.router.navigate(['admin/content']);
        }).catch(
          error => {
            alert("Wrong username or password!");
            this.axiosService.setAuthToken(null);
          }
        );
  }

  onSubmitRegister(): void {
    this.axiosService.request(
      "POST",
      "/register",
      {
        firstName: this.firstName,
        lastName: this.lastName,
        login: this.login,
        password: this.password
      }).then(
        response => {
          this.axiosService.setAuthToken(response.data.token);
          alert("Successful registration");
          this.router.navigate(['admin/content']);
        }).catch(
          error => {
            alert("Wrong registration data!");
            this.axiosService.setAuthToken(null);
          }
        );
  }

}

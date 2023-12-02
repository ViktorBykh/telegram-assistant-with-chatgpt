import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AxiosService } from 'src/app/services/axios.service';

export interface ChatHistory {
  externalId: number;
  firstName: string;
  lastName: string;
  userMessage: string;
  botResponse: string;
  dateTime: Date;
}

export interface User {
  externalId: number;
  firstName: string;
  lastName: string;
}

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent {
  users: User[] = [];

  count: number = 5;
  page: number = 0;
  sortBy: string = 'externalId';
  sortOrder: string = ':ASC';

  setCount(value: number) {
    this.count = value;
  }

  setPage(value: number) {
    this.page = value;
  }

  setSortBy(value: string) {
    this.sortBy = value;
  }

  getSortByText(sortBy: string): string {
    switch(sortBy) {
      case('firstName'):
      return 'First name';
      case('lastName'):
      return 'Last name';
      default:
        return 'External id';
    }
  }

  setSortOrder(value: string) {
    this.sortOrder = value;
  }

  getSortOrderText(sortOrder: string): string {
    return sortOrder === ':ASC' ? 'ASC' : 'DESC';
  }

  constructor(
    private router: Router,
    private axiosService: AxiosService) { }

  ngOnInit(): void {
    this.axiosService.request(
      "GET",
      `/user?count=${this.count}&page=${this.page}&sortBy=${this.sortBy}&sortOrder=${this.sortOrder}`,
      {}).then(
        (response: { data: any; }) => {
          this.users = response.data;
        }).catch(
          (error: { response: { status: number; code: any; }; }) => {
            if (error.response.status === 401) {
              this.axiosService.setAuthToken(null);
            } else {
              this.users = error.response.code;
            }
          }
        );
  }

  navigateToChatHistory(externalId: number) {
    this.router.navigate([`admin/user/chat-history/${externalId}`]);
  }


  onSubmit() {
    this.axiosService.request(
      "GET",
      `/user?count=${this.count}&page=${this.page}&sortBy=${this.sortBy}&sortOrder=${this.sortOrder}`,
      {})
      .then(
        (response: { data: any; }) => {
          this.users = response.data;
        }).catch(
          (error: { response: { status: number; code: any; }; }) => {
            if (error.response.status === 401) {
              this.axiosService.setAuthToken(null);
            } else {
              this.users = error.response.code;
            }
          }
        );
  }
}
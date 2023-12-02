import { Component } from '@angular/core';
import { AxiosService } from 'src/app/services/axios.service';
import { ActivatedRoute } from '@angular/router';

export interface ChatHistory {
  externalId: number;
  firstName: string;
  lastName: string;
  userMessage: string;
  botResponse: string;
  dateTime: Date;
}

@Component({
  selector: 'app-chat-history',
  templateUrl: './chat-history.component.html',
  styleUrls: ['./chat-history.component.scss']
})
export class ChatHistoryComponent {
  chatHistories: ChatHistory[] = [];

  count: number = 5;
  page: number = 0;
  sortBy: string = 'dateTime';
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
      case('userMessage'):
      return 'Message';
      case('botResponse'):
      return 'Response';
      default:
        return 'Date';
    }
  }

  setSortOrder(value: string) {
    this.sortOrder = value;
  }

  getSortOrderText(sortOrder: string): string {
    return sortOrder === ':ASC' ? 'ASC' : 'DESC';
  }

  externalId: number = 0;

  constructor(
    private route: ActivatedRoute,
    private axiosService: AxiosService) { }


  ngOnInit(): void {
    this.externalId = +this.route.snapshot.paramMap.get('externalId')!;
    this.axiosService.request(
      "GET",
      `/user/chat-history?externalId=${this.externalId}&count=${this.count}&page=${this.page}&sortBy=${this.sortBy}&sortOrder=${this.sortOrder}`,
      {})
      .then(
        (response: { data: any; }) => {
          this.chatHistories = response.data;
        }).catch(
          (error: { response: { status: number; code: any; }; }) => {
            if (error.response.status === 401) {
              this.axiosService.setAuthToken(null);
            } else {
              this.chatHistories = error.response.code;
            }
          }
        );
  }

  onSubmit() {
    this.axiosService.request(
      "GET",
      `/user/chat-history?externalId=${this.externalId}&count=${this.count}&page=${this.page}&sortBy=${this.sortBy}&sortOrder=${this.sortOrder}`,
      {})
      .then(
        (response: { data: any; }) => {
          this.chatHistories = response.data;
        }).catch(
          (error: { response: { status: number; code: any; }; }) => {
            if (error.response.status === 401) {
              this.axiosService.setAuthToken(null);
            } else {
              this.chatHistories = error.response.code;
            }
          }
        );
  }
}

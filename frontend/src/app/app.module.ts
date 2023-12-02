import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { DragDropModule } from '@angular/cdk/drag-drop';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ContentComponent } from './components/content/content.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AxiosService } from './services/axios.service';
import { MainComponent } from './components/main/main.component';
import { RouterModule, Routes } from '@angular/router';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChatHistoryComponent } from './components/chat-history/chat-history.component';

const routes: Routes = [
  { path: 'admin/login', component: LoginFormComponent },
  { path: 'admin/main', component: MainComponent },
  { path: 'admin/content', component: ContentComponent },
  { path: 'admin/user/chat-history/:externalId', component: ChatHistoryComponent },
  { path: '**', redirectTo: '/admin/main', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    LoginFormComponent,
    MainComponent,
    FooterComponent,
    ChatHistoryComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    DragDropModule,
    BrowserAnimationsModule
  ],
  providers: [AxiosService],
  bootstrap: [AppComponent]
})
export class AppModule { }

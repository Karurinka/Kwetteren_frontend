import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { KweetCreateComponent } from './components/kweet-create/kweet-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { KweetHomepageComponent } from './components/kweet-homepage/kweet-homepage.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    KweetCreateComponent,
    UserProfileComponent,
    UserLoginComponent,
    KweetHomepageComponent,
    PageNotFoundComponent,
    UserRegisterComponent,
    UserEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

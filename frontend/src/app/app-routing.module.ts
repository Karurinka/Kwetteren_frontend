import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserLoginComponent} from './components/user-login/user-login.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {KweetHomepageComponent} from './components/kweet-homepage/kweet-homepage.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: UserLoginComponent
  },
  {
    path: 'profile',
    component: UserProfileComponent
  },
  {
    path: 'home',
    component: KweetHomepageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

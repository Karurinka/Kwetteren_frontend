import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { KweetHomepageComponent } from './components/kweet-homepage/kweet-homepage.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { KweetCreateComponent } from './components/kweet-create/kweet-create.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';

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
    path: 'register',
    component: UserRegisterComponent
  },
  {
    path: 'profile',
    component: UserProfileComponent,
    children: [{
      path: 'edit',
      component: UserEditComponent
    }]
  },
  {
    path: 'home',
    component: KweetHomepageComponent
  },
  {
    path: 'kweet',
    component: KweetCreateComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

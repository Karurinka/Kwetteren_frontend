import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserProfileeComponent } from './user-profilee/user-profilee.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { KweetHomepageComponent } from './kweet-homepage/kweet-homepage.component';
import { UserFollowingComponent } from './user-following/user-following.component';

@NgModule({
  declarations: [UserLoginComponent, UserProfileeComponent, UserProfileComponent, KweetHomepageComponent, UserFollowingComponent],
  imports: [
    CommonModule
  ]
})
export class UserModule { }

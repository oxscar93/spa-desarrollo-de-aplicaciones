import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { UsersComponent } from './users/users.component';
import { CriptosComponent } from './criptos/criptos.component';
import { UsersRegistrationComponent } from './users/registration/users-registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'users', component: UsersComponent },
    { path: 'register', component: UsersRegistrationComponent },
    { path: 'criptos', component: CriptosComponent },
  ];

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
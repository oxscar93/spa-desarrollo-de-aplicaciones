import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users-registration',
  templateUrl: './users-registration.component.html',
  styleUrls: ['./users-registration.component.css']
})
export class UsersRegistrationComponent implements OnInit {

  user:any;
  ngForm:any;

  constructor(private http: HttpClient, private router:Router) { }

  ngOnInit(): void {
    this.user = {};
  }

  registerUser(){    
    this.http.post(environment.api + "api/users", this.user)
    .subscribe((data) =>
    {
      this.user = {};
      alert("User registered successfully");
      this.router.navigate([""])
    },
    (error:any) => {
      alert("Error ocurred while registering user. Try Again. " + error.error)
    }
    );
  }
}

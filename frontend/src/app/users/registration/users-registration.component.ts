import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-users-registration',
  templateUrl: './users-registration.component.html',
  styleUrls: ['./users-registration.component.css']
})
export class UsersRegistrationComponent implements OnInit {

  user:any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.user = {};
  }

  registerUser(){    
    this.http.post(environment.api + "api/users", this.user)
    .subscribe((data) =>
    {
      this.user = {};
      alert("User registered successfully");
    }
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
    this.http.post("http://localhost:8080/api/users", this.user).subscribe((data) =>{alert("User registered successfully")});
  }
}

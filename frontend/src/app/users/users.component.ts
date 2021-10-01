import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users:any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    //refactor url a una configuracion comun cuando se siga desarrollando la app
    
    this.http.get("http://localhost:8080/api/users").subscribe((data) => this.users = data);
  }
}

import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-my-transactions',
  templateUrl: './my-transactions.component.html',
  styleUrls: ['./my-transactions.component.css']
})
export class MyTransactionsComponent implements OnInit {

  transactions:any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(){   
    var token = localStorage.getItem("token");

    if (token != null){
        this.http.get(environment.api + "api/transactions/" + JSON.parse(token).username ).subscribe((data) => 
        {
              this.transactions = data;  
        }
    );
    }   
  }
}

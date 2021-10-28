import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  activity:any;
  transaction:any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {  
    
    this.activity =  {}
    this.transaction =  {}
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.http.get(environment.api + "api/activity/" + params.get("id")).subscribe((data) => this.activity = data);
    });
  }

  calculateAmount(){
    this.transaction.operationAmount = this.transaction.criptoCount * this.activity.operationAmount
  }

  create(){
    this.transaction.cripto = this.activity.cripto;
    this.transaction.user = this.activity.user;
    this.transaction.type = this.activity.type;

    this.http.post(environment.api + "api/transactions/create", this.transaction)
    .subscribe((data) =>
    {
      alert("Operation OK");
    }
    );
  }

  cancel(){
    this.router.navigate(["/activities"])
  }
}

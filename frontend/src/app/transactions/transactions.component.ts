import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { interval } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  activity:any;
  transaction:any;
  timerOn:boolean;
  transactionSent:boolean;
  confirmed:boolean;
  cancelled:boolean;
  totalSeconds:number;
  seconds:string;
  minutes:string;
  transactionId: any;
  intervalId: any;
  ngForm:any;
  
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {  
    
    this.activity =  {}
    this.transaction =  {}
    this.timerOn = false;
    this.confirmed = false;
    this.cancelled = false;
    this.transactionSent = false;
    this.seconds = "00";
    this.minutes = "00";
    this.totalSeconds = 0;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.http.get(environment.api + "api/activity/" + params.get("id")).subscribe((data) => this.activity = data);
    });
  }

  calculateAmount(){
    if (this.transaction.criptoCount > this.activity.criptoCount || this.transaction.criptoCount == 0 ){
      this.transaction.criptoCount = "";
      this.transaction.operationAmount = "";
    }
    else{
      this.transaction.operationAmount = this.transaction.criptoCount * this.activity.operationAmount
    } 
  }

  create(){
    this.transaction.activityId = this.activity.id;
    this.transaction.cripto = this.activity.cripto;
    this.transaction.user = this.activity.user;
    this.transaction.type = this.activity.type;
    this.transaction.status = 1; //pending

    this.http.post(environment.api + "api/transactions/create", this.transaction)
    .subscribe((data:any) =>
    { 
      this.transactionSent = true;
      this.transactionId = data.id;

      this.startTimer();
      alert("Operation OK");
    }
    );
  }

  cancel(){
    this.transaction.activityId = this.activity.id;
    this.transaction.id = this.transactionId;

    if (this.intervalId != null){
      this.intervalId.unsubscribe();
      this.timerOn = false; 
    }

      

    this.http.post(environment.api + "api/transactions/cancel", this.transaction)
    .subscribe((data:any) =>
    { 
      this.cancelled = true; 
      this.transactionSent = true;

      alert("Operation OK");
      this.close()
    }
    );
  }
  close(){
    this.router.navigate(["/activities"])
  }

 setTime()
  {
      ++this.totalSeconds;
      this.seconds = this.pad(this.totalSeconds%60);
      this.minutes = this.pad(parseInt((this.totalSeconds/60).toString()));
  }

  pad(val:any)
  {
      var valString = val + "";
      if(valString.length < 2)
      {
          return "0" + valString;
      }
      else
      {
          return valString;
      }
  }

  startTimer(){
    this.intervalId = interval(1000).subscribe(i => {
     this.timerOn = true;
     this.setTime();
     this.http.get(environment.api + "api/transactions/status/" + this.transactionId)
          .subscribe((data:any) => {
            if (data.status == 2){    
              this.confirmed = true;  
              this.intervalId.unsubscribe();
              this.timerOn = false;           
            }   
            if (data.status == 3){    
              this.cancelled = true; 
              this.intervalId.unsubscribe();
              this.timerOn = false;           
            }        
          });
    });
  }
}

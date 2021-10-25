import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-price',
  templateUrl: './price.component.html',
  styleUrls: ['./price.component.css']
})
export class PriceComponent implements OnInit {

  prices:any;
  timeUpdated:Date;
  updated:boolean;

  constructor(private http: HttpClient) { 
    this.timeUpdated = new Date();
    this.updated = false;
  }

  ngOnInit(): void {
    this.getPrices();
    this.startPolling();
  }

  getPrices(){   
    this.http.get(environment.api + "api/criptos/prices").subscribe((data) => 
      {
            this.prices = data;  
            this.timeUpdated = new Date();
            this.updated = true;
      }
    );
  }

  startPolling(){
    interval(600000).subscribe((i) => this.getPrices() )
  }
}

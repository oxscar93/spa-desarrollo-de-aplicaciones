import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sell-buy',
  templateUrl: './sell-buy.component.html',
  styleUrls: ['./sell-buy.component.css']
})
export class SellBuyComponent implements OnInit {

  activities:any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getActivities()
  }

  getActivities(){   
    this.http.get(environment.api + "api/activities").subscribe((data) => this.activities = data);
  }
}

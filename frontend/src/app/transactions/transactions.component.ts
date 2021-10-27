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

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {  
    this.activity =  {}
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.http.get(environment.api + "api/activity/" + params.get("id")).subscribe((data) => this.activity = data);
    });
  }

  cancel(){
    this.router.navigate(["/activities"])
  }
}

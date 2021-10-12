import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-criptos',
  templateUrl: './criptos.component.html',
  styleUrls: ['./criptos.component.css']
})
export class CriptosComponent implements OnInit {

  criptos:any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getCriptos();
  }

  getCriptos() {
    this.http.get(environment.api + "api/criptos/actives").subscribe((data) => this.criptos = data);
  }

}

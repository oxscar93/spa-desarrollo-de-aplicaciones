import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-criptos',
  templateUrl: './criptos.component.html',
  styleUrls: ['./criptos.component.css']
})
export class CriptosComponent implements OnInit {

  closeModal: string;
  criptos:any;
  sellBuy:any;

  constructor(private http: HttpClient, private modalService: NgbModal) {  this.closeModal = ""; }

  ngOnInit(): void {
    this.getCriptos();
    this.sellBuy = {};
  }

  getCriptos() {
    this.http.get(environment.api + "api/criptos/actives").subscribe((data) => this.criptos = data);
  }

  showModal(content:any, cripto:any, type:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;

      let token = localStorage.getItem('token')?.toString();

      if (token != null)
      {
        this.sellBuy.user = JSON.parse(token).username;
        this.sellBuy.criptoPrice = cripto.price;
        this.sellBuy.cripto = cripto.name;
        this.sellBuy.date = cripto.date; 
      }

      var url = type == 1 ? environment.api + "api/activities/buy" : environment.api + "api/activities/sell";

      this.http.post(url, this.sellBuy)
      .subscribe((data) =>
      {
        this.sellBuy = {};
        alert("Operation OK");
      }
      );

    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

}

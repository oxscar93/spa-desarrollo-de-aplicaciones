import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpResponse, HttpRequest, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class HttpAuthInterceptor implements HttpInterceptor {

  constructor(private router: Router){}

  intercept(httpRequest: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('token')?.toString();
    let  Authorization = "";

    if (token != undefined){
        Authorization = 'Bearer ' + JSON.parse(token).access_token;
    }
    else{
        this.router.navigate([""])
    }
    
    return next.handle(httpRequest.clone({ setHeaders: { Authorization } }));
  }
}
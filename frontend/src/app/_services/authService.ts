import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { tokenize } from '@angular/compiler/src/ml_parser/lexer';

@Injectable() 
export class AuthService {

    constructor(private http: HttpClient) {

    }

    login(username:string, password:string) {
        let body = new URLSearchParams();
        body.set('user', username);
        body.set('password', password);

        let options = {
            headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
        };

        return this.http.post<any>(`${environment.api}api/auth`, body.toString(), options)
            .pipe(map(token => {
                localStorage.setItem('token', JSON.stringify(token));
                return token;
            }));
    }

    logout() {
        localStorage.removeItem('currentUser');
    }
}
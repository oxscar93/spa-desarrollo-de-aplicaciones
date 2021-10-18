import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../_services/AuthService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private authenticationService: AuthService, private router: Router, private formBuilder: FormBuilder) {
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      });
   }

  ngOnInit(): void {
    var token = localStorage.getItem("token");

    if (token != null){
      this.router.navigate(["home"])
    }
  }

  onSubmit() {
        this.submitted = true;
        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.login(this.loginForm.controls.username.value, this.loginForm.controls.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate(["/users"]);
                },
                error => {
                    alert(error)
                    this.loading = false;
                });
    }
}



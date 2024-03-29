import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import { AppRoutingModule } from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CriptosComponent } from './criptos/criptos.component';
import { UsersRegistrationComponent } from './users/registration/users-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { AuthService } from './_services/auth.service';
import { HttpAuthInterceptor } from './_services/httpInterceptor';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SellBuyComponent } from './sell-buy/sell-buy.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { MyTransactionsComponent } from './my-transactions/my-transactions.component';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import localeFrExtra from '@angular/common/locales/extra/fr';
import { LocalizedDatePipe } from './localized-date.pipe';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

registerLocaleData(localeFr, 'es', localeFrExtra);
registerLocaleData(localeFr, 'en', localeFrExtra);
registerLocaleData(localeFr, 'fr', localeFrExtra);

const PIPES = [
  LocalizedDatePipe
];

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    CriptosComponent,
    UsersRegistrationComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    SellBuyComponent,
    TransactionsComponent,
    MyTransactionsComponent,
    ...PIPES
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [AuthService,  { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true }],
  bootstrap: [AppComponent],
  exports: [...PIPES]
})
export class AppModule { }

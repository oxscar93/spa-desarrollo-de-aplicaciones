import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cripto-app';
  langs: string[] = [];

  constructor(public translate: TranslateService) {
    this.translate.addLangs(['es', 'en']);
    if (!this.translate.currentLang) {
      this.translate.use('es');
    }
    // const browserLang = translate.getBrowserLang();
    // translate.use(browserLang.match(/en|es/) ? browserLang : 'en');
    this.langs = this.translate.getLangs();

    // this.translate.stream('page.login.username').subscribe((res: string) => {
    //   console.log(res);
    // });
  }

  changeLang(lang: string) {
    this.translate.use(lang);
  }
}

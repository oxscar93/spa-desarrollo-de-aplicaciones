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
    this.translate.use('es');
    // const browserLang = translate.getBrowserLang();
    // translate.use(browserLang.match(/en|es/) ? browserLang : 'en');
    this.langs = this.translate.getLangs();
  }

  changeLang(lang: string) {
    this.translate.use(lang);
  }
}

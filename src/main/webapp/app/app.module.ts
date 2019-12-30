import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { TestKkSharedModule } from 'app/shared/shared.module';
import { TestKkCoreModule } from 'app/core/core.module';
import { TestKkAppRoutingModule } from './app-routing.module';
import { TestKkHomeModule } from './home/home.module';
import { TestKkEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    TestKkSharedModule,
    TestKkCoreModule,
    TestKkHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    TestKkEntityModule,
    TestKkAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class TestKkAppModule {}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Router, RouterModule} from "@angular/router";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppComponent } from './app.component';
import {OrdersComponent, TrucksComponent ,PageNotFoundComponent ,EditTruckComponent} from './components';
import {NavigationComponent} from "./components/navigation/navigation.component";

import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {OrderService} from "./services/orders/order.service";
import {TruckService} from "./services/trucks/truck.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TruckProfileComponent} from "./components/truck-profile/truck-profile.component";
import { CreateTruckComponent } from './components';
import { CreateOrderComponent } from './components';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpModule} from "@angular/http";

@NgModule({
  declarations: [
    AppComponent,
    TrucksComponent,
    OrdersComponent,
    PageNotFoundComponent,
    NavigationComponent,
    EditTruckComponent,
    TruckProfileComponent,
    CreateTruckComponent,
    CreateOrderComponent
  ],

  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,

    BrowserAnimationsModule,
    HttpClientModule
  ],

  providers: [OrderService,TruckService],
  bootstrap: [AppComponent],

  exports:[TrucksComponent,
    OrdersComponent,
    PageNotFoundComponent,
    NavigationComponent,
    EditTruckComponent,
    TruckProfileComponent,
    CreateTruckComponent,
    CreateOrderComponent]
})

 export class AppModule {
   constructor(router: Router) {
     console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
    }
 }

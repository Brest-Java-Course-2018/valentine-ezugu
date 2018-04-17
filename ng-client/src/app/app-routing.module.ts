import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {OrdersComponent, TrucksComponent, PageNotFoundComponent} from "./components";
import {EditTruckComponent} from "./components";
import {EditOrderComponent} from "./components/edit-order/edit-order.component";
import {TruckProfileComponent} from "./components/truck-profile/truck-profile.component";

const routes: Routes = [
  {
    path: 'orders',
    component: OrdersComponent
  },
  {
    path: 'trucks',
    component: TrucksComponent
  },
  {
    path: 'editsTrucks/:truckId',
    component: EditTruckComponent
  },
  {
    path: 'editOrders',
    component: EditOrderComponent
  },
  {
    path: 'truckProfile',
    component: TruckProfileComponent
  },
  {
    path: '',
    redirectTo: '/trucks',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


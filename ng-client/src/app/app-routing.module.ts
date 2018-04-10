import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {OrdersComponent, TrucksComponent, PageNotFoundComponent} from "./components";
import {EditTruckComponent} from "./components";

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
    path: 'edits',
    component: EditTruckComponent
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


import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../services/orders/order.service";
import {Router} from "@angular/router";
import {Order} from "../../model/order";

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent implements OnInit {
private order: Order;
  constructor(private orderService: OrderService, router: Router) { }

  ngOnInit() {
    this.order = this.orderService.getter();
  }

}

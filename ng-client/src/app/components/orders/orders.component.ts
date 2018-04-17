import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Order} from "../../model/order";
import {OrderService} from "../../services/orders/order.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})

export class OrdersComponent implements OnInit {

  orders: Observable<Array<Order>>;
  status: number;
  start: Date;
  end: Date;
  order: Order;

  dateForm = new FormGroup({
    date1: new FormControl('', Validators.required),
    date2: new FormControl('', Validators.required)
   });

  statusCode:number;
  private data: Order[];

  constructor(private orderService: OrderService, private router: Router) {
  }

  ngOnInit() {
    this.orders = this.getOrders();
  }

  // private getOrders() {
  //   return this.orderService.getOrders(this.start, this.end)
  // }

  private getOrdersFilter() {
    this.orderService.getOrders(this.start, this.end)
      .subscribe(data => {
        this.data = data;
      }, errorCode => this.statusCode = errorCode);
    return this.orders;
  }

  private getOrders() {
   return this.orderService.getOrdersWithoutDate()
  }

  deleteOrder(order) {
    return this.orderService.deleteOrderById(order.orderId)
      .subscribe((order) => {
          this.router.navigate(['/orders'])
          console.log(order);
        },
        errorCode => this.status = errorCode)
  }

  updateOrder(order) {
    this.orderService.setter(order)
    this.router.navigate(['/editOrders'])
  }

}

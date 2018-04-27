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
  orderz:Order[];
  status: number;
  start: Date;
  end: Date;
  order: Order;

  processValidation = false;
  requestProcessing = false;

  dateForm = new FormGroup({
    date1: new FormControl('', [Validators.required, Validators.pattern("^\\d{4}-\\d{2}-\\d{2}$")]),
    date2: new FormControl('', [Validators.required, Validators.pattern("^\\d{4}-\\d{2}-\\d{2}$")])
   });

  statusCode: any;

  constructor(private orderService: OrderService, private router: Router) {
  }

  ngOnInit() {
    this.orders = this.getOrders();
  }


  get date1() {
    return this.dateForm.get('date1');
  }

  get date2() {
    return this.dateForm.get('date2');
  }


  private getOrdersFilter() {
    this.processValidation = true;
    if (this.dateForm.invalid) {
      return;
    }

    this.preProcessConfigurations();
    this.start = this.dateForm.get('date1').value ;
    this.end = this.dateForm.get('date2').value ;

    this.orders = this.orderService.getOrders(this.start, this.end)

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

  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }
}

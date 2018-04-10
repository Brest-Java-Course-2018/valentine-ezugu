import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable()
export class OrderService {

  baseUrl : string = 'http://localhost:8088/orders';

  constructor(private http: HttpClient) { }


}

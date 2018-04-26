import {TestBed, inject, async} from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrderService } from './order.service';

describe('OrderService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ OrderService ]
    });
  });

  afterEach(inject([HttpTestingController], (httpClient: HttpTestingController) => {
    httpClient.verify();
  }));


  it(`should create`, async(inject([OrderService, HttpTestingController],
    (service: OrderService, httpClient: HttpTestingController) => {
      expect(service).toBeTruthy();
    })));

  it(`get Order by id should return something`, async(inject([OrderService, HttpTestingController],
    (service: OrderService, httpClient: HttpTestingController) => {
      expect(service.getOrderById(1)).toBeTruthy();
    })));

});

import {TestBed, inject, async} from '@angular/core/testing';
import { TruckService } from './truck.service';

import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";


describe('TruckService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ TruckService ]
    });
  });

  afterEach(inject([HttpTestingController], (httpClient: HttpTestingController) => {
    httpClient.verify();
  }));


  it(`should create`, async(inject([TruckService, HttpTestingController],
    (service: TruckService, httpClient: HttpTestingController) => {
      expect(service).toBeTruthy();
    })));


  it(`get Truck by id should return something`, async(inject([TruckService, HttpTestingController],
    (service: TruckService, httpClient: HttpTestingController) => {
      expect(service.getTruckById(1)).toBeTruthy();
    })));

});

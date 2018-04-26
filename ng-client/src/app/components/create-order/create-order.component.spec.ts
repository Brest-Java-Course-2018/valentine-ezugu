import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateOrderComponent } from './create-order.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {TruckService} from "../../services/trucks/truck.service";
import {OrderService} from "../../services/orders/order.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('CreateOrderComponent', () => {

  let component: CreateOrderComponent;
  let fixture: ComponentFixture<CreateOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, RouterTestingModule, HttpClientTestingModule],
      declarations: [ CreateOrderComponent ],

      providers:[TruckService,  OrderService]
    })
    .compileComponents();
  }));

  it('should create the app', async(() => {
      const fixture = TestBed.createComponent(CreateOrderComponent);
      const component = fixture.debugElement.componentInstance;
      expect(component).toBeTruthy();
    })
  );

  it('should fail if not valid', async(() => {
      const fixture = TestBed.createComponent(CreateOrderComponent);
      const component = fixture.debugElement.componentInstance;
      expect(component.invalid).toBeFalsy();
    })
  );

});

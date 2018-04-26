import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTruckComponent } from './create-truck.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TruckService} from "../../services/trucks/truck.service";
 import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";

describe('CreateTruckComponent', () => {
  let component: CreateTruckComponent;
  let fixture: ComponentFixture<CreateTruckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({

      imports: [ReactiveFormsModule, FormsModule,HttpClientTestingModule, RouterTestingModule],
       declarations: [ CreateTruckComponent ],
      providers:[TruckService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTruckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

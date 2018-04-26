import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckProfileComponent } from './truck-profile.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {TruckService} from "../../services/trucks/truck.service";

describe('TruckProfileComponent', () => {
  let component: TruckProfileComponent;
  let fixture: ComponentFixture<TruckProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule,HttpClientTestingModule],
      declarations: [ TruckProfileComponent ],
      providers:[TruckService]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

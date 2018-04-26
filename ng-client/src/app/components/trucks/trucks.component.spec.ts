import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrucksComponent } from './trucks.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {TruckService} from "../../services/trucks/truck.service";

describe('TrucksComponent', () => {
  let component: TrucksComponent;
  let fixture: ComponentFixture<TrucksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule,HttpClientTestingModule],
      declarations: [ TrucksComponent ],
      providers:[TruckService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrucksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

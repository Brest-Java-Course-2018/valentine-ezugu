import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTruckComponent } from './edit-truck.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {TruckService} from "../../services/trucks/truck.service";

describe('EditTruckComponent', () => {
  let component: EditTruckComponent;
  let fixture: ComponentFixture<EditTruckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule,RouterTestingModule,HttpClientTestingModule],
      declarations: [ EditTruckComponent ],
      providers:[TruckService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTruckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

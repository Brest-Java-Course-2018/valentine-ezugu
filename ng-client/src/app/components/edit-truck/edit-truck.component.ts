import {Component, OnInit} from '@angular/core';
import {Truck} from "../../model/truck";
import {TruckService} from "../../services/trucks/truck.service";

import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-truck',
  templateUrl: './edit-truck.component.html',
  styleUrls: ['./edit-truck.component.css']
})
export class EditTruckComponent implements OnInit {

  private truck: Truck;

  public truckForm: FormGroup;


  constructor(private truckService: TruckService, formBuilder: FormBuilder,
              private router: Router) {

    this.truckForm = formBuilder.group({
      truckCode: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      purchasedDate: ['', [Validators.required]],
      descriptions : ['', [Validators.required]]
    });
  }

  // private readonly toastCtrl: ToastController

  ngOnInit() {
    this.truck = this.truckService.getter();
  }


  processForm() {
    if (this.truck  == undefined) {
      this.truckService.createTruck(this.truck)
        .subscribe((truck) => {
        console.log(truck)

        });
    }
    else {
      this.truckService.updateTrucks(this.truck)
        .subscribe(
          (truck) => {
            console.log(truck);
            this.router.navigate([''])
          }, (error) => {
            console.log(error);
          });
    }

  }

  hasError(field: string, error: string) {
    const ctrl = this.truckForm.get(field);
    return ctrl.dirty && ctrl.hasError(error);
  }

  isInvalidAndDirty(field: string) {
    const ctrl = this.truckForm.get(field);
    return !ctrl.valid && ctrl.dirty;
  }


}

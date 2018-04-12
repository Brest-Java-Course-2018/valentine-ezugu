import {Component, OnInit} from '@angular/core';
import {Truck} from "../../model/truck";
import {TruckService} from "../../services/trucks/truck.service";

import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-truck',
  templateUrl: './edit-truck.component.html',
  styleUrls: ['./edit-truck.component.css']
})
export class EditTruckComponent implements OnInit {

  private truck: Truck;
  processValidation = false;
  statusCode: number;
  requestProcessing = false;
  truckForm = new FormGroup({
    truckCode: new FormControl('', Validators.required),
    date: new FormControl('', Validators.required),
    descriptions: new FormControl('', Validators.required)
  });

  constructor(private truckService: TruckService,
              private router: Router) {
  }


  ngOnInit() {
    this.truck = this.truckService.getter();
  }

  back() {
    this.router.navigate(['/trucks'])
  }

  processForm() {

    this.processValidation = true;
    if (this.truckForm.invalid) {
      return; //Validation failed, exit from method.
    }

    // if we are here then all good
    this.preProcessConfigurations()

    let truckCode = this.truckForm.get('truckCode').value.trim();
    let date = this.truckForm.get('date').value.trim();
    let description = this.truckForm.get('descriptions').value.trim();


    if (this.truck.truckId == undefined) {
      let truck = new Truck(null, truckCode, date, description);


      this.truckService.createTruck(truck).subscribe(successCode => {
        this.statusCode = successCode;
        this.router.navigate(['/trucks']);
      }, errorCode => this.statusCode = errorCode);

    } else {
      let truck = new Truck(this.truck.truckId, truckCode, date, description);

      this.truckService.updateTrucks(truck).subscribe(successCode => {
        this.statusCode = successCode;
        this.router.navigate(['/trucks']);
      }, errorCode => this.statusCode = errorCode);

    }
  }

  //Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }


}

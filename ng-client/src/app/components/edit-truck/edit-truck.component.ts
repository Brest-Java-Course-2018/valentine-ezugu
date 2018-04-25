import {Component, Input, OnInit} from '@angular/core';
import {Truck} from "../../model/truck";
import {TruckService} from "../../services/trucks/truck.service";
import 'rxjs/add/operator/switchMap';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";

@Component({
  selector: 'app-edit-truck',
  templateUrl: './edit-truck.component.html',
  styleUrls: ['./edit-truck.component.css']
})

export class EditTruckComponent implements OnInit {

  @Input() truck: Truck;
  processValidation = false;
  statusCode: number;
  requestProcessing = false;

  truckForm = new FormGroup({
    truckCode: new FormControl('', [Validators.required, Validators.minLength(5),Validators.maxLength(7)]),
    descriptions: new FormControl('', [Validators.required, Validators.minLength(7),Validators.maxLength(15)])
  });

  constructor(private truckService: TruckService,
              private router: Router, private route: ActivatedRoute, private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.truckService.getTruckById(+params['truckId']))
      .subscribe((truck) => {
        this.truck = truck;
        console.log("truck");
      });
  }


  get truckCode() { return this.truckForm.get('truckCode'); }

  get descriptions() { return this.truckForm.get('descriptions'); }

  back() {
    this.location.back()
  }

  processForm() {

    this.processValidation = true;
    if (this.truckForm.invalid) {
      return; //Validation failed, exit from method.
    }

    // if we are here then all good
    this.preProcessConfigurations()

    let truckCode = this.truckForm.get('truckCode').value.trim();
    let description = this.truckForm.get('descriptions').value.trim();

    let truck = new Truck(this.truck.truckId, truckCode, this.truck.purchasedDate, description);

    this.truckService.updateTrucks(truck).subscribe(successCode => {
      this.statusCode = successCode;
      this.router.navigate(['/trucks']);
    }, errorCode => this.statusCode = errorCode);

  }

  //Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }


}

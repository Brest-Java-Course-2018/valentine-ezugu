import {Component, OnInit} from '@angular/core';
import {Truck} from "../../model/truck";
import {TruckService} from "../../services/trucks/truck.service";

import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-truck',
  templateUrl: './edit-truck.component.html',
  styleUrls: ['./edit-truck.component.css']
})
export class EditTruckComponent implements OnInit {

  private truck: Truck;



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

    if (this.truck.truckId == undefined) {
      this.truckService.createTruck(this.truck).subscribe((truck) => {
        console.log(truck);
        this.router.navigate(['/trucks'])
      }, (error) => {
        console.log(error);
      });
    }else {

      this.truckService.updateTrucks(this.truck).subscribe((truck) => {
        console.log(truck);
        this.router.navigate(['/trucks'])
      }, (error) => {
        console.log(error);
      });
    }
  }

}

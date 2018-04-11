import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Truck} from "../../model/truck";
import {TruckService} from "../../services/trucks/truck.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trucks',
  templateUrl: './trucks.component.html',
  styleUrls: ['./trucks.component.css']
})

export class TrucksComponent implements OnInit {

  statusCode: number;
  trucks: Observable<Array<Truck>>;

  constructor(private truckService: TruckService, private router: Router) { }

  ngOnInit() {
    this.trucks = this.getTrucksDtos();
  }

    getTrucksDtos() {
       return this.truckService.getTrucks();
      }

  deleteTruck(truck) {
    this.truckService.deleteTruckById(truck.truckId)
      .subscribe(successCode => {
          this.statusCode = successCode;
          this.getTrucksDtos();
          this.router.navigate(['/trucks'])
        },
        errorCode => this.statusCode = errorCode);
   }

  updateTruck(truck) {
    this.truckService.setter(truck)
    this.router.navigate(['/edits'])
  }

  //this gets us edit page with blank forms.
  newTruck() {
    let truck = new Truck()
    this.truckService.setter(truck)
    this.router.navigate(['/edits'])
   }

}


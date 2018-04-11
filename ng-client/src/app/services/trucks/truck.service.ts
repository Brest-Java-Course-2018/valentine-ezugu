import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from "@angular/common/http";

import {Truck} from "../../model/truck";
import {Observable} from "rxjs/Observable";
import {catchError, map, tap} from "rxjs/operators";


@Injectable()
export class TruckService {

  private baseUrl: string = 'http://localhost:8088/trucks';

  private truck: Truck;
  private  headers = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient) {
  }

  getTrucks(): Observable<Truck[]> {
    return this.http.get(this.baseUrl)
      .pipe(
        map(this.extractData),
        tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError)
      );
  }

  deleteTruckById(truckId: number) {
    return this.http.delete(this.baseUrl + "/" + truckId)
      .pipe(
        map(this.extractData),
        catchError(this.handleError)
      );
  }

  updateTrucks(truck: Truck): Observable<any> {
    return this.http.put(this.baseUrl, JSON.stringify(truck), {headers:this.headers})
      .pipe(
        map(this.extractData),
        tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError));
  }

   setter(truck: Truck) {
    this.truck = truck;
   }


  getter() {
    return this.truck;
  }

  createTruck(truck: Truck): Observable<any> {
     return this.http.post(this.baseUrl, JSON.stringify(truck), {headers: this.headers})
      .pipe(
        map(this.extractData),
        tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError));
  }

  getTruckById(id: Number) {
    return this.http.get(this.baseUrl + '/' + id, {headers: this.headers})
      .pipe(map(this.extractData),
        catchError(this.handleError));
  }

  private extractData(response: HttpResponse<Truck>) {
    const body = response;
    return body || {};
  }

  private handleError(err: HttpErrorResponse) {
    let errorMessage: string;

    // A client-side or network error occurred.
    if (err.error instanceof Error) {
      errorMessage = `An error occurred: ${err.error.message}`;
    }

    // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    else {
      errorMessage = `server side  ${err.status}, body was: ${err.error}`;
    }

    console.error(errorMessage);
    return Observable.throw(errorMessage);
  }

}

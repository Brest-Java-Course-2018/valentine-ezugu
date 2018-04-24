import {Observable} from "rxjs/Observable";

/**
 * this class is used to get truck with avg by id.
 */
export class TruckDetail {

  constructor(public truckId: string,
  public truckCode : string,
  public purchasedDate: Date,
  public descriptions : string,
  public avgPerMonth: number,
  public orderList: Observable<Array<TruckDetail>>) {
  }

}



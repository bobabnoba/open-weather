import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../interfaces/city';

@Injectable({
  providedIn: 'root'
})
export class CityWeatherService {

  constructor(private _http : HttpClient) { }

  getAllCities() : Observable<City[]> {
    return this._http.get<City[]>("http://localhost:8080" + "/cities")
  }
}

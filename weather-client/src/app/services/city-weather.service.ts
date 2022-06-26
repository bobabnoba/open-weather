import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../interfaces/city';
import { CityAvgTemp } from '../interfaces/city-avg-temp';

@Injectable({
  providedIn: 'root'
})
export class CityWeatherService {

  constructor(private _http : HttpClient) { }

  getAllCities() : Observable<City[]> {
    return this._http.get<City[]>("http://localhost:8080" + "/cities")
  }

  getAvgTemp(cityIds : number[], from : string | null, to : string | null, sortType : String) : Observable<CityAvgTemp[]> {
    return this._http.get<CityAvgTemp[]>("http://localhost:8080" + "/weather/avg-temperature"
    + "?cityIds=" + cityIds[0] + "&from=" + from + "&to=" + to + "&sortBy=" + sortType)
  }
}

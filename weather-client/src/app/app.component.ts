import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { City } from './interfaces/city';
import { CityWeatherService } from './services/city-weather.service';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Open Weather';

  cities! : City[];
  countryCodes! : String[];
  selectedCity! : City | undefined;
  options : string[] = [];
  searchControl = new FormControl();
  filteredOptions! : Observable<string[]>;

  constructor(private _service : CityWeatherService){
  }

  ngOnInit(): void {
    this._service.getAllCities().subscribe(res =>
      {
        this.cities = res;
        console.log(this.cities); 

        let codes: String[] = []
        res.forEach(c => {
          codes.push(c.countryCode)
        })

        this.countryCodes = [...new Set(codes)]
        this.cities.forEach( c => this.options.push(c.name))
      });

    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))

    )
  }

  selectCity(event: any) {
    this.selectedCity = this.cities.find(c => c.name === event);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()
    return this.options.filter(option =>
      option.toLowerCase().includes(filterValue)
    )
  }

 
  selectedCountryCode = "GB"
  temp = "20"
  daysWithTemp = [
    { day: 'FRIDAY', temp: 22 },
    { day: 'SATURDAY', temp: 25 },
    { day: 'SUNDAY', temp: 25 },
    { day: 'MONDAY', temp: 26 },
    { day: 'TUESDAY', temp: 24 },
  ]
}

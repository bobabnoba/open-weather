import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { City } from './interfaces/city';
import { CityWeatherService } from './services/city-weather.service';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators'
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Open Weather';
  selectedCc! : any;
  cities! : City[];
  countryCodes! : String[];
  selectedCity! : City | undefined;
  options : string[] = [];
  searchControl = new FormControl();
  filteredOptions! : Observable<string[]>;
  today = new Date();
  todayPlusFive = new Date().setDate(this.today.getDate() + 5);
  tempFor5Days! : number;
  dateRange! : any;

  constructor(private _service : CityWeatherService, public datePipe :DatePipe){
    this.formatDateRange(); 
  }

  ngOnInit(): void {
    this._service.getAllCities().subscribe(res =>
      {
        this.cities = res;

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
    this.selectedCc = this.countryCodes.find(cc => this.selectedCity!.countryCode === cc);
    this.avgTempForNext5days(this.selectedCity!.id);
    this.avgTempByDays(this.selectedCity!.id)
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()
    return this.options.filter(option =>
      option.toLowerCase().includes(filterValue)
    )
  }

  private formatDate(date : any){
    return this.datePipe.transform(date, 'yyyy-MM-dd')
  }
  
  avgTempForNext5days(cityId : number){
    this._service.getAvgTemp([cityId], this.formatDate(this.today), this.formatDate(this.todayPlusFive), "asc").subscribe(
      res => {
        this.tempFor5Days = res[0].avgTemperature
      }
    )
  }

  formatDateRange(){
    this.dateRange = this.datePipe.transform(this.today, 'MMM dd, yyyy') + ' - ' + this.datePipe.transform(this.todayPlusFive, 'MMM dd, yyyy')
    if (this.today.getFullYear() === new Date(this.todayPlusFive).getFullYear()){
      this.dateRange = this.datePipe.transform(this.today, 'MMM dd') + ' - ' + this.datePipe.transform(this.todayPlusFive, 'MMM dd, yyyy')
      if(this.today.getMonth() === new Date(this.todayPlusFive).getMonth()){
        this.dateRange = this.datePipe.transform(this.today, 'MMM dd') + ' - ' + this.datePipe.transform(this.todayPlusFive, 'dd, yyyy')
      }
    }
  }

  avgTempByDays(cityId : number){
    for(let i = 1; i < 6; i++) {
      this.daysWithTemp[i-1].day = this.daysOfTheWeek[new Date(new Date().setDate(this.today.getDate() + i)).getDay()]
      this._service.getAvgTemp([cityId], 
        this.formatDate(new Date().setDate(this.today.getDate() + i)), 
        this.formatDate(new Date().setDate(this.today.getDate() + i+1)), 
        'asc').subscribe(
          res => this.daysWithTemp[i-1].temp = res[0].avgTemperature
        )
      
    }

  }

  daysOfTheWeek = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY']
  daysWithTemp = [
    { day: '', temp: 0 },
    { day: '', temp: 0 },
    { day: '', temp: 0 },
    { day: '', temp: 0 },
    { day: '', temp: 0 },
  ]
}

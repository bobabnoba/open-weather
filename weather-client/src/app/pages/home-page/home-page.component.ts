import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { City } from '../../interfaces/city';
import { CityWeatherService } from '../../services/city-weather.service';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators'
import { DatePipe } from '@angular/common';
import { ColorService } from '../../services/color.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

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
  bgGradient: string =
   "linear-gradient(129deg, rgba(87,160,221,1) 0%, rgba(180,225,255,1) 69%, rgba(238,220,168,1) 94%)"

  constructor(private _service : CityWeatherService, private _colorService : ColorService,  public datePipe :DatePipe){
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
        this.tempFor5Days = res[0].avgTemperature;
        this.bgGradient = `linear-gradient(127deg,
          rgba(73,158,229,0.3) 4%, rgba(169,218,251,0.3) 50%,
          ${this._colorService.getColorForTemperature(res[0].avgTemperature, 0.7)} 90%)`
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

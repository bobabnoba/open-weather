import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'weather-client';
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

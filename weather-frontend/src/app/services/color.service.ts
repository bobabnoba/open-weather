import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColorService {
  
  constructor() { }

  private percentColors = [
    { pct: 0.0, color: { r: 0x1b, g: 0x35, b: 0x7d } },
    { pct: 0.25, color: { r: 0x46, g: 0x99, b: 0xde } },
    { pct: 0.5, color: { r: 0x73, g: 0xb5, b: 0xe8 } },
    { pct: 0.75, color: { r: 0xf7, g: 0xcf, b: 0x6f } },
    { pct: 1.0, color: { r: 0xf1, g: 0x88, b: 0x62 } }];

  getColorForTemperature(temperature: number, opacity: number) {
    let pct = (temperature + 40) / 80;
    for (var i = 1; i < this.percentColors.length - 1; i++) {
      if (pct < this.percentColors[i].pct) {
        break;
      }
      
    }
    let lower = this.percentColors[i - 1];
    let upper = this.percentColors[i];
    let range = upper.pct - lower.pct;
    let rangePct = (pct - lower.pct) / range;
    let pctLower = 1 - rangePct;
    let pctUpper = rangePct;

    let color = {
      r: Math.floor(lower.color.r * pctLower + upper.color.r * pctUpper),
      g: Math.floor(lower.color.g * pctLower + upper.color.g * pctUpper),
      b: Math.floor(lower.color.b * pctLower + upper.color.b * pctUpper)
    };
    return 'rgba(' + [color.r, color.g, color.b, opacity].join(',') + ')';
  };
}

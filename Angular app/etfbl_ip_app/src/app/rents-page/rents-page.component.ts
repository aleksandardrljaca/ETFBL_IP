import { Component } from '@angular/core';

@Component({
  selector: 'app-rents-page',
  templateUrl: './rents-page.component.html',
  styleUrl: './rents-page.component.css'
})
export class RentsPageComponent {
  constructor(){
    localStorage.removeItem('vehicleId')
  }
}

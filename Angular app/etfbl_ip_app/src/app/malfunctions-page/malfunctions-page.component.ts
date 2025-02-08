import { Component } from '@angular/core';

@Component({
  selector: 'app-malfunctions-page',
  templateUrl: './malfunctions-page.component.html',
  styleUrl: './malfunctions-page.component.css'
})
export class MalfunctionsPageComponent {
  constructor(){
    localStorage.removeItem('vehicleId');
  }
}

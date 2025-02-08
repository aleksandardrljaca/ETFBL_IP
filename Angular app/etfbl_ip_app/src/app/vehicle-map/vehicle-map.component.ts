import { Component, OnInit} from '@angular/core';
import * as L from 'leaflet';
import { RentService } from '../services/rent-service/rent.service';

@Component({
  selector: 'app-vehicle-map',
  templateUrl: './vehicle-map.component.html',
  styleUrl: './vehicle-map.component.css'
})
export class VehicleMapComponent implements OnInit {
  map!: L.Map; 
  lat: number = 44.7722; //Banja Luka
  lng: number = 17.191;  //Banja Luka
  markers: L.Marker[] = [];
  markerGroup!: L.LayerGroup;
  rents:any[]=[];
  constructor(private rentService:RentService){}
  ngOnInit(): void {
    this.initMap(); 
    this.loadData();
  }
  loadData(){
    this.rentService.getCurrentRents().subscribe({
      next:(data)=>this.rents=data,
      error:(err)=>console.log(err)
    });
  }
  onSelectionChange(event:any):void{
    const rent=event.value;
    if(rent){
      this.removeAllMarkers();
      const marker1 = L.marker([rent.locationByStartLocationLatitude, rent.locationByStartLocationLongitude]).addTo(this.markerGroup);
      const marker2 = L.marker([rent.locationByEndLocationLatitude, rent.locationByEndLocationLongitude]).addTo(this.markerGroup);
      this.markers.push(marker1);
      this.markers.push(marker2);
      marker1.bindPopup('Start location!').openPopup();
      marker2.bindPopup('End location!').openPopup();
    }
  }
  initMap(): void {
    this.map = L.map('map', {
      center: [this.lat, this.lng],
      zoom: 13
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(this.map);

    this.markerGroup = L.layerGroup().addTo(this.map);
  }
  removeAllMarkers(): void {
    this.markerGroup.clearLayers(); 
  }
}

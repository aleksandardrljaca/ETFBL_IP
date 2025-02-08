import { HttpClient } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private httpClient:HttpClient) { }

  addVehicle(vehicleData:any): Observable<any> {
    return this.httpClient.post('http://localhost:8000/api/vehicles',vehicleData);
  }
  getVehicles(){
    return this.httpClient.get<any>('http://localhost:8000/api/vehicles');
  }
  delete(id:string){
    return this.httpClient.delete('http://localhost:8000/api/vehicles/'+id);
  }
  
  insertFromCSV(formData:FormData){
    return this.httpClient.post('http://localhost:8000/api/vehicles/upload-csv',formData);
  }
  getCurrentVehicle():string{
    const vehicle=localStorage.getItem('vehicleId');
    if(vehicle)
      return vehicle;
    return '';
  }
  getById(id:string){
    return this.httpClient.get<any>('http://localhost:8000/api/vehicles/'+id);
  }
}

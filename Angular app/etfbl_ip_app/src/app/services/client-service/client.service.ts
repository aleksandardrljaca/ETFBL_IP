import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  baseUrl='http://localhost:8000/api/clients';
  constructor(private http:HttpClient) { }
  getAll(){
    return this.http.get<any>(this.baseUrl);
  }
  block(id:number,data:any){
    return this.http.put(this.baseUrl+'/'+id,data);
  }
}

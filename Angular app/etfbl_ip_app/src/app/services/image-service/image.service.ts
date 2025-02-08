import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient) { }
  insertImage(data:FormData){
    return this.http.post('http://localhost:8000/api/images',data);
  }
}

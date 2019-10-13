import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Location} from './location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private baseUrl = 'http://localhost:8102/locations';

  constructor(private http: HttpClient) { }

  createLocation(location: Location): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, location);
  }

  getLocationList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAll`);
  }

  getLocationInRadius(locationId: string, radius: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getLocationInRadius/` + locationId + '/' + radius);
  }
}

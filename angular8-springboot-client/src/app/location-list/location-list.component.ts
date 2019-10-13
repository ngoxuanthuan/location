import {Component, OnInit} from '@angular/core';
import {Location} from '../location';
import {Observable} from 'rxjs';
import {LocationService} from '../location.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-location-list',
  templateUrl: './location-list.component.html',
  styleUrls: ['./location-list.component.css']
})
export class LocationListComponent implements OnInit {

  locationId: string;
  radius: number;

  employees: Observable<Location[]>;
  submitted = false;

  constructor(private locationService: LocationService,
              private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.employees = this.locationService.getLocationList();
  }

  addLocation() {
    this.router.navigate(['./add']);
  }

  search() {
    this.employees = this.locationService.getLocationInRadius(this.locationId, this.radius);
  }
}

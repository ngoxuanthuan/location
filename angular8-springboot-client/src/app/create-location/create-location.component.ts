import {Component, OnInit} from '@angular/core';
import {Location} from '../location';
import {LocationService} from '../location.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-location',
  templateUrl: './create-location.component.html',
  styleUrls: ['./create-location.component.css']
})
export class CreateLocationComponent implements OnInit {

  location: Location = new Location();

  constructor(private locationService: LocationService, private router: Router) {
  }

  ngOnInit() {
  }

  save() {
    this.locationService.createLocation(this.location)
      .subscribe(data => console.log(data), error => console.log(error));
    this.location = new Location();
    this.gotoList();
  }

  onSubmit() {
    this.save();
  }

  gotoList() {
    this.router.navigate(['/locationList']);
  }
}

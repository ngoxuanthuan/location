import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LocationListComponent} from './location-list/location-list.component';
import {CreateLocationComponent} from './create-location/create-location.component';

const routes: Routes = [
  { path: '', redirectTo: 'locationList', pathMatch: 'full' },
  { path: 'locationList', component: LocationListComponent },
  { path: 'add', component: CreateLocationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

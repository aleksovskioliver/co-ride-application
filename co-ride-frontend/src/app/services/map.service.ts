import { Injectable } from '@angular/core';
import { NewType } from 'src/app/models/newType'

@Injectable({
  providedIn: 'root'
})
export class MapService {
  markers: NewType[]= [];

  constructor() { }

  addMarker(lat: number,lng:number) {
    if(this.markers.length == 2){
      this.markers.splice(0);
    }  
    this.markers.push({
      position: {
        lat: lat,
        lng: lng,
      },
      label: {
        color: "red",
        text: 'Marker label 1',
      },
      title: 'Marker title 1',
      info: 'Marker info 2',
      options: {
        animation: google.maps.Animation.BOUNCE,
       }
      });
    }
}

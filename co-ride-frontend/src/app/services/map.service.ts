import {Injectable} from '@angular/core';
import {MapType} from 'src/app/models/MapType'

@Injectable({
  providedIn: 'root'
})
export class MapService {
  markers: MapType[] = [];

  constructor() {
  }

  addMarker(lat: number, lng: number) {
    if (this.markers.length == 2) {
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

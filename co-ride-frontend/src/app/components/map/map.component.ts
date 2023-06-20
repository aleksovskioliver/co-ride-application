import { Component, OnInit, ViewChild } from '@angular/core'
import { MapInfoWindow, GoogleMap } from '@angular/google-maps'
import { MapService } from 'src/app/services/map.service'
import { NewType } from 'src/app/models/newType'
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit{

  @ViewChild(GoogleMap, { static: false })
  map!: GoogleMap
  @ViewChild(MapInfoWindow, { static: false })
  info!: MapInfoWindow

  constructor(private service: MapService){}
  
  zoom = 8.5
  center!: google.maps.LatLngLiteral
  options: google.maps.MapOptions = {
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    mapTypeId: 'roadmap',
    maxZoom: 16,
    minZoom: 8,
  }
  markers: NewType[] = this.service.markers;
  infoContent = ''

  ngOnInit() {
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: 41.724182,
        lng: 21.774216,
      }
    })
  }

  zoomIn() {
    if (this.zoom < this.options.maxZoom!) this.zoom++
  }

  zoomOut() {
    if (this.zoom > this.options.minZoom!) this.zoom--
  }

  click(event: google.maps.MapMouseEvent) {
    console.log(event)
  }

  logCenter() {
    console.log(JSON.stringify(this.map.getCenter()))
  }
  
}

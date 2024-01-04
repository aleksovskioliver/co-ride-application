export interface MapType {
    position: {
      lat: number
      lng: number
    }
    label: {
      color: string
      text: string
    }
    title: string
    info: string
    options: {
      animation: google.maps.Animation
    }
  }

export interface NewType {
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
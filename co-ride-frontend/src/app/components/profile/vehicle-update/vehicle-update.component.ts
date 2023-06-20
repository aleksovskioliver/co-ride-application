import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vehicle } from 'src/app/models/Vehicle';
import { VehiclesService } from 'src/app/services/vehicles.service';

@Component({
  selector: 'app-vehicle-update',
  templateUrl: './vehicle-update.component.html',
  styleUrls: ['./vehicle-update.component.css']
})
export class VehicleUpdateComponent{

  id: number | undefined;
  vehicle: Vehicle | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private vehicleService: VehiclesService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.vehicleService.getVehicleById(this.id!)
      .subscribe({
        next: (data)=>this.vehicle = data})
   }

  updateVehicle(){
    this.vehicleService.updateVehicle(this.id!,this.vehicle!)
      .subscribe(() => {
        this.router.navigate(['/profile']);
      })
    }

  onSubmit(){
    this.updateVehicle();
  }
}

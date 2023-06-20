import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { SearchComponent } from './components/search/search.component';
import { UpdateComponent } from './components/profile/update/update.component';
import { VehicleUpdateComponent } from './components/profile/vehicle-update/vehicle-update.component';
import { UpdateFormComponent } from './components/reservation-form/update-form/update-form.component';


const routes: Routes = [
  { path: "home", component: DashboardComponent },
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "reservation", component: ReservationComponent },
  { path: "profile", component: ProfileComponent },
  { path: "search", component: SearchComponent },
  { path: "vehicle/:id", component: VehicleUpdateComponent},
  { path: "update/:id", component: UpdateComponent},
  { path: "reservation/update/:id",component: UpdateFormComponent},
  { path: "", redirectTo: "home", pathMatch: "full" },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GoogleMapsModule } from '@angular/google-maps'
import { NgxPaginationModule } from 'ngx-pagination'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterComponent } from './components/register/register.component';


import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { MapComponent } from './components/map/map.component';
import { LoginComponent } from './components/login/login.component';
import { PhoneMaskDirective } from './directives/phone-mask.directive';
import { SearchComponent } from './components/search/search.component';
import { ReservationFormComponent } from './components/reservation-form/reservation-form.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { ProfileComponent } from './components/profile/profile.component';
import { CreatedComponent } from './components/reservation/created/created.component';
import { UpdateComponent } from './components/profile/update/update.component';
import { VehicleUpdateComponent } from './components/profile/vehicle-update/vehicle-update.component';
import { UpdateFormComponent } from './components/reservation-form/update-form/update-form.component';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCarSide, faUserPen, faCircleUser, faCoins, faPhone, faRoute, faRightLong, faCar } from '@fortawesome/free-solid-svg-icons';

@NgModule({
  declarations: [
    AppComponent,
    ReservationComponent,
    DashboardComponent,
    RegisterComponent,
    FooterComponent,
    HeaderComponent,
    MapComponent,
    LoginComponent,
    PhoneMaskDirective,
    SearchComponent,
    ReservationFormComponent,
    ProfileComponent,
    CreatedComponent,
    UpdateComponent,
    VehicleUpdateComponent,
    UpdateFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    GoogleMapsModule,
    FormsModule,
    NgxPaginationModule,
    FontAwesomeModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIcons(
      faCarSide,
      faCircleUser,
      faCoins,
      faPhone,
      faRoute,
      faRightLong,
      faUserPen,
      faCar
    );
  }
}
import { Reservation } from "./Reservation";
import { Vehicle } from "./Vehicle";

export interface User {
    id: number
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    password: string;
    role: string;
    vehicle: Vehicle | null;
    reservation: Reservation[];
}
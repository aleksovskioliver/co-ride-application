import { Vehicle } from "./Vehicle";

export interface UserDTO {
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    password: string;
    role: string;
    vehicle: Vehicle | null;
}
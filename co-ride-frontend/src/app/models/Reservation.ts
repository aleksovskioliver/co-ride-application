import { MyLocation } from "./MyLocation";
import { User } from "./User";

export interface Reservation{
    id: number;
    driver: User;
    startTime: string;
    endTime: string;
    pickupLocation: MyLocation;
    dropoutLocation: MyLocation;
    tripCost: number;
    status: string;
    availableSeats: number;
    customers: User[]
}
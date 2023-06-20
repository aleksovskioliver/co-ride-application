export interface ReservationDTO {
    startTime: string,
    endTime: string,
    pickupLocation: string,
    dropoutLocation: string,
    tripCost: number,
    availableSeats: number
}
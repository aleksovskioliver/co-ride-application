import { User } from "./User";

export interface GetUserResponse {
    user?: User;
    error?: string;
}
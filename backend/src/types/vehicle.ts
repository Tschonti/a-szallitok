/**
 * A szállítók - OpenAPI 3.0
 * API documentation for the backend of The Transporters project
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

export interface Vehicle { 
    id?: number;
    plateNumber?: string;
    type?: string;
    yearOfManufacturing?: number;
    location?: string;
    maxWeight?: number;
    maxHeight?: number;
    maxLength?: number;
    maxWidth?: number;
    pictureUrl?: string;
}
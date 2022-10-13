'use strict';


/**
 * Add a new vehicle
 * Add a new vehicle
 *
 * body Vehicle Add a new vehicle to the database
 * returns Vehicle
 **/
exports.addVehicle = function(body) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "maxHeight" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "yearOfManufacturing" : 2008,
  "location" : "Telephely",
  "id" : 10,
  "maxWeight" : 2,
  "plateNumber" : "AD0000",
  "type" : "Volkswagen Transporter",
  "maxLength" : 2,
  "maxWidth" : 2
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Find a vehicle by ID
 * Returns a single vehicle
 *
 * iD Long ID of the vehicle to return
 * returns Vehicle
 **/
exports.getVehicleById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "maxHeight" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "yearOfManufacturing" : 2008,
  "location" : "Telephely",
  "id" : 10,
  "maxWeight" : 2,
  "plateNumber" : "AD0000",
  "type" : "Volkswagen Transporter",
  "maxLength" : 2,
  "maxWidth" : 2
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update an existing vehicle
 * Update an existing vehicle
 *
 * body Vehicle Update an existent vehicle in the database
 * iD Long ID of the vehicle to return
 * returns Vehicle
 **/
exports.updateVehicle = function(body,iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "maxHeight" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "yearOfManufacturing" : 2008,
  "location" : "Telephely",
  "id" : 10,
  "maxWeight" : 2,
  "plateNumber" : "AD0000",
  "type" : "Volkswagen Transporter",
  "maxLength" : 2,
  "maxWidth" : 2
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


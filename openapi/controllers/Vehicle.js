'use strict';

var utils = require('../utils/writer.js');
var Vehicle = require('../service/VehicleService');

module.exports.addVehicle = function addVehicle (req, res, next, body) {
  Vehicle.addVehicle(body)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getVehicleById = function getVehicleById (req, res, next, iD) {
  Vehicle.getVehicleById(iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.updateVehicle = function updateVehicle (req, res, next, body, iD) {
  Vehicle.updateVehicle(body, iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

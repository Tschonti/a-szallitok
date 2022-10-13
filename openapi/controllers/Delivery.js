'use strict';

var utils = require('../utils/writer.js');
var Delivery = require('../service/DeliveryService');

module.exports.addDelivery = function addDelivery (req, res, next, body) {
  Delivery.addDelivery(body)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.delDeliveryById = function delDeliveryById (req, res, next, iD) {
  Delivery.delDeliveryById(iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getDelivery = function getDelivery (req, res, next) {
  Delivery.getDelivery()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getDeliveryById = function getDeliveryById (req, res, next, iD) {
  Delivery.getDeliveryById(iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.rateClient = function rateClient (req, res, next, body, iD) {
  Delivery.rateClient(body, iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.rateTransporter = function rateTransporter (req, res, next, body, iD) {
  Delivery.rateTransporter(body, iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.updateDelivery = function updateDelivery (req, res, next, body, iD) {
  Delivery.updateDelivery(body, iD)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

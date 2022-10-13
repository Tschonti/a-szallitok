'use strict';


/**
 * Add a new delivery
 * Add a new delivery
 *
 * body Delivery Add a new ddelivery to the database
 * returns Delivery
 **/
exports.addDelivery = function(body) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Delete a delivery by ID
 * Delete a single delivery
 *
 * iD Long ID of the delivery to return
 * returns Delivery
 **/
exports.delDeliveryById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Get all deliveries
 * Returns all deliveries
 *
 * returns List
 **/
exports.getDelivery = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
}, {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Find a delivery by ID
 * Returns a single delivery
 *
 * iD Long ID of the delivery to return
 * returns Delivery
 **/
exports.getDeliveryById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Rate the client of the delivery
 * Rate the client of the delivery
 *
 * body ID_rateClient_body The rating of the client
 * iD Long ID of the delivery to edit
 * returns Delivery
 **/
exports.rateClient = function(body,iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Rate the transporter of the delivery
 * Rate the transporter of the delivery
 *
 * body ID_rateTransporter_body The rating of the transporter
 * iD Long ID of the delivery to edit
 * returns Delivery
 **/
exports.rateTransporter = function(body,iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update an existing delivery
 * Update an existing delivery
 *
 * body Delivery Update an existent delivery in the database
 * iD Long ID of the delivery to edit
 * returns Delivery
 **/
exports.updateDelivery = function(body,iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "clientUserId" : 2,
  "transporterUserId" : 2,
  "pictureUrl" : "file:///defaultImage.png",
  "length" : 2,
  "description" : "Please deliver it!",
  "weight" : 2,
  "title" : "Brick delivery",
  "createdAt" : "2022.10.10",
  "clientRating" : 2,
  "pickUpUntil" : "2022.10.20",
  "price" : 10000,
  "width" : 2,
  "sourceLocation" : {
    "country" : "Hungary",
    "coordinate" : {
      "latitude" : -19.47381,
      "longitude" : 14.45529
    },
    "address" : "Irinyi József utca 42",
    "city" : "Budapest",
    "postalCode" : 1117
  },
  "transporterRating" : 2,
  "id" : 2,
  "pickUpFrom" : "2022.10.10",
  "height" : 2,
  "status" : "DONE",
  "updatedAt" : "2022.10.20"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


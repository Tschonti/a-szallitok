'use strict';


/**
 * Add a new users' profile
 * Add a new users' profile
 *
 * body User Add a new users' profile to the database
 * returns User
 **/
exports.addUser = function(body) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Delete user by ID
 * Delete a single user
 *
 * iD Long ID of user to return
 * returns User
 **/
exports.delUserById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Find user by ID
 * Returns a single user
 *
 * iD Long ID of user to return
 * returns User
 **/
exports.getUserById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Find user by ID
 * Returns a single user
 *
 * returns List
 **/
exports.getUserToplist = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "firstName" : "John",
  "lastName" : "James",
  "moneyEarned" : 100000,
  "deliveriesCompleted" : 10,
  "id" : 10
}, {
  "firstName" : "John",
  "lastName" : "James",
  "moneyEarned" : 100000,
  "deliveriesCompleted" : 10,
  "id" : 10
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Get all users
 * Get all users
 *
 * returns List
 **/
exports.getUsers = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
}, {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Promote user by id
 * Promote user to admin role
 *
 * iD Long ID of user to return
 * returns User
 **/
exports.promUserById = function(iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Update an existing users' profile
 * Update an existing users' profile by UID
 *
 * body User Update an existent user in the database
 * iD Long ID of user to return
 * returns User
 **/
exports.updateUser = function(body,iD) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "profilePictureUrl" : "files:///defaultImage.png",
  "firstName" : "John",
  "lastName" : "James",
  "googletoken" : "U2314251234234",
  "phoneNumber" : "12345",
  "id" : 10,
  "vehicleId" : 4,
  "isAdmin" : false,
  "email" : "john@email.com"
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


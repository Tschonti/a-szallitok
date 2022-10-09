CREATE TABLE `User` (
  `id` int PRIMARY KEY,
  `firstName` varchar(255),
  `lastName` varchar(255),
  `googleToken` varchar(255),
  `email` varchar(255),
  `phoneNumber` varchar(255),
  `vehicleId` int,
  `isAdmin` boolean,
  `profilePictureUrl` varchar(255)
);

CREATE TABLE `DeliveryToUser` (
  `deliveryId` int,
  `userId` int,
  `status` int,
  `updatedAt` datetime,
  PRIMARY KEY (`deliveryId`, `userId`)
);

CREATE TABLE `Delivery` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `sourceCoordId` int,
  `destinationCoordId` int,
  `clientUserId` int,
  `transporterUserId` int,
  `pickUpFrom` datetime,
  `pickUpUntil` datetime,
  `price` int,
  `capacityId` int,
  `status` DeliveryStatus,
  `pictureUrl` varchar(255),
  `createdAt` datetime,
  `updatedAt` datetime
);

CREATE TABLE `Capacity` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `weight` int,
  `height` int,
  `length` int,
  `width` int
);

CREATE TABLE `Coordinate` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `longitude` int,
  `latitude` int
);

CREATE TABLE `Rating` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `deliveryId` int,
  `ratedUserId` int,
  `raterUserID` int,
  `rating` int,
  `createdAt` datetime
);

CREATE TABLE `Vehicle` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `plateNumber` varchar(255),
  `type` varchar(255),
  `yearOfManufacturing` int,
  `capacityId` int,
  `location` varchar(255),
  `pictureUrl` varchar(255)
);

ALTER TABLE `User` ADD FOREIGN KEY (`vehicleId`) REFERENCES `Vehicle` (`id`);

ALTER TABLE `DeliveryToUser` ADD FOREIGN KEY (`userId`) REFERENCES `User` (`id`);

ALTER TABLE `DeliveryToUser` ADD FOREIGN KEY (`deliveryId`) REFERENCES `Delivery` (`id`);

ALTER TABLE `Rating` ADD FOREIGN KEY (`deliveryId`) REFERENCES `Delivery` (`id`);

ALTER TABLE `Rating` ADD FOREIGN KEY (`raterUserID`) REFERENCES `User` (`id`);

ALTER TABLE `Rating` ADD FOREIGN KEY (`ratedUserId`) REFERENCES `User` (`id`);

ALTER TABLE `Delivery` ADD FOREIGN KEY (`capacityId`) REFERENCES `Capacity` (`id`);

ALTER TABLE `Vehicle` ADD FOREIGN KEY (`capacityId`) REFERENCES `Capacity` (`id`);

ALTER TABLE `Delivery` ADD FOREIGN KEY (`sourceCoordId`) REFERENCES `Coordinate` (`id`);

ALTER TABLE `Delivery` ADD FOREIGN KEY (`destinationCoordId`) REFERENCES `Coordinate` (`id`);

ALTER TABLE `User` ADD FOREIGN KEY (`id`) REFERENCES `Delivery` (`clientUserId`);

ALTER TABLE `User` ADD FOREIGN KEY (`id`) REFERENCES `Delivery` (`transporterUserId`);

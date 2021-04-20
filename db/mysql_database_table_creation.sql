-- Crea el esquema de base de datos
CREATE SCHEMA `Programa Movil` ;

-- Lo usa
USE `Programa Movil`;

-- Crea una tabla para el formulario a llenar con nombre, apellido y edad
CREATE TABLE Formulario (
  `idForm` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  PRIMARY KEY (`idForm`));
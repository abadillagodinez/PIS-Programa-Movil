-- Crea el esquema de base de datos
CREATE SCHEMA `Programa Movil` ;

-- Lo usa
USE `Programa Movil`;

-- Crea una tabla para el formulario a llenar con nombre, apellido y edad
CREATE TABLE `Programa Movil`.`Formulario` (
  `idFormulario` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Edad` INT NOT NULL,
  PRIMARY KEY (`idFormulario`));
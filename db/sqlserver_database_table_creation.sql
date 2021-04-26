-- Se borra la base de datos si existe
USE master;
DROP DATABASE IF EXISTS ProgramaMovil;
GO

-- Creación de la base de datos
CREATE DATABASE ProgramaMovil;
GO

-- se utiliza la base de datos
USE ProgramaMovil;
GO

-- Se crea la tabla de formulario
CREATE TABLE Formulario (
    formID int IDENTITY(1,1) PRIMARY KEY,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255) NOT NULL,
    Age int not null
);
GO
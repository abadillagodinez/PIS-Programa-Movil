USE `Programa Movil`;
DROP PROCEDURE IF EXISTS `usp_Create_Formulario`;
DROP PROCEDURE IF EXISTS `usp_Update_Formulario`;
DROP PROCEDURE IF EXISTS `usp_Read_Formulario`;
DROP PROCEDURE IF EXISTS `usp_Delete_Formulario`;

DELIMITER $$
USE `Programa Movil`$$
CREATE PROCEDURE `usp_Create_Formulario`
    (IN pName VARCHAR(45), IN pLastName VARCHAR(45), IN p_Age INT)
BEGIN
IF pName IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Name.';
ELSEIF pLastName IS NULL THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Last Name.';
ELSEIF p_Age IS NULL THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Age.';
  END IF;
  INSERT INTO Formulario (Formulario.Name, Formulario.LastName, Formulario.Age) VALUES (pName, pLastName, p_Age);
END$$

DELIMITER ;


DELIMITER $$
USE `Programa Movil`$$
CREATE PROCEDURE `usp_Update_Formulario`
    (IN pId INT,IN pName VARCHAR(45), IN pLastName VARCHAR(45), IN p_Age INT)
BEGIN
IF pId IS NULL THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on ID.';
ELSEIF pName IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Name.';
ELSEIF pLastName IS NULL THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Last Name.';
ELSEIF p_Age IS NULL THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NULL is not allowed on Age.';
END IF;
  UPDATE Formulario 
  SET Formulario.Name = pName, Formulario.LastName = pLastName, Formulario.Age = p_Age
  where Formulario.idForm = pId;
END$$

DELIMITER ;

DELIMITER $$
USE `Programa Movil`$$
CREATE PROCEDURE `usp_Read_Formulario`
    ()
BEGIN
  SELECT Formulario.idForm as ID, Formulario.Name as Nombre, Formulario.LastName as Apellido, Formulario.Age as Edad
  FROM Formulario;
END$$

DELIMITER ;

DELIMITER $$
USE `Programa Movil`$$
CREATE PROCEDURE `usp_Delete_Formulario`
    (IN pId INT)
BEGIN
    DELETE FROM Formulario
    where Formulario.idForm = pId;
END$$

DELIMITER ;


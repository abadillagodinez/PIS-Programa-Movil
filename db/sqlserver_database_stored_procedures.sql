USE ProgramaMovil;
DROP PROC IF EXISTS usp_Create_Formulario;
DROP PROC IF EXISTS usp_Read_Formulario;
DROP PROC IF EXISTS usp_Update_Formulario;
DROP PROC IF EXISTS usp_Delete_Formulario;


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE usp_Create_Formulario
	@pName VARCHAR(255),
	@pLastName VARCHAR(255),
	@pAge INT
AS
BEGIN
	IF(@pName IS NOT NULL AND @pLastName IS NOT NULL AND @pAge IS NOT NULL)
	BEGIN
		INSERT INTO Formulario (FirstName, LastName, Age)
		VALUES (@pName, @pLastName, @pAge);
	END;
END
GO

		
CREATE PROCEDURE usp_Read_Formulario
AS
BEGIN
	SELECT formID as ID, FirstName, LastName, Age
	FROM Formulario;
END
GO

CREATE PROCEDURE usp_Update_Formulario
	@pFormID INT,
	@pName VARCHAR(255),
	@pLastName VARCHAR(255),
	@pAge INT 
AS
BEGIN
	IF(@pName IS NOT NULL AND @pLastName IS NOT NULL AND @pAge IS NOT NULL)
	BEGIN
		UPDATE Formulario
		SET FirstName = @pName, LastName = @pLastName, Age = @pAge 
		WHERE formID = @pFormID;
		RETURN 1;
	END;
	ELSE
	BEGIN
		RETURN 0
	END;
END
GO

CREATE PROCEDURE usp_Delete_Formulario
	@pFormId INT
AS
BEGIN
	IF(@pFormId IS NOT NULL)
	BEGIN
		DELETE FROM Formulario
		WHERE formID = @pFormId;
	END
END
GO
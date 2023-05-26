USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'LAB1')
    DROP DATABASE LAB1;
GO

CREATE DATABASE LAB1;
GO

USE LAB1;
GO

CREATE TABLE tbl_User (
    userID NVARCHAR(20) PRIMARY KEY,
    password NVARCHAR(20) NOT NULL,
    fullName NVARCHAR(40) NOT NULL,
    role NVARCHAR(2) NOT NULL,
    CONSTRAINT CHK_UserID CHECK (userID LIKE 'AD[0-9][0-9][0-9][0-9]' OR userID LIKE 'US[0-9][0-9][0-9][0-9]'),
    CONSTRAINT CHK_Role CHECK (role LIKE 'AD' OR role LIKE 'US')
);
GO

INSERT INTO tbl_User
VALUES
    ('AD0000', 'admin', 'John Doe', 'AD'),
    ('US0000', '12345', 'Jane Doe', 'US');
GO
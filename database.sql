USE [master]
GO
/****** Object:  Database [PlantManager]    Script Date: 11/30/2021 4:52:31 PM ******/
CREATE DATABASE [PlantManager]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PlantManager', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\PlantManager.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PlantManager_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\PlantManager_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PlantManager] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PlantManager].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PlantManager] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PlantManager] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PlantManager] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PlantManager] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PlantManager] SET ARITHABORT OFF 
GO
ALTER DATABASE [PlantManager] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PlantManager] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PlantManager] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PlantManager] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PlantManager] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PlantManager] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PlantManager] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PlantManager] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PlantManager] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PlantManager] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PlantManager] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PlantManager] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PlantManager] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PlantManager] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PlantManager] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PlantManager] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PlantManager] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PlantManager] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PlantManager] SET  MULTI_USER 
GO
ALTER DATABASE [PlantManager] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PlantManager] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PlantManager] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PlantManager] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PlantManager] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PlantManager] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [PlantManager] SET QUERY_STORE = OFF
GO
USE [PlantManager]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 11/30/2021 4:52:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id_category] [int] IDENTITY(1,1) NOT NULL,
	[name] [nchar](10) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id_category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Plant]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Plant](
	[id_plant] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[last_watered] [date] NOT NULL,
	[image_url] [nvarchar](500) NULL,
	[id_category] [int] NOT NULL,
	[id_user] [int] NOT NULL,
 CONSTRAINT [PK_Plant] PRIMARY KEY CLUSTERED 
(
	[id_plant] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id_user] [int] IDENTITY(1,1) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[active] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id_user] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id_category], [name]) VALUES (1, N'medical   ')
INSERT [dbo].[Category] ([id_category], [name]) VALUES (2, N'tropical  ')
INSERT [dbo].[Category] ([id_category], [name]) VALUES (3, N'decorative')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Plant] ON 

INSERT [dbo].[Plant] ([id_plant], [name], [last_watered], [image_url], [id_category], [id_user]) VALUES (1, N'Spearmint', CAST(N'2021-11-28' AS Date), N'C:\Users\rober\Desktop\Laboratoare\Anul III sem I\Dezvoltarea aplicatiilor mobile\PlantManager\app\src\main\res\drawable\spearmint.jpg', 1, 1)
INSERT [dbo].[Plant] ([id_plant], [name], [last_watered], [image_url], [id_category], [id_user]) VALUES (3, N'Orhideea', CAST(N'2021-11-27' AS Date), NULL, 3, 1)
INSERT [dbo].[Plant] ([id_plant], [name], [last_watered], [image_url], [id_category], [id_user]) VALUES (4, N'Echinacea', CAST(N'2021-11-27' AS Date), NULL, 1, 1)
INSERT [dbo].[Plant] ([id_plant], [name], [last_watered], [image_url], [id_category], [id_user]) VALUES (5, N'Cactus', CAST(N'2021-09-02' AS Date), NULL, 3, 2)
INSERT [dbo].[Plant] ([id_plant], [name], [last_watered], [image_url], [id_category], [id_user]) VALUES (6, N'DragonTree', CAST(N'2021-11-20' AS Date), NULL, 2, 2)
SET IDENTITY_INSERT [dbo].[Plant] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([id_user], [last_name], [first_name], [email], [username], [password], [active]) VALUES (1, N'Luca', N'Roberta', N'roberta.luca@student.unibv.ro', N'roberta.luca', N'roberta', 1)
INSERT [dbo].[User] ([id_user], [last_name], [first_name], [email], [username], [password], [active]) VALUES (2, N'Pamfile', N'Alex', N'alex.pamfile@student.unitbv.ro', N'pamfile.alex', N'alex', 1)
SET IDENTITY_INSERT [dbo].[User] OFF
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_active]  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[Plant]  WITH CHECK ADD  CONSTRAINT [FK_Plant_User] FOREIGN KEY([id_category])
REFERENCES [dbo].[Category] ([id_category])
GO
ALTER TABLE [dbo].[Plant] CHECK CONSTRAINT [FK_Plant_User]
GO
/****** Object:  StoredProcedure [dbo].[spGetPlantsByUserId]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetPlantsByUserId]
	@id_user int
AS
BEGIN
	select *
	from [Plant]
	where [id_user]=@id_user
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertPlant]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertPlant]
	@name varchar(50),
	@last_watered Date,
	@image_url varchar(500),
	@id_category int,
	@id_user int

AS
BEGIN
	insert into [Plant](name, last_watered, image_url, id_category, id_user) 
	values(@name, @last_watered, @image_url, @id_category, @id_user)
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertUser]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertUser]
	@last_name varchar(50),
	@first_name varchar(50),
	@email varchar(50),
	@username varchar(50),
	@password varchar(50)
AS
BEGIN
	insert into [User](last_name, first_name, email, username, password) 
	values(@last_name, @first_name, @email, @username, @password)
END
GO
/****** Object:  StoredProcedure [dbo].[spSelectCategories]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spSelectCategories]
AS
BEGIN
	select *
	from [Category]
END
GO
/****** Object:  StoredProcedure [dbo].[spSelectPlants]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spSelectPlants]
AS
BEGIN
	select *
	from [Plant]
END
GO
/****** Object:  StoredProcedure [dbo].[spSelectUserByUsername]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spSelectUserByUsername]
	@username varchar(50)
AS
BEGIN
	select * 
	from [User]
	where [username] = @username
END
GO
/****** Object:  StoredProcedure [dbo].[spSelectUserByUsernameAndPassword]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spSelectUserByUsernameAndPassword]
	@username varchar(50),
	@password varchar(50)
AS
BEGIN
	select * 
	from [User]
	where [username] = @username and [password] = @password
END
GO
/****** Object:  StoredProcedure [dbo].[spSelectUsers]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spSelectUsers]
AS
BEGIN
	select * 
	from [User]
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateUser]    Script Date: 11/30/2021 4:52:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spUpdateUser]
	@last_name varchar(50),
	@first_name varchar(50),
	@email varchar(50),
	@username varchar(50),
	@password varchar(50),
	@active bit
AS
BEGIN
	update [User] set 
	last_name=@last_name,
	first_name=@first_name,
	email=@email,
	username=@username,
	password=@password,
	active=@active
END
GO
USE [master]
GO
ALTER DATABASE [PlantManager] SET  READ_WRITE 
GO

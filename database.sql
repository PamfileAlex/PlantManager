USE [master]
GO
/****** Object:  Database [PlantManager]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  Table [dbo].[Plant]    Script Date: 12/12/2021 7:52:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Plant](
	[id_plant] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[id_category] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[image] [varbinary](max) NULL,
	[last_watered] [date] NOT NULL,
	[next_water] [date] NOT NULL,
	[time] [time](0) NOT NULL,
	[allow_notifications] [bit] NOT NULL,
 CONSTRAINT [PK_Plant] PRIMARY KEY CLUSTERED 
(
	[id_plant] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 12/12/2021 7:52:49 PM ******/
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

INSERT [dbo].[Category] ([id_category], [name]) VALUES (1, N'none      ')
INSERT [dbo].[Category] ([id_category], [name]) VALUES (2, N'medical   ')
INSERT [dbo].[Category] ([id_category], [name]) VALUES (3, N'tropical  ')
INSERT [dbo].[Category] ([id_category], [name]) VALUES (4, N'decorative')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Plant] ON 

INSERT [dbo].[Plant] ([id_plant], [id_user], [id_category], [name], [image], [last_watered], [next_water], [time], [allow_notifications]) VALUES (7, 1, 1, N'Spearmint', NULL, CAST(N'2021-12-11' AS Date), CAST(N'2021-12-13' AS Date), CAST(N'20:27:00' AS Time), 1)
INSERT [dbo].[Plant] ([id_plant], [id_user], [id_category], [name], [image], [last_watered], [next_water], [time], [allow_notifications]) VALUES (8, 1, 3, N'Orhideea', NULL, CAST(N'2021-12-12' AS Date), CAST(N'2021-12-13' AS Date), CAST(N'13:00:00' AS Time), 1)
INSERT [dbo].[Plant] ([id_plant], [id_user], [id_category], [name], [image], [last_watered], [next_water], [time], [allow_notifications]) VALUES (9, 1, 1, N'Echinaceea', NULL, CAST(N'2021-12-10' AS Date), CAST(N'2021-12-13' AS Date), CAST(N'13:30:00' AS Time), 1)
SET IDENTITY_INSERT [dbo].[Plant] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([id_user], [last_name], [first_name], [email], [username], [password], [active]) VALUES (1, N'Luca', N'Roberta', N'roberta.luca@student.unibv.ro', N'roberta.luca', N'roberta', 1)
INSERT [dbo].[User] ([id_user], [last_name], [first_name], [email], [username], [password], [active]) VALUES (2, N'Pamfile', N'Alex', N'alex.pamfile@student.unibv.ro', N'pamfile.alex', N'alex', 1)
SET IDENTITY_INSERT [dbo].[User] OFF
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_active]  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[Plant]  WITH CHECK ADD  CONSTRAINT [FK_Plant_User] FOREIGN KEY([id_category])
REFERENCES [dbo].[Category] ([id_category])
GO
ALTER TABLE [dbo].[Plant] CHECK CONSTRAINT [FK_Plant_User]
GO
/****** Object:  StoredProcedure [dbo].[spGetPlantsByUserId]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spInsertPlant]    Script Date: 12/12/2021 7:52:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertPlant]
	@id_user int,
	@id_category int,
	@name varchar(50),
	@image varbinary(MAX),
	@last_watered Date,
	@next_water Date,
	@time Time,
	@allow_notifications bit
AS
BEGIN
	insert into [Plant](id_user, id_category, name, image, last_watered, next_water, time, allow_notifications) 
	values(@id_user, @id_category, @name, @image, @last_watered, @next_water, @time, @allow_notifications)
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertUser]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spSelectCategories]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spSelectPlants]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spSelectUserByUsername]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spSelectUserByUsernameAndPassword]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spSelectUsers]    Script Date: 12/12/2021 7:52:49 PM ******/
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
/****** Object:  StoredProcedure [dbo].[spUpdatePlantDates]    Script Date: 12/12/2021 7:52:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spUpdatePlantDates]
	@id int, 
	@last_watered Date,
	@next_water Date
AS
BEGIN
	update [Plant] set
	last_watered=@last_watered,
	next_water=@next_water
	where id_plant=@id
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateUser]    Script Date: 12/12/2021 7:52:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spUpdateUser]
	@username varchar(50),
	@last_name varchar(50),
	@first_name varchar(50),
	@email varchar(50)
AS
BEGIN
	update [User] set 
	last_name=@last_name,
	first_name=@first_name,
	email=@email
	where username=@username
END
GO
USE [master]
GO
ALTER DATABASE [PlantManager] SET  READ_WRITE 
GO

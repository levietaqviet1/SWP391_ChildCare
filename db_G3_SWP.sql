
USE [SWP391_Project_Lastest]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[account_id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[gender] [bit] NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[dob] [date] NOT NULL,
	[phone_number] [nvarchar](12) NOT NULL,
	[address] [nvarchar](max) NULL,
	[img] [nvarchar](max) NULL,
	[role_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Activity]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Activity](
	[activity_id] [int] IDENTITY(1,1) NOT NULL,
	[act_description] [text] NOT NULL,
	[act_name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[activity_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Attendance]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attendance](
	[student_id] [int] NOT NULL,
	[check_date] [date] NOT NULL,
	[status] [int] NOT NULL,
	[teacher_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[student_id] ASC,
	[check_date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Class]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[class_id] [int] IDENTITY(1,1) NOT NULL,
	[class_name] [nvarchar](20) NOT NULL,
	[grade] [int] NOT NULL,
	[class_description] [text] NOT NULL,
	[teacher_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[class_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Criteria]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Criteria](
	[criteria_id] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[criteria_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[feedback_id] [int] IDENTITY(1,1) NOT NULL,
	[kid_id] [int] NULL,
	[teacher_id] [int] NULL,
	[fb_content] [nvarchar](max) NULL,
	[rating] [float] NULL,
	[fb_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[feedback_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kindergartner]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kindergartner](
	[kinder_id] [int] IDENTITY(1,1) NOT NULL,
	[parent_id] [int] NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[dob] [date] NOT NULL,
	[gender] [bit] NOT NULL,
	[img] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[kinder_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Record]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Record](
	[record_id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NULL,
	[note] [text] NULL,
	[criteria_id] [int] NULL,
	[semester] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[record_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedule](
	[schedule_id] [int] IDENTITY(1,1) NOT NULL,
	[class_id] [int] NULL,
	[activity_id] [int] NULL,
	[slot_id] [int] NULL,
	[schedule_date] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slot]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slot](
	[slot_id] [int] IDENTITY(1,1) NOT NULL,
	[start_hour] [float] NOT NULL,
	[end_hour] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[slot_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Study_Record]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Study_Record](
	[study_record_id] [int] IDENTITY(1,1) NOT NULL,
	[class_id] [int] NULL,
	[kinder_id] [int] NULL,
	[study_year] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[study_record_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher_achivement]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher_achivement](
	[achivement_id] [int] IDENTITY(1,1) NOT NULL,
	[content] [text] NOT NULL,
	[teacher_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[achivement_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher_record]    Script Date: 12/09/2022 11:52:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher_record](
	[record_id] [int] IDENTITY(1,1) NOT NULL,
	[teacher_id] [int] NULL,
	[description] [text] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[record_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (1, N'Admin', N'Admin', 1, N'admin@gmail.com', N'1234', CAST(N'1980-12-07' AS Date), N'0387458346', N'New York', NULL, 1)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (2, N'Peter', N'Crouch', 1, N'peter@gmail.com', N'1234', CAST(N'1980-12-07' AS Date), N'0387458346', N'New York', N'https://www.transfermarkt.com/peter-crouch/profil/spieler/4072', 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (3, N'Maria', N'Gonzales', 0, N'maria@gmail.com', N'1234', CAST(N'1978-04-23' AS Date), N'0912345678', N'St. Saint German', N'https://cdn1.sportngin.com/attachments/photo/4194-168917380/IMG_5401.jpg', 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (4, N'James', N'Bonds', 1, N'james@gmail.com', N'1234', CAST(N'1988-01-13' AS Date), N'08563490812', N'Brucia Dortmun', N'https://ichef.bbci.co.uk/news/640/cpsprodpb/A37B/production/_121215814_craigepa.jpg', 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (5, N'Cristiano', N'Ronaldo', 1, N'ronaldo@gmail.com', N'1234', CAST(N'1990-09-12' AS Date), N'0387458346', N'New York', N'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Cristiano_Ronaldo_2018.jpg/200px-Cristiano_Ronaldo_2018.jpg', 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (6, N'Lionel', N'Messi', 1, N'messi@gmail.com', N'1234', CAST(N'1991-05-25' AS Date), N'0387458346', N'New York', N'https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg', 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (7, N'Jeff', N'John', 1, N'jeff@gmail.com', N'1234', CAST(N'1889-12-12' AS Date), N'0387458346', N'Brokelyn', N'https://d2favcmz4lf91v.cloudfront.net/wp-content/uploads/2016/04/jeff_john.jpg', 3)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (8, N'Jonah', N'Hex', 1, N'jonah@gmail.com', N'1234', CAST(N'1890-01-14' AS Date), N'0123456789', N'Russia', N'https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Josh_Brolin_Berlin_2016.jpg/250px-Josh_Brolin_Berlin_2016.jpg', 3)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (9, N'Macey', N'Albert', 0, N'macey@gmail.com', N'1234', CAST(N'1985-11-02' AS Date), N'0813601669', N'Canada', NULL, 3)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (10, N'Clark', N'Kent', 1, N'clark@gmail.com', N'1234', CAST(N'1985-11-11' AS Date), N'0813701669', N'New York', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (11, N'Lewis', N'Lennor', 1, N'lewis@gmail.com', N'1234', CAST(N'1977-10-10' AS Date), N'0883701669', N'New York', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (12, N'Catherine', N'Parr', 0, N'cath@gmail.com', N'1234', CAST(N'1980-09-09' AS Date), N'0893701669', N'London', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (13, N'Asiyah', N'McGee', 0, N'asiyah@gmail.com', N'1234', CAST(N'1985-11-11' AS Date), N'0813701668', N'New Jersey', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (14, N'Sulayman', N'Shannon', 0, N'sulayman@gmail.com', N'1234', CAST(N'1985-01-02' AS Date), N'0813703668', N'New Jersey', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (15, N'Fearne', N'Sykes', 1, N'fearne@gmail.com', N'1234', CAST(N'1985-01-01' AS Date), N'0813711668', N'California', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (16, N'Charlotte', N'McGregor', 0, N'charlotte@gmail.com', N'1234', CAST(N'1985-01-03' AS Date), N'0813702668', N'Brooklyn', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (17, N'Josiah', N'Cervantes', 1, N'josiah@gmail.com', N'1234', CAST(N'1985-01-04' AS Date), N'0913701668', N'Washington DC', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (18, N'Gethin', N'Lowery', 1, N'gethin@gmail.com', N'1234', CAST(N'1985-01-05' AS Date), N'0814701668', N'Beijing', NULL, 2)
INSERT [dbo].[Account] ([account_id], [first_name], [last_name], [gender], [email], [password], [dob], [phone_number], [address], [img], [role_id]) VALUES (19, N'Lana', N'Richards', 0, N'lana@gmail.com', N'1234', CAST(N'1985-01-06' AS Date), N'0813701768', N'Alabama', NULL, 2)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Activity] ON 

INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (1, N'Have breakfast', N'Breakfast')
INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (2, N'Do workout', N'Physical Education')
INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (3, N'Study', N'Study')
INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (4, N'Lunch', N'Lunch')
INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (5, N'Drawing', N'Drawing')
INSERT [dbo].[Activity] ([activity_id], [act_description], [act_name]) VALUES (6, N'Singing', N'Singing')
SET IDENTITY_INSERT [dbo].[Activity] OFF
GO
SET IDENTITY_INSERT [dbo].[Class] ON 

INSERT [dbo].[Class] ([class_id], [class_name], [grade], [class_description], [teacher_id]) VALUES (1, N'SE1608', 2, N'It was great', 7)
INSERT [dbo].[Class] ([class_id], [class_name], [grade], [class_description], [teacher_id]) VALUES (2, N'SE1609', 3, N'It was great', 8)
INSERT [dbo].[Class] ([class_id], [class_name], [grade], [class_description], [teacher_id]) VALUES (3, N'SE1610', 3, N'It was great', 9)
SET IDENTITY_INSERT [dbo].[Class] OFF
GO
SET IDENTITY_INSERT [dbo].[Kindergartner] ON 

INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (1, 1, N'Peter', N'Senior', CAST(N'2020-04-12' AS Date), 1, N'https://kidsactivitiesblog--o--com.follycdn.com/wp-content/uploads/2020/11/Activities-for-Kindergarten-best-things-for-Kindergartners-to-do-from-Kids-Activities-Blog-1024x1024.jpg')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (2, 2, N'Jimmy', N'Olsen', CAST(N'2020-01-13' AS Date), 1, N'https://media.istockphoto.com/photos/exited-kindergartner-on-his-first-day-of-school-picture-id607493732')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (3, 3, N'James', N'Bonds,Jr', CAST(N'2020-05-24' AS Date), 1, N'https://assets.babycenter.com/ims/2015/04/187185569_4x3.jpg')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (4, 3, N'James', N'Oliver', CAST(N'2020-02-15' AS Date), 1, N'https://c.stocksy.com/a/Wiq400/z9/1155960.jpg')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (5, 4, N'Cristiano', N'Ronaldo, Jr', CAST(N'2020-12-08' AS Date), 1, N'https://www.parentmap.com/sites/default/files/styles/1180x660_scaled_cropped/public/2020-08/iStock-1204260068.jpg?itok=XtyKtZ9T')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (6, 5, N'Thiago', N'Messi', CAST(N'2002-04-12' AS Date), 1, N'https://resources.finalsite.net/images/f_auto,q_auto,t_image_size_2/v1614275984/catawbaschoolsnet/wvmaws8mqbbjqs9zwfav/kindergarten-boy-hands-paint.jpg')
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (7, 6, N'Jon', N'Kent', CAST(N'2020-06-06' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (8, 6, N'Connor', N'Kent', CAST(N'2020-07-07' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (9, 7, N'Jasmin', N'Lennor', CAST(N'2020-05-05' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (10, 7, N'Shane', N'Lennor', CAST(N'2020-06-05' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (11, 8, N'Landon', N'Parr', CAST(N'2020-05-06' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (12, 8, N'Bruce', N'Parr', CAST(N'2020-07-03' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (13, 9, N'Jonathan', N'McGee', CAST(N'2020-01-01' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (14, 9, N'Alyx', N'McGee', CAST(N'2020-01-02' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (15, 9, N'Sana', N'McGee', CAST(N'2020-01-03' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (16, 10, N'Farah', N'Shannon', CAST(N'2020-01-04' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (17, 10, N'Skye', N'Shannon', CAST(N'2020-01-05' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (18, 10, N'Cally', N'Shannon', CAST(N'2020-01-06' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (19, 11, N'Michele', N'Sykes', CAST(N'2020-01-07' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (20, 11, N'Parker', N'Sykes', CAST(N'2020-01-08' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (21, 12, N'Nour', N'McGregor', CAST(N'2020-01-09' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (22, 12, N'Felix', N'McGregor', CAST(N'2020-01-10' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (23, 13, N'Imaan', N'Cervantes', CAST(N'2020-01-11' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (24, 13, N'Ada', N'Cervantes', CAST(N'2020-01-12' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (25, 13, N'Kayden', N'Cervantes', CAST(N'2020-02-01' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (26, 14, N'Corey', N'Lowery', CAST(N'2020-02-02' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (27, 14, N'Tanvir', N'Lowery', CAST(N'2020-02-03' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (28, 15, N'Ceri', N'Lowery', CAST(N'2020-02-04' AS Date), 0, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (29, 15, N'Avi', N'Richards', CAST(N'2020-02-05' AS Date), 1, NULL)
INSERT [dbo].[Kindergartner] ([kinder_id], [parent_id], [first_name], [last_name], [dob], [gender], [img]) VALUES (30, 15, N'Diya', N'Richards', CAST(N'2020-02-06' AS Date), 1, NULL)
SET IDENTITY_INSERT [dbo].[Kindergartner] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (2, N'parent')
INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (3, N'teacher')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Schedule] ON 

INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (1, 1, 1, 1, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (2, 1, 2, 2, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (3, 1, 3, 3, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (4, 1, 4, 4, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (5, 1, 4, 5, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (6, 1, 5, 6, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (7, 1, 6, 7, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (8, 1, 1, 1, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (9, 1, 2, 2, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (10, 1, 3, 3, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (11, 1, 4, 4, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (12, 1, 4, 5, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (13, 1, 5, 6, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (14, 1, 6, 7, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (15, 1, 1, 1, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (16, 1, 2, 2, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (17, 1, 3, 3, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (18, 1, 4, 4, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (19, 1, 4, 5, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (20, 1, 5, 6, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (21, 1, 6, 7, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (22, 1, 1, 1, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (23, 1, 2, 2, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (24, 1, 3, 3, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (25, 1, 4, 4, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (26, 1, 4, 5, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (27, 1, 5, 6, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (28, 1, 6, 7, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (29, 1, 1, 1, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (30, 1, 2, 2, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (31, 1, 3, 3, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (32, 1, 4, 4, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (33, 1, 4, 5, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (34, 1, 5, 6, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (35, 1, 6, 7, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (36, 2, 1, 1, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (37, 2, 2, 2, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (38, 2, 3, 3, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (39, 2, 4, 4, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (40, 2, 4, 5, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (41, 2, 5, 6, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (42, 2, 6, 7, CAST(N'2022-06-22' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (43, 2, 1, 1, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (44, 2, 2, 2, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (45, 2, 3, 3, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (46, 2, 4, 4, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (47, 2, 4, 5, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (48, 2, 5, 6, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (49, 2, 6, 7, CAST(N'2022-06-23' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (50, 2, 1, 1, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (51, 2, 2, 2, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (52, 2, 3, 3, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (53, 2, 4, 4, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (54, 2, 4, 5, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (55, 2, 5, 6, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (56, 2, 6, 7, CAST(N'2022-06-24' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (57, 2, 1, 1, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (58, 2, 2, 2, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (59, 2, 3, 3, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (60, 2, 4, 4, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (61, 2, 4, 5, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (62, 2, 5, 6, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (63, 2, 6, 7, CAST(N'2022-06-25' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (64, 2, 1, 1, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (65, 2, 2, 2, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (66, 2, 3, 3, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (67, 2, 4, 4, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (68, 2, 4, 5, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (69, 2, 5, 6, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (70, 2, 6, 7, CAST(N'2022-06-26' AS Date))
INSERT [dbo].[Schedule] ([schedule_id], [class_id], [activity_id], [slot_id], [schedule_date]) VALUES (71, 1, 1, 1, CAST(N'2022-09-12' AS Date))
SET IDENTITY_INSERT [dbo].[Schedule] OFF
GO
SET IDENTITY_INSERT [dbo].[Slot] ON 

INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (1, 7, 8)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (2, 8, 9)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (3, 9, 10)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (4, 10, 11)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (5, 11, 12)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (6, 12, 13)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (7, 13, 14)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (8, 14, 15)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (9, 15, 16)
INSERT [dbo].[Slot] ([slot_id], [start_hour], [end_hour]) VALUES (10, 16, 17)
SET IDENTITY_INSERT [dbo].[Slot] OFF
GO
SET IDENTITY_INSERT [dbo].[Study_Record] ON 

INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (1, 1, 1, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (2, 1, 2, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (3, 1, 3, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (4, 1, 4, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (5, 1, 5, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (6, 1, 6, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (7, 1, 7, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (8, 1, 8, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (9, 1, 9, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (10, 1, 10, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (11, 2, 11, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (12, 2, 12, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (13, 2, 13, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (14, 2, 13, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (15, 2, 14, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (16, 2, 15, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (17, 2, 16, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (18, 2, 17, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (19, 2, 18, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (20, 2, 19, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (21, 2, 20, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (22, 3, 21, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (23, 3, 22, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (24, 3, 23, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (25, 3, 24, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (26, 3, 25, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (27, 3, 26, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (28, 3, 27, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (29, 3, 28, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (30, 3, 29, 2022)
INSERT [dbo].[Study_Record] ([study_record_id], [class_id], [kinder_id], [study_year]) VALUES (31, 3, 30, 2022)
SET IDENTITY_INSERT [dbo].[Study_Record] OFF
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Kindergartner] ([kinder_id])
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([teacher_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD FOREIGN KEY([teacher_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([kid_id])
REFERENCES [dbo].[Kindergartner] ([kinder_id])
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([teacher_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Kindergartner]  WITH CHECK ADD FOREIGN KEY([parent_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Record]  WITH CHECK ADD FOREIGN KEY([criteria_id])
REFERENCES [dbo].[Criteria] ([criteria_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Record]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Kindergartner] ([kinder_id])
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD FOREIGN KEY([activity_id])
REFERENCES [dbo].[Activity] ([activity_id])
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD FOREIGN KEY([class_id])
REFERENCES [dbo].[Class] ([class_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD FOREIGN KEY([slot_id])
REFERENCES [dbo].[Slot] ([slot_id])
GO
ALTER TABLE [dbo].[Study_Record]  WITH CHECK ADD FOREIGN KEY([class_id])
REFERENCES [dbo].[Class] ([class_id])
GO
ALTER TABLE [dbo].[Study_Record]  WITH CHECK ADD FOREIGN KEY([kinder_id])
REFERENCES [dbo].[Kindergartner] ([kinder_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teacher_achivement]  WITH CHECK ADD FOREIGN KEY([teacher_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Teacher_record]  WITH CHECK ADD FOREIGN KEY([teacher_id])
REFERENCES [dbo].[Account] ([account_id])
ON DELETE CASCADE
GO

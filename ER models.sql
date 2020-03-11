create table Team(
teamID integer primary key auto_increment,
teamName text,
ranking integer);


create table Sponser(
sponserID integer primary key auto_increment,
abbre text,
SponserName text,
Sponsertype integer);

alter table Sponser modify Sponsertype text;

CREATE TABLE IF NOT EXISTS `Teams_Sponsers` (
  teamID integer,
  foreign key(teamID)  references Team(teamID)
  on delete no action 
  on update no action, 
  sponserID integer,
  foreign key(sponserID)  references Sponser(sponserID)
  on delete no action 
  on update no action) ;
  
  alter table Teams_Sponsers add column donate_amount INT;
  
  CREATE TABLE IF NOT EXISTS Student (
  memeberID integer primary key auto_increment,
  StuName text,
  birthday DATE,
  gender boolean,
  teamID integer,
  foreign key(teamID)  references Team(teamID)
  on delete no action 
  on update no action);


  CREATE TABLE IF NOT EXISTS SoftwareDev (
  software_devID integer primary key auto_increment,
  memeberID integer,
  foreign key(memeberID)  references Student(memeberID)
  on delete no action 
  on update no action);
  
  create table if not exists ProgramLan(
  prog_id integer primary key auto_increment,
  software_devID integer,
  foreign key(software_devID)  references SoftwareDev(software_devID)
  on delete no action 
  on update no action);
  use Company;
  alter table ProgramLan add column languageName text;
   
  CREATE TABLE IF NOT EXISTS SoftwareTest (
  software_testID integer primary key auto_increment,
   test_tool text,
    memeberID integer,
    foreign key(memeberID)  references Student(memeberID)
    on delete no action 
    on update no action);
   

CREATE TABLE IF NOT EXISTS SoftwareExport (
  software_exportID integer primary key auto_increment,
  dbms text,
  memeberID integer,
 foreign key(memeberID)  references Student(memeberID)
 on delete no action 
 on update no action);

drop table Country; 
create table Country(
budget integer,
countryName char(10) primary key,
countryranking integer);

--  https://dba.stackexchange.com/questions/34040/many-to-many-and-weak-entities
-- https://stackoverflow.com/questions/10292355/how-to-create-a-real-one-to-one-relationship-in-sql-server
-- OneToOne  league and team

drop table Leagues;
CREATE TABLE Leagues
( countryName char(10),
  leagueName char(10),
   primary key (countryName, leagueName),
   FOREIGN KEY (countryName)
   REFERENCES Country (countryName)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   teamID INT NOT NULL,
    CONSTRAINT fk_League_team
    FOREIGN KEY (teamID)
    REFERENCES  Team(teamID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    insert into Team values(1,"BIL_Footballests",1);
    insert into Team values(2,"CEV_Footballests",3);
    insert into Team values(3,"IBF_Footballests",2);
    insert into Team values(4,"Bishkek lions",5);
    insert into Team values(5,"Djal Boys",4);
    
    
    insert into Sponser values(1,"ACM","Namatullah","Media Sponsors");
    insert into Sponser values(2,"ACM","Askar","Prize Sponsors");
    insert into Sponser values(3,"IEEE","Islam","Financial Sponsors");
    insert into Sponser values(4,"ACM","Roza","Prize Sponsors");
    insert into Sponser values(5,"IEEE","Koldosh","Media Sponsors");
    
     insert into Teams_Sponsers (teamID,sponserID,donate_amount) Values(
    (select teamID from Team where teamID=1),
    (select sponserID from Sponser where sponserID=2),5000); 
    
   insert into Teams_Sponsers (teamID,sponserID,donate_amount) Values(
    (select teamID from Team where teamID=2),
    (select sponserID from Sponser where sponserID=2),5000); 
    
     insert into Teams_Sponsers (teamID,sponserID,donate_amount) Values(
    (select teamID from Team where teamID=3),
    (select sponserID from Sponser where sponserID=5),10000); 
    
     insert into Teams_Sponsers (teamID,sponserID,donate_amount) Values(
    (select teamID from Team where teamID=4),
    (select sponserID from Sponser where sponserID=2),15000); 
    
     insert into Teams_Sponsers (teamID,sponserID,donate_amount) Values(
    (select teamID from Team where teamID=5),
    (select sponserID from Sponser where sponserID=3),160000); 
    

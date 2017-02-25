-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: 127.0.0.1    Database: loganalyser
-- ------------------------------------------------------
-- Server version	5.7.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `LogFiles`
--

DROP TABLE IF EXISTS `LogFiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LogFiles` (
  `Logfile` varchar(50) NOT NULL,
  `LogfileDate` date DEFAULT NULL,
  PRIMARY KEY (`Logfile`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LogFiles`
--

LOCK TABLES `LogFiles` WRITE;
/*!40000 ALTER TABLE `LogFiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `LogFiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `School`
--

DROP TABLE IF EXISTS `Schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Schools` (
  `IPAddress` varchar(15) NOT NULL,
  `Site` varchar(75) DEFAULT NULL,
  `Street` varchar(50) DEFAULT NULL,
  `Zip` varchar(15) DEFAULT NULL,
  `City` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IPAddress`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `School`
--

LOCK TABLES `Schools` WRITE;
/*!40000 ALTER TABLE `Schools` DISABLE KEYS */;
INSERT INTO `Schools` VALUES ('10.1.c.d','CVO Brussel','Materiaalstraat 67','1070','Anderlecht'),('10.100.c.d','CVO Elishout COOVI','Emile Grysonlaan 1','1070','Anderlecht'),('10.101.c.d','CVO PROVINCIE Antwerpen','Meistraat 5','2000','Antwerpen'),('10.104.c.d','Centrum voor Volwassenonderwijs Deurne','Frank Craeybecklaan 22','2100','Deurne'),('10.108.c.d','Centrum voor Volwassenonderwijs DTL','Kerkstraat 38','2200','Herentals'),('10.113.c.d','Centrum voor Volwassenonderwijs Sint-Jozef','Kleinhoefstraat','2440','Geel'),('10.115.c.d','Stedelijk Centrum voor Volwassenonderwijs SIVO','Grotesteenweg 2','2600','Berchem'),('10.117.c.d','Stedelijk Centrum voor Volwassenonderwijs Nijverheid','Grotesteenweg 2','2600','Berchem'),('10.120.c.d','CVO Leerstad','Brouwerijstraat 5','9160','Lokeren'),('10.122.c.d','Centrum voor Volwassenonderwijs Antwerpen-Zuid','Distelvinkslaan 22','2660','Hoboken'),('10.123.c.d','CVO CRESCENDO','Vaartdijk 86','2800','Mechelen'),('10.124.c.d','CVO Tweedekansonderwijs Mechelen','Onze-Lieve-Vrouwstraat 94','2800','Mechelen'),('10.125.c.d','CVO Rivierenland','Anseelstraat 283','2830','Willebroek'),('10.128.c.d','Centrum voor Volwassenonderwijs Leuven/Landen','Redingenstraat 90','3000','Leuven'),('10.129.c.d','Centrum voor Volwassenonderwijs Hageland','Schaluin 28','3200','Aarschot'),('10.130.c.d','Centrum voor Volwassenonderwijs De Oranjerie','Boudewijnvest 3','3290','Diest'),('10.131.c.d','Centrum voor Volwassenonderwijs TIKB','Pastorijstraat 40','3530','Houthalen-Helchteren'),('10.132.c.d','Centrum voor Volwassenonderwijs LBC-NVK','Paalsesteenweg 33','3530','Beringen'),('10.133.c.d','Campus de helix','Rijksweg 357','3630','Maasmechelen'),('10.134.c.d','KCST Centrum voor Volwassenonderwijs','Plankstraat 11','3800','Sint-Truiden'),('10.135.c.d','Centrum voor Volwassenonderwijs LINO','Mudakkers 25','3920','Lommel'),('10.136.c.d','CVO De Avondschool','Leopold III laan 1','8400','Oostende'),('10.140.c.d','Centrum voor Volwassenonderwijs HITEK','Doorniksesteenweg 145','8500','Kortrijk'),('10.142.c.d','CVO Roeselare','Arme-Klarenstraat 40','8800','Roeselare'),('10.151.c.d','Centrum voor Volwassenonderwijs ISBO','Assenedesteenweg 163','9060','Zelzate'),('10.162.c.d','Centrum voor Volwassenonderwijs Leerdorp','Martelaarslaan 65','9000','Gent'),('10.163.c.d','Centrum voor Volwassenonderwijs KISP','Industrieweg 22','9030','Mariakerke'),('10.164.c.d','Vrij Technisch Centrum voor Volwassenonderwijs','Vakschoolstraat 41','9300','Aalst'),('10.165.c.d','Centrum voor Volwassenonderwijs VTI Brugge','Boeverleistraat 73','8000','Brugge'),('10.170.c.d','Het Perspectief PC voor Volwassenonderwijs','Henleykaai 83','9000','Gent'),('10.172.c.d','Centrum voor Volwassenonderwijs de AvondSchool','Schoonmeerstraat 26','9000','Gent'),('10.174.c.d','Centrum voor Volwassenonderwijs','Arsenaalstraat 4','8000','Brugge'),('10.175.c.d','Provinciaal Centrum Volwassenonderwijs Waas en Durme','Durmelaan 34_A','9160','Lokeren'),('10.182.c.d','Provinciaal Centrum Volwassenonderwijs MeetjesLand','Roze 131','9900','Eeklo'),('10.190.c.d','Centrum voor Volwassenonderwijs De Vlaamse Ardennen','Fortstraat 47','9700','Oudenaarde'),('10.193.c.d','Centrum voor Volwassenonderwijs Technicum','Londenstraat 43','2000','Antwerpen'),('10.195.c.d','HORITO Centrum voor Volwassenonderwijs','de Merodelei 22','2300','Turnhout'),('10.202.c.d','Centrum voor Volwassenonderwijs St-Ludgardis','Gasthuistraat 3','2400','Mol'),('10.51.c.d','Centrum voor Volwassenonderwijs VTI Lier','Kruisbogenhofstraat 7','2500','Lier'),('10.80.c.d','Centrum voor Volwassenonderwijs Kempen','Leenhofstraat 3','2400','Mol'),('10.110.c.d','UNKNOWN','','','');
/*!40000 ALTER TABLE `Schools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sessions`
--

DROP TABLE IF EXISTS `Sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sessions` (
  `SessionID` varchar(50) NOT NULL,
  `LogFile` varchar(50) NOT NULL,
  `IPAddress` varchar(15) DEFAULT NULL,
  `SessionTime` time DEFAULT NULL,
  `TotalTime` int(20) DEFAULT NULL,
  `TransferredBytes` int(11) DEFAULT NULL,
  `NumberOfRequests` int(11) DEFAULT NULL,
  `UserID` varchar(3) DEFAULT NULL,
  `SiteID` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`SessionID`),
  KEY `SiteID` (`SiteID`),
  KEY `Logfile` (`LogFile`),
  KEY `UserID` (`UserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sessions`
--

LOCK TABLES `Sessions` WRITE;
/*!40000 ALTER TABLE `Sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteApplications`
--

DROP TABLE IF EXISTS `SiteApplications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteApplications` (
  `ApplicationID` int(30) NOT NULL AUTO_INCREMENT,
  `Application` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ApplicationID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteApplications`
--

LOCK TABLES `SiteApplications` WRITE;
/*!40000 ALTER TABLE `SiteApplications` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteApplications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sites`
--

DROP TABLE IF EXISTS `Sites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sites` (
  `SiteId` int(11) NOT NULL AUTO_INCREMENT,
  `Site` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SiteId`)
) ENGINE=MyISAM AUTO_INCREMENT=3203 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sites`
--

LOCK TABLES `Sites` WRITE;
/*!40000 ALTER TABLE `Sites` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `UserID` varchar(3) NOT NULL,
  `Name` varchar(15) DEFAULT NULL,
  `FirstName` varchar(15) DEFAULT NULL,
  `Cat` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES ('AIP','Van Tilt','Jean','Student'),('AKC','Detieren','Filip','Student'),('ALY','Poisson','Tim','Student'),('APJ','Nassens','Koen','Student'),('APP','Van Baas','Jean-Pierre','Student'),('AVZ','Kierbeek','Tom','Lector'),('AWB','Kuipers','Romain','Student'),('AYT','Laloi','Patrick','Student'),('AZZ','Delpierre','Daniel','Lector'),('BAT','Sarden','Maurien','Student'),('BPD','Fleron','Michel','Lector'),('BSR','Palten','Michel','Student'),('BTM','Buron','Benny','Lector'),('BZP','Shmidt','Koen','Student'),('CAG','Manchester','Fabrice','Student'),('CBS','Demarseille','Marc','Student'),('CCY','Nassau','Alfred','Student'),('CHN','Doust','Jan','Student'),('CHV','Faillard','Romain','Student'),('CJT','Falat','Thomas','Student'),('CKO','Dassault','Marc','Student'),('CLL','Marsin','Benny','Student'),('CLY','Noort','Patrick','Lector'),('CRY','Zarly','Tim','Student'),('CVE','Jorpe','Daniel','Lector'),('DBG','Denice','Hubert','Student'),('DJE','Vanmoorten','Piet','Student'),('DMF','Jefron','Christophe','Student'),('DQD','Dulierre','Thierry','Student'),('DQG','Allibert','Philippe','Lector'),('DRD','Delriviere','Tom','Student'),('DRT','Bonfond','Jaques','Student'),('DVO','Autier','Marc','Student'),('EGL','Detier','Marie','Student'),('EJO','Manoir','Paul','Student'),('EKT','Leloux','Patrick','Student'),('ELR','De Gaule','Pascal','Student'),('EMA','Van Biel','Axl','Student'),('EME','Klaas','Gerard','Student'),('EOV','Dubois','Jean','Student'),('EZB','Novembre','Albert','Student'),('FBI','Deliege','Jaques','Student'),('FCA','Fluteau','Eddy','Lector'),('GGW','Flink','Bert','Lector'),('GJN','Hallet','Albert','Student'),('GMD','Berg','Bruno','Lector'),('GMN','Doost','Daniel','Lector'),('GNQ','Altier','Michel','Lector'),('GRT','Colruyt','Geert','Student'),('GUY','Dedinant','Marie','Student'),('GWR','Sombreffe','Hubert','Student'),('GXD','Fiberglass','Piet','Student'),('HDO','Raes','Willy','Student'),('HEL','Vandebaan','Eddy','Student'),('HJN','Hotel','Jean','Student'),('HKJ','Faillard','Bruno','Lector'),('HKT','Ionaas','Thierry','Student'),('HKW','Toren','Dirk','Student'),('HKZ','Pasden','Jo','Student'),('HSY','Klaes','Maurien','Student'),('HWA','Kaas','Alfred','Student'),('HXI','Nieperdeel','Kurt','Student'),('ICA','Maes','Albert','Student'),('IFM','Demarche','Hugues','Student'),('IIF','Faille','Marc','Lector'),('ILB','Luypaart','Geert','Student'),('INQ','Herbiet','Kurt','Student'),('IPX','Destree','Kurt','Student'),('IQD','Dufour','Serge','Student'),('IUY','Pierson','Piet','Student'),('IYW','Jambes','Jules','Student'),('JFV','Van Den Brempt','Hubert','Student'),('JKB','Fige','Franck','Lector'),('JKO','Dujour','Tim','Student'),('JMS','Duhoux','Jules','Student'),('JON','Mees','Patrick','Lector'),('JOZ','Goldie','Jan','Student'),('JSP','Vanlierde','Tim','Student'),('KAJ','Stalport','Pierre','Student'),('KBA','Libermont','Jean','Student'),('KFU','Desmet','Jo','Student'),('KLP','Partis','Christophe','Student'),('KPP','Vandenbeek','Geert','Student'),('KVT','Deduine','Geert','Student'),('KWY','Furnes','Pierre','Lector'),('LFB','Iverlek','Ludo','Student'),('LFE','Paris','Hubert','Student'),('LFF','Vandamme','Jaques','Student'),('LGK','Kluis','Gerard','Student'),('LHS','Guige','Marie','Student'),('LMU','Laurent','Pierre','Student'),('LNM','Delarue','Richard','Student'),('LPV','De Maeren','Julien','Student'),('LRS','Gerald','Paul','Student'),('LVH','Diepre','Daniel','Student'),('LWL','Rives','Patrick','Student'),('LWZ','Talent','Michel','Student'),('MAX','Demontoit','Jean','Student'),('MCG','Detroit','Thierry','Student'),('MGV','Carrefour','Willy','Student'),('MMX','Luaux','Nicolas','Student'),('MNO','Kempen','Bob','Student'),('MPI','Humbert','Pierre','Lector'),('MPT','Maren','Marc','Student'),('MTF','Sabben','John','Student'),('MVT','Deltour','Thomas','Student'),('MZK','Deportere','Serge','Student'),('NAV','Vandromme','Piet','Student'),('NBC','Lafaille','Ludo','Student'),('NCS','Pommier','Willy','Student'),('NCT','Dormal','Hugues','Student'),('NCZ','Nieuwpoort','Bert','Student'),('NFO','Orta','Hubert','Student'),('NNQ','Barlot','Thierry','Student'),('NOC','Vermeulen','Kurt','Lector'),('NQG','Gorin','Serge','Student'),('NUQ','Dast','Albert','Student'),('NXP','Dasilva','Pierre','Student'),('NXX','Vliegen','Thierry','Student'),('NYP','Koorten','Julien','Student'),('OEC','Monjoie','Hugues','Student'),('OHS','Op Den Veld','Piet','Student'),('OKY','Dutille','Richard','Student'),('OXC','Chart','Benny','Lector'),('OYK','Seste','Maurien','Student'),('PCA','De Smedt','Jean','Student'),('PCX','Lematin','Fabrice','Student'),('PGR','Delcourt','Jean-Pierre','Lector'),('PHD','Liverpool','Gilles','Student'),('PHL','Capron','Daniel','Student'),('PII','Lesoir','Jef','Student'),('PLP','Jongen','Marc','Student'),('PLX','Poutrelle','Bruno','Lector'),('PWV','Erable','Daniel','Lector'),('QGU','Vanbrugge','Jean','Student'),('QNI','Olivier','Maurien','Student'),('QQY','Masen','Thierry','Student'),('QQZ','Louvrois','Philippe','Student'),('QZR','Despa','Elio','Student'),('RAY','Detheux','Gilles','Student'),('RCZ','Garou','Geert','Student'),('RDG','Lapeur','Jean','Student'),('RFL','Nollert','Michel','Student'),('RFM','Lajoie','Jean','Student'),('RFX','Dedijn','Romain','Student'),('RGE','Romeo','Axl','Lector'),('RIC','Duchateau','Marc','Lector'),('RLF','Gruaux','Kurt','Student'),('RUD','Ducharme','Filip','Student'),('RXA','Daix','Thomas','Lector'),('SAW','Delpierre','Tom','Lector'),('SHU','Mineur','Jean-Pierre','Student'),('SID','Claes','Michel','Lector'),('SJW','Dubart','Bruno','Lector'),('SLM','Klimmen','Patrick','Student'),('SSG','Poignard','Marc','Student'),('SWY','Libert','Marc','Lector'),('TJX','Lieren','Filip','Student'),('TKH','Vanlier','Jef','Student'),('TMT','Demeulder','Filip','Student'),('TQI','Halmaar','Herbert','Student'),('TTH','Duhamel','Richard','Student'),('TTK','Paarden','Albert','Student'),('TUJ','Pieperzeel','Filip','Student'),('TUV','Deraam','Kurt','Lector'),('ULZ','Defriet','Thomas','Lector'),('UNX','Gerfon','Hubert','Student'),('UOF','Nalouille','Daniel','Student'),('URH','Van Moorleghem','Paul','Student'),('VAF','Lapin','Daniel','Student'),('VCJ','Vandag','Jan','Student'),('VDO','Tummers','Jaques','Student'),('VEB','Hendrickx','Gerard','Student'),('VJT','Erto','Elio','Student'),('VLN','Vanbroek','Willy','Student'),('VRM','Meunier','Kurt','Lector'),('VTL','Piot','Bob','Student'),('VTT','Biennomm?à','Jules','Student'),('WAK','Masson','Tom','Student'),('WKB','Sieren','Albert','Student'),('WMY','Koen','Pierre','Lector'),('WOR','Delaer','Tom','Student'),('WPC','Bontemps','Dirk','Student'),('WYV','Fint','Jean','Lector'),('XBD','Vroman','Marc','Student'),('XHE','Rivier','Philippe','Lector'),('XIC','Van Den Zuur','Jules','Student'),('XLZ','Nebert','Pascal','Student'),('XOE','Gormy','Franck','Student'),('YMX','Laloux','Philippe','Student'),('YOJ','Van Toren','Filip','Student'),('YPI','Vanbeek','Nicolas','Student'),('YVG','Laleu','Jean','Student'),('ZAE','Jerome','Thomas','Student'),('ZCD','Claessens','Filip','Lector'),('ZDX','Filoir','Julien','Student'),('ZFI','Kempen','Dirk','Student'),('ZFJ','Delamer','Dirk','Student'),('ZFY','Vanstraat','Pierre','Student'),('ZGQ','Monjour','Herbert','Student'),('ZHJ','Pier','Nicolas','Student'),('ZIW','Kaart','Philippe','Student'),('ZNJ','Delierre','Marc','Student'),('ZOF','Menuisier','Dirk','Student'),('ZUI','Hulot','Nicolas','Student'),('ZVW','Dugard','Tom','Lector'),('ZZI','Niemen','John','Student');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Visits`
--

DROP TABLE IF EXISTS `Visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Visits` (
  `VisitID` varchar(50) NOT NULL,
  `Logfile` varchar(50) NOT NULL,
  `IPAddress` varchar(15) DEFAULT NULL,
  `VisitTime` time DEFAULT NULL,
  `TotalTime` int(20) DEFAULT NULL,
  `TransferredBytes` int(11) DEFAULT NULL,
  `NumberOfRequests` int(11) DEFAULT NULL,
  `User` varchar(15) DEFAULT NULL,
  `SiteApplicationID` varchar(30) DEFAULT NULL,
  `IPSchool` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`VisitID`),
  KEY `Logfile` (`Logfile`),
  KEY `SiteApplicationID` (`SiteApplicationID`),
  KEY `IPAddress` (`IPSchool`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Visits`
--

LOCK TABLES `Visits` WRITE;
/*!40000 ALTER TABLE `Visits` DISABLE KEYS */;
/*!40000 ALTER TABLE `Visits` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-15 20:42:39

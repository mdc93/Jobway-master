-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: jobway
-- ------------------------------------------------------
-- Server version	5.5.5-10.7.3-MariaDB

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
-- Table structure for table `availability`
--

DROP TABLE IF EXISTS `availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `availability` (
  `idAvailability` int(11) NOT NULL AUTO_INCREMENT,
  `availability` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idAvailability`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availability`
--

LOCK TABLES `availability` WRITE;
/*!40000 ALTER TABLE `availability` DISABLE KEYS */;
INSERT INTO `availability` VALUES (1,'Temps-plein',0),(2,'Mi-temps',0),(3,'Jour',0),(4,'Nuit',0),(5,'Week-end',0);
/*!40000 ALTER TABLE `availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `idCity` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(50) NOT NULL,
  `idPostalCode` int(11) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCity`),
  KEY `City_PostalCode_FK` (`idPostalCode`),
  CONSTRAINT `City_PostalCode_FK` FOREIGN KEY (`idPostalCode`) REFERENCES `postalcode` (`idPostalCode`)
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Rocourt',1,0),(2,'Glain',1,0),(3,'Liège',1,0),(4,'Liège',2,0),(5,'Bressoux',2,0),(6,'Jupille-Sur-Meuse',2,0),(7,'Wandre',2,0),(8,'Grivegnee',3,0),(9,'Angleur',4,0),(10,'Chênee',5,0),(11,'Herstal',6,0),(12,'Milmort',7,0),(13,'Vottem',7,0),(14,'Liers',8,0),(15,'Chaudfontaine',9,0),(16,'Vaux-Sous-Chèvremont',10,0),(17,'Beaufays',11,0),(18,'Embourg',12,0),(19,'Boncelles',13,0),(20,'Seraing',13,0),(21,'Jemeppe-Sur-Meuse',14,0),(22,'Ougrée',15,0),(23,'Neupré',16,0),(24,'Rotheux-Rimière',16,0),(25,'Ehein',16,0),(26,'Neuville-En-Condroz',17,0),(27,'Plainevaux',18,0),(28,'Esneux',19,0),(29,'Tilff',19,0),(30,'Gomzé-Andoumont',20,0),(31,'Dolembreux',20,0),(32,'Rouvreux',20,0),(33,'Sprimont',20,0),(34,'Louveigné',21,0),(35,'Anthisnes',22,0),(36,'Villers-Aux-Tours',23,0),(37,'Hody',24,0),(38,'Tavier',25,0),(39,'Comblain-Au-Pont',26,0),(40,'Poulseur',27,0),(41,'Comblain-La-Tour',28,0),(42,'Comblain-Fairon',28,0),(43,'Hamoir',28,0),(44,'Filot',29,0),(45,'Ferrières',30,0),(46,'Vieuxville',30,0),(47,'My',30,0),(48,'Xhoris',30,0),(49,'Werbomont',30,0),(50,'Hannêche',31,0),(51,'Burdinne',31,0),(52,'Oteppe',31,0),(53,'Lamontzée',31,0),(54,'Marneffe',31,0),(55,'Héron',32,0),(56,'Waret-L\'evêque',32,0),(57,'Lavoir',32,0),(58,'Couthuin',33,0),(59,'Acosse',34,0),(60,'Ambresin',34,0),(61,'Meeffe',34,0),(62,'Wasseiges',34,0),(63,'Boëlhe',35,0),(64,'Lens-Saint-Servais',35,0),(65,'Geer',35,0),(66,'Hollogne-Sur-Geer',35,0),(67,'Omal',36,0),(68,'Darion',37,0),(69,'Ligney',38,0),(70,'Berloz',39,0),(71,'Corswarem',39,0),(72,'Fumal',40,0),(73,'Braives',40,0),(74,'Fallais',40,0),(75,'Ciplet',40,0),(76,'Avennes',40,0),(77,'Ville-En-Hesbaye',40,0),(78,'Latinne',41,0),(79,'Tourinne',42,0),(80,'Abolens',43,0),(81,'Avin',43,0),(82,'Blehen',43,0),(83,'Grand-Hallet',43,0),(84,'Villers-Le-Peuplier',43,0),(85,'Moxhe',43,0),(86,'Thisnes',43,0),(87,'Cras-Avernas',43,0),(88,'Avernas-Le-Bauduin',43,0),(89,'Petit-Hallet',43,0),(90,'Poucet',43,0),(91,'Trognée',43,0),(92,'Bertrée',43,0),(93,'Crehen',43,0),(94,'Hannut',43,0),(95,'Lens-Saint-Remy',43,0),(96,'Merdorp',43,0),(97,'Wansin',43,0),(98,'Pellaines',44,0),(99,'Racour',44,0),(100,'Lincent',44,0),(101,'Grand-Axhe',45,0),(102,'Lantremange',45,0),(103,'Oleye',45,0),(104,'Bleret',45,0),(105,'Waremme',45,0),(106,'Bettincourt',45,0),(107,'Bovenistier',45,0),(108,'Faimes',46,0),(109,'Aineffe',46,0),(110,'Borlez',46,0),(111,'Celles',46,0),(112,'Les Waleffes',46,0),(113,'Viemme',46,0),(114,'Fooz',47,0),(115,'Villers-L\'evêque',47,0),(116,'Awans',47,0),(117,'Othée',47,0),(118,'Hognoul',48,0),(119,'Fexhe-Le-Haut-Clocher',49,0),(120,'Freloux',49,0),(121,'Voroux-Goreux',49,0),(122,'Noville',49,0),(123,'Roloux',49,0),(124,'Pousset',50,0),(125,'Remicourt',50,0),(126,'Lamine',50,0),(127,'Momalle',50,0),(128,'Hodeige',51,0),(129,'Limont',52,0),(130,'Donceel',52,0),(131,'Haneffe',52,0),(132,'Jeneffe',52,0),(133,'Bergilers',53,0),(134,'Lens-Sur-Geer',53,0),(135,'Oreye',53,0),(136,'Grandville',53,0),(137,'Otrange',53,0),(138,'Fize-Le-Marsal',54,0),(139,'Crisnée',54,0),(140,'Kemexhe',54,0),(141,'Odeur',54,0),(142,'Thys',54,0),(143,'Awirs',55,0),(144,'Flémalle-Grande',55,0),(145,'Flémalle',55,0),(146,'Chokier',55,0),(147,'Ivoz-Ramet',55,0),(148,'Mons-Lez-Liège',55,0),(149,'Flémalle-Haute',55,0),(150,'Montegnée',56,0),(151,'Saint-Nicolas',56,0),(152,'Tilleur',56,0),(153,'Ans',57,0),(154,'Loncin',58,0),(155,'Xhendremael',59,0),(156,'Alleur',59,0),(157,'Juprelle',60,0),(158,'Slins',60,0),(159,'Lantin',60,0),(160,'Voroux-Lez-Liers',61,0),(161,'Paifve',62,0),(162,'Wihogne',62,0),(163,'Villers-Saint-Siméon',63,0),(164,'Fexhe-Slins',64,0),(165,'Bierset',65,0),(166,'Grâce-Hollogne',65,0),(167,'Grâce-Berleur',65,0),(168,'Horion-Hozémont',65,0),(169,'Hollogne-Aux-Pierres',65,0),(170,'Velroux',65,0),(171,'Saint-Georges-Sur-Meuse',66,0),(172,'Ehein',67,0),(173,'Hermalle-Sous-Huy',67,0),(174,'Engis',67,0),(175,'Clermont-Sous-Huy',67,0),(176,'Ben-Ahin',68,0),(177,'Huy',68,0),(178,'Tihange',68,0),(179,'Huccorgne',69,0),(180,'Moha',69,0),(181,'Bas-Oha',69,0),(182,'Antheit',69,0),(183,'Vinalmont',69,0),(184,'Wanze',69,0),(185,'Vaux-Et-Borset',70,0),(186,'Villers-Le-Bouillet',70,0),(187,'Fize-Fontaine',70,0),(188,'Vieux-Waleffe',70,0),(189,'Warnant-Dreye',70,0),(190,'Chapon-Seraing',71,0),(191,'Bodegnée',71,0),(192,'Seraing-Le-Château',71,0),(193,'Verlaine',71,0),(194,'Flône',72,0),(195,'Ombret',72,0),(196,'Jehay',72,0),(197,'Ampsin',72,0),(198,'Amay',72,0),(199,'Nandrin',73,0),(200,'Yernée-Fraineux',73,0),(201,'Saint-Séverin',73,0),(202,'Villers-Le-Temple',73,0),(203,'Fraiture',74,0),(204,'Ramelot',74,0),(205,'Soheit-Tinlot',74,0),(206,'Seny',74,0),(207,'Tinlot',74,0),(208,'Abée',74,0),(209,'Clavier',75,0),(210,'Terwagne',75,0),(211,'Bois-Et-Borsu',75,0),(212,'Ocquier',75,0),(213,'Les Avins',75,0),(214,'Pailhe',75,0),(215,'Vyle-Et-Tharoul',76,0),(216,'Marchin',76,0),(217,'Vierset-Barse',77,0),(218,'Modave',77,0),(219,'Outrelouxhe',77,0),(220,'Strée-Lez-Huy',77,0),(221,'Ellemelle',78,0),(222,'Ouffet',78,0),(223,'Warzée',78,0),(224,'Richelle',79,0),(225,'Visé',79,0),(226,'Lixhe',79,0),(227,'Lanaye',79,0),(228,'Argenteau',80,0),(229,'Cheratte',81,0),(230,'Saint-André',82,0),(231,'Bombaye',83,0),(232,'Dalhem',83,0),(233,'Berneau',83,0),(234,'Feneur',83,0),(235,'Mortroux',83,0),(236,'Neufchâteau',84,0),(237,'Warsage',84,0),(238,'Beyne-Heusay',85,0),(239,'Bellaire',85,0),(240,'Queue-Du-Bois',85,0),(241,'Fléron',86,0),(242,'Retinne',87,0),(243,'Magnée',88,0),(244,'Romsée',89,0),(245,'Micheroux',90,0),(246,'Tignée',90,0),(247,'Ayeneux',90,0),(248,'Soumagne',90,0),(249,'Evegnée',91,0),(250,'Cérexhe-Heuseux',92,0),(251,'Melen',93,0),(252,'Chaineux',94,0),(253,'Grand-Rechain',94,0),(254,'Julémont',94,0),(255,'Herve',94,0),(256,'Battice',95,0),(257,'Xhendelesse',96,0),(258,'Bolland',97,0),(259,'Charneux',98,0),(260,'Blégny',99,0),(261,'Mortier',99,0),(262,'Trembleur',99,0),(263,'Housse',100,0),(264,'Barchon',100,0),(265,'Saive',100,0),(266,'Saint-Remy',101,0),(267,'Hermée',102,0),(268,'Oupeye',102,0),(269,'Hermalle-Sous-Argenteau',103,0),(270,'Houtain-Saint-Siméon',104,0),(271,'Heure-Le-Romain',104,0),(272,'Vivegnis',105,0),(273,'Haccourt',106,0),(274,'Boirs',107,0),(275,'Eben-Emael',107,0),(276,'Glons',107,0),(277,'Roclenge-Sur-Geer',107,0),(278,'Wonck',107,0),(279,'Bassenge',107,0),(280,'Eupen',108,0),(281,'Kettenis',109,0),(282,'Lontzen',110,0),(283,'Walhorn',111,0),(284,'La Calamine',112,0),(285,'Neu-Moresnet',113,0),(286,'Hergenrath',114,0),(287,'Raeren',115,0),(288,'Hauset',115,0),(289,'Eynatten',116,0),(290,'Butgenbach',117,0),(291,'Elsenborn',117,0),(292,'Bullange',118,0),(293,'Manderfeld',118,0),(294,'Rocherath',119,0),(295,'Meyerode',120,0),(296,'Amblève',120,0),(297,'Heppenbach',121,0),(298,'Recht',122,0),(299,'Saint-Vith',122,0),(300,'Schoenberg',123,0),(301,'Lommersweiler',124,0),(302,'Crombach',125,0),(303,'Burg-Reuland',126,0),(304,'Reuland',126,0),(305,'Thommen',127,0),(306,'Lambermont',128,0),(307,'Verviers',128,0),(308,'Ensival',128,0),(309,'Petit-Rechain',128,0),(310,'Polleur',128,0),(311,'Stembert',129,0),(312,'Heusy',130,0),(313,'Dison',131,0),(314,'Andrimont',132,0),(315,'Limbourg',133,0),(316,'Bilstain',134,0),(317,'Goé',135,0),(318,'Baelen',136,0),(319,'Membach',136,0),(320,'Welkenraedt',137,0),(321,'Henri-Chapelle',138,0),(322,'Jalhay',139,0),(323,'Sart-Lez-Spa',139,0),(324,'Plombières',140,0),(325,'Moresnet',140,0),(326,'Montzen',140,0),(327,'Sippenaeken',141,0),(328,'Gemmenich',141,0),(329,'Hombourg',142,0),(330,'Pepinster',143,0),(331,'Cornesse',143,0),(332,'Wegnez',143,0),(333,'Soiron',144,0),(334,'Nessonvaux',145,0),(335,'Trooz',145,0),(336,'Fraipont',145,0),(337,'Forêt',145,0),(338,'Olne',146,0),(339,'Aubel',147,0),(340,'Thimister',148,0),(341,'Thimister-Clermont',148,0),(342,'Clermont',148,0),(343,'Spa',149,0),(344,'La Reid',150,0),(345,'Theux',150,0),(346,'Polleur',150,0),(347,'Ernonheid',151,0),(348,'Aywaille',151,0),(349,'Louveigné',151,0),(350,'Harzé',151,0),(351,'Waimes',152,0),(352,'Sourbrodt',152,0),(353,'Robertville',152,0),(354,'Faymonville',152,0),(355,'Bevercé',153,0),(356,'Malmedy',153,0),(357,'Stavelot',154,0),(358,'Francorchamps',154,0),(359,'Fosse',155,0),(360,'Wanne',155,0),(361,'Trois-Ponts',155,0),(362,'Basse-Bodeux',156,0),(363,'Chevron',157,0),(364,'La Gleize',157,0),(365,'Rahier',157,0),(366,'Lorcé',157,0),(367,'Stoumont',157,0),(368,'Bra',158,0),(369,'Lierneux',158,0),(370,'Arbrefontaine',158,0),(371,'Riemst',159,0);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `civilstatus`
--

DROP TABLE IF EXISTS `civilstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `civilstatus` (
  `idCivilStatus` int(11) NOT NULL AUTO_INCREMENT,
  `civilStatusName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCivilStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `civilstatus`
--

LOCK TABLES `civilstatus` WRITE;
/*!40000 ALTER TABLE `civilstatus` DISABLE KEYS */;
INSERT INTO `civilstatus` VALUES (1,'Célibataire',0),(2,'Marié',0),(3,'Cohabitant légal',0),(4,'Divorcé',0),(5,'Autre',0);
/*!40000 ALTER TABLE `civilstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `idCountry` int(11) NOT NULL AUTO_INCREMENT,
  `countryName` varchar(50) NOT NULL,
  `idcountryType` int(11) NOT NULL DEFAULT 4,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCountry`),
  KEY `Country_CountryType_FK` (`idcountryType`),
  CONSTRAINT `Country_CountryType_FK` FOREIGN KEY (`idcountryType`) REFERENCES `countrytype` (`idcountryType`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'\"Réfugié\"',1,0),(2,'Abu Dhabi',4,0),(3,'Afars et Issas',4,0),(4,'Afrique du Sud',4,0),(5,'Afghanistan',4,0),(6,'Albanie',4,0),(7,'Algérie',4,0),(8,'Allemagne',3,0),(9,'Allemagne -  Ex RDA',4,0),(10,'Allemagne -  Ex RFA',3,0),(11,'Andorre',4,0),(12,'Angola',4,0),(13,'Anguilla (R.U.)',4,0),(14,'Antigua-et-Barbuda',4,0),(15,'Antilles américaines',4,0),(16,'Antilles britaniques',4,0),(17,'Antilles neerlandaise',4,0),(18,'Apatrides',1,0),(19,'Arabie Saoudite',4,0),(20,'Archipelle des Carolines',4,0),(21,'Argentine',4,0),(22,'Arménie',4,0),(23,'Australie',4,0),(24,'Autriche',3,0),(25,'Azerbaïdjan',4,0),(26,'Bahamas',4,0),(27,'Bahreïn',4,0),(28,'Bangladesh',4,0),(29,'Barbade',4,0),(30,'Belgique',2,0),(31,'Belize',4,0),(32,'Bénin',4,0),(33,'Bermudes',4,0),(34,'Bhoutan',4,0),(35,'Biélorussie',4,0),(36,'Birmanie',4,0),(37,'Bolivie',4,0),(38,'Bophuatswana',4,0),(39,'Bosnie-Herzégovine',4,0),(40,'Botswana',4,0),(41,'Brésil',4,0),(42,'Brunei',4,0),(43,'Bulgarie',3,0),(44,'Burkina Faso',4,0),(45,'Burundi',4,0),(46,'Caimanes (R.U.)',4,0),(47,'Cambodge',4,0),(48,'Cambodge (République Khmer Du)',4,0),(49,'Cameroun',4,0),(50,'Canada',4,0),(51,'Cap-Vert',4,0),(52,'Chili',4,0),(53,'Chine',4,0),(54,'Chypre',3,0),(55,'Colombie',4,0),(56,'Comores',4,0),(57,'Congo Belge',4,0),(58,'Congo (Brazzaville)',4,0),(59,'Congo (République Démocratique du)',4,0),(60,'Corée du Nord',4,0),(61,'Corée du Sud',4,0),(62,'Costa Rica',4,0),(63,'Côte d’Ivoire',4,0),(64,'Croatie',3,0),(65,'Cuba',4,0),(66,'Danemark',3,0),(67,'Diverses iles pacifiques US',4,0),(68,'Djibouti',4,0),(69,'Dominique (République de la)',4,0),(70,'Dom Tom (France)',4,0),(71,'Égypte',4,0),(72,'Émirats arabes unis',4,0),(73,'Équateur',4,0),(74,'Érythrée',4,0),(75,'Espagne',3,0),(76,'Eswatini',4,0),(77,'Estonie',3,0),(78,'États-Unis d\'Amérique',4,0),(79,'Éthiopie',4,0),(80,'Fidji',4,0),(81,'Finlande',3,0),(82,'France',3,0),(83,'Gabon',4,0),(84,'Gambie',4,0),(85,'Géorgie',4,0),(86,'Géorgie du sud et Iles Sandwich',4,0),(87,'Ghana',4,0),(88,'Grèce',3,0),(89,'Grenade',4,0),(90,'Groenland (DK)',4,0),(91,'Guatemala',4,0),(92,'Guam',4,0),(93,'Guinée',4,0),(94,'Guinée équatoriale',4,0),(95,'Guinée-Bissau',4,0),(96,'Guyane',4,0),(97,'Guyane Hollandaise',4,0),(98,'Haïti',4,0),(99,'Haute-Volta',4,0),(100,'Hawai',4,0),(101,'Honduras',4,0),(102,'Honduras Britanique',4,0),(103,'Hong-Kong',4,0),(104,'Hongrie',3,0),(105,'Îles Cook',4,0),(106,'Ile de Dominica',4,0),(107,'Ile de Santhome',4,0),(108,'Ile Ryukyu',4,0),(109,'Ile Sainte Hélène',4,0),(110,'Iles Bouvet',4,0),(111,'Iles Canaries',4,0),(112,'Iles du Cap vert',4,0),(113,'Iles du pacifique',4,0),(114,'Iles Falkland',4,0),(115,'Iles Gilbert',4,0),(116,'Iles Heard et Mc Donald',4,0),(117,'Iles Mariannes du nord',4,0),(118,'Îles Marshall',4,0),(119,'Iles Salomon',4,0),(120,'Iles Turks et Caicos',4,0),(121,'Iles Vierges',4,0),(122,'INCONNU',6,0),(123,'Inde',4,0),(124,'Indonésie',4,0),(125,'Irak',4,0),(126,'Iran',4,0),(127,'Irlande',3,0),(128,'Islande',4,0),(129,'Israël',4,0),(130,'Italie',3,0),(131,'Jamaïque',4,0),(132,'Japon',4,0),(133,'Jordanie',4,0),(134,'Kazakhstan',4,0),(135,'Kenya',4,0),(136,'Kirghizistan',4,0),(137,'Kiribati',4,0),(138,'Kosovo',4,0),(139,'Koweït',4,0),(140,'Laos',4,0),(141,'Lesotho',4,0),(142,'Lettonie',3,0),(143,'Liban',4,0),(144,'Liberia',4,0),(145,'Libye',4,0),(146,'Liechtenstein',4,0),(147,'Lituanie',3,0),(148,'Luxembourg',3,0),(149,'Macédoine',4,0),(150,'Madagascar',4,0),(151,'Malaisie',4,0),(152,'Malawi',4,0),(153,'Maldives',4,0),(154,'Mali',4,0),(155,'Malte',3,0),(156,'Maroc',4,0),(157,'Maurice',4,0),(158,'Mauritanie',4,0),(159,'Mexique',4,0),(160,'Micronésie',4,0),(161,'Moldavie',4,0),(162,'Monaco',4,0),(163,'Mongolie',4,0),(164,'Monténégro',4,0),(165,'Montserrat (R.U.)',4,0),(166,'Mozambique',4,0),(167,'Myanmar (Birmanie)',4,0),(168,'Namibie',4,0),(169,'Nauru',4,0),(170,'Népal',4,0),(171,'Nicaragua',4,0),(172,'Niger',4,0),(173,'Nigeria',4,0),(174,'Niue',4,0),(175,'Norvège',4,0),(176,'Nouvelle-Calédonie',4,0),(177,'Nouvelle-Hebrides',4,0),(178,'Nouvelle-Zélande',4,0),(179,'Oman',4,0),(180,'Ouganda',4,0),(181,'Ouzbékistan',4,0),(182,'Pakistan',4,0),(183,'Pakistant occidental et oriental',4,0),(184,'Palaos',4,0),(185,'Palestine (territoires)',4,0),(186,'Panama',4,0),(187,'Papouasie-Nouvelle-Guinée',4,0),(188,'Paraguay',4,0),(189,'Pays-Bas',3,0),(190,'Peninsule Malaisienne',4,0),(191,'Pérou',4,0),(192,'Philippines',4,0),(193,'Pologne',3,0),(194,'Porto Rico',4,0),(195,'Portugal',3,0),(196,'Qatar',4,0),(197,'République centrafricaine',4,0),(198,'République Dominicaine',4,0),(199,'République tchèque',3,0),(200,'Rhodésie',4,0),(201,'Roumanie',3,0),(202,'Royaume-Uni',4,0),(203,'Russie',4,0),(204,'Rwanda',4,0),(205,'Sabah',4,0),(206,'Saint-Kitts-et-Nevis',4,0),(207,'Saint-Kitts-et-Nevis & Anguilla',4,0),(208,'Saint-Vincent-et-les-Grenadines',4,0),(209,'Sainte-Lucie',4,0),(210,'Saint-Marin',4,0),(211,'Salvador (El)',4,0),(212,'Samoa',4,0),(213,'Samoa Américaine',4,0),(214,'São Tomé-et-Principe',4,0),(215,'Sarawak',4,0),(216,'Sénégal',4,0),(217,'Senegambie',4,0),(218,'Serbie - Monténégro (Ex)',4,0),(219,'Serbie',4,0),(220,'Seychelles',4,0),(221,'Sierra Leone',4,0),(222,'Singapour',4,0),(223,'Slovaquie',3,0),(224,'Slovénie',4,0),(225,'Somalie',4,0),(226,'Soudan',4,0),(227,'Soudan du Sud',4,0),(228,'Sri Lanka',4,0),(229,'Suède',3,0),(230,'Suisse',4,0),(231,'Suriname',4,0),(232,'Swaziland',4,0),(233,'Syrie',4,0),(234,'Tadjikistan',4,0),(235,'Taiwan',4,0),(236,'Tanganyka (Ex)',4,0),(237,'Tanzanie',4,0),(238,'Tchad',4,0),(239,'Tchecoslovaquie (Ex)',3,0),(240,'Thaïlande',4,0),(241,'Timor oriental',4,0),(242,'Togo',4,0),(243,'Tokelau (NZ)',4,0),(244,'Tonga',4,0),(245,'Transkei',4,0),(246,'Trinité-et-Tobago',4,0),(247,'Tunisie',4,0),(248,'Turkménistan',4,0),(249,'Turquie',4,0),(250,'Tuvalu',4,0),(251,'Ukraine',4,0),(252,'Uruguay',4,0),(253,'Urundi',4,0),(254,'Vanuatu',4,0),(255,'Vatican',4,0),(256,'Venezuela',4,0),(257,'Viêt Nam',4,0),(258,'Viêt Nam (ex république)',4,0),(259,'Yémen',4,0),(260,'Yémen (république Arabe - Ex)',4,0),(261,'Yémen (république démocratique - Ex)',4,0),(262,'Yougoslavie (Ex)',4,0),(263,'Zaïre (république du)',4,0),(264,'Zambie',4,0),(265,'Zanzibart et Pempa',4,0),(266,'Zimbabwe',4,0),(267,'Zones libres',4,0);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countrytype`
--

DROP TABLE IF EXISTS `countrytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countrytype` (
  `idcountryType` int(11) NOT NULL AUTO_INCREMENT,
  `countryTypeName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idcountryType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countrytype`
--

LOCK TABLES `countrytype` WRITE;
/*!40000 ALTER TABLE `countrytype` DISABLE KEYS */;
INSERT INTO `countrytype` VALUES (1,'Apatride',0),(2,'Belgique',0),(3,'Ressortissant Union Européenne',0),(4,'Ressortissant hors Union Européenne',0),(6,'Inconnu',0);
/*!40000 ALTER TABLE `countrytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipa`
--

DROP TABLE IF EXISTS `dipa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dipa` (
  `idDipa` int(11) NOT NULL AUTO_INCREMENT,
  `dipaName` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`idDipa`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `disability`
--

DROP TABLE IF EXISTS `disability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disability` (
  `idDisability` int(11) NOT NULL AUTO_INCREMENT,
  `disReco` tinyint(1) DEFAULT 0,
  `disOther` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idDisability`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `driverlicense`
--

DROP TABLE IF EXISTS `driverlicense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driverlicense` (
  `idDriverLicense` int(11) NOT NULL AUTO_INCREMENT,
  `driverLicenseType` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idDriverLicense`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driverlicense`
--

LOCK TABLES `driverlicense` WRITE;
/*!40000 ALTER TABLE `driverlicense` DISABLE KEYS */;
INSERT INTO `driverlicense` VALUES (1,'A',0),(2,'B',0),(3,'C',0),(4,'D',0),(5,'E',0);
/*!40000 ALTER TABLE `driverlicense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_eve`
--

DROP TABLE IF EXISTS `emp_eve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_eve` (
  `idEmployee` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  PRIMARY KEY (`idEmployee`,`idEvent`),
  KEY `Emp_Eve_Event0_FK` (`idEvent`),
  CONSTRAINT `Emp_Eve_Employee_FK` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`idEmployee`),
  CONSTRAINT `Emp_Eve_Event0_FK` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `empPseudo` varchar(50) NOT NULL,
  `empIsDelete` tinyint(1) NOT NULL,
  `empName` varchar(50) DEFAULT NULL,
  `empFirstName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEmployee`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Administrateur',0,'',''),(2,'HON',0,NULL,NULL),(3,'CEL',0,NULL,NULL),(4,'MAR',0,NULL,NULL),(5,'SAN',0,NULL,NULL),(6,'JOR',0,NULL,NULL),(7,'CIN',0,NULL,NULL),(8,'TAM',0,NULL,NULL),(9,'MNA',0,NULL,NULL),(10,'X2',0,NULL,NULL),(11,'X1',0,NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `idEvent` int(11) NOT NULL AUTO_INCREMENT,
  `eventDate` date NOT NULL DEFAULT curdate(),
  `eventDuration` smallint(5) unsigned DEFAULT 0,
  `eventNote` text DEFAULT NULL,
  `idPerson` int(11) NOT NULL,
  `idTheme` int(11) NOT NULL,
  `idEventType` int(11) DEFAULT NULL,
  `exitEvent` tinyint(1) DEFAULT NULL,
  `idExitType` int(11) DEFAULT NULL,
  `idRequired` int(11) DEFAULT NULL,
  `idOrganization` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEvent`),
  KEY `Event_Person_FK` (`idPerson`),
  KEY `Event_Theme0_FK` (`idTheme`),
  KEY `Event_EventType1_FK` (`idEventType`),
  KEY `Event_Required2_FK` (`idRequired`),
  KEY `Event_ExitType3_FK` (`idExitType`),
  KEY `event_organization_FK` (`idOrganization`),
  CONSTRAINT `Event_EventType1_FK` FOREIGN KEY (`idEventType`) REFERENCES `eventtype` (`idEventType`),
  CONSTRAINT `Event_ExitType3_FK` FOREIGN KEY (`idRequired`) REFERENCES `required` (`idRequired`),
  CONSTRAINT `Event_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`),
  CONSTRAINT `Event_Required2_FK` FOREIGN KEY (`idExitType`) REFERENCES `exittype` (`idExitType`),
  CONSTRAINT `Event_Theme0_FK` FOREIGN KEY (`idTheme`) REFERENCES `theme` (`idTheme`),
  CONSTRAINT `event_organization_FK` FOREIGN KEY (`idOrganization`) REFERENCES `organization` (`idOrganization`)
) ENGINE=InnoDB AUTO_INCREMENT=3492 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `eventtype`
--

DROP TABLE IF EXISTS `eventtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventtype` (
  `idEventType` int(11) NOT NULL AUTO_INCREMENT,
  `eventTypeName` varchar(60) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idEventType`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventtype`
--

LOCK TABLES `eventtype` WRITE;
/*!40000 ALTER TABLE `eventtype` DISABLE KEYS */;
INSERT INTO `eventtype` VALUES (1,'Rencontre',1),(2,'Administratif',0),(3,'Recherche et analyse',0),(4,'Entretien téléphonique',0),(5,'Courrier',0),(6,'Discussion sur un cas',0),(7,'Traitement et production',0),(8,'Urgence sans randez-vous',0),(9,'Permis de conduire individuel',0),(10,'Permis de conduire collectif',0),(11,'Scéance collective',0),(12,'Demande',0),(13,'Rendez-vous',0);
/*!40000 ALTER TABLE `eventtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER eventtype_update
BEFORE UPDATE
ON eventtype FOR EACH ROW
BEGIN 
		IF(old.idEventType=12) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Impossible de changer : Entrée nécessaire";
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER eventtype_delete
BEFORE DELETE
ON eventtype FOR EACH ROW
BEGIN
		IF(OLD.idEventType=12) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Impossible de supprimer : Entrée nécessaire";
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `exittype`
--

DROP TABLE IF EXISTS `exittype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exittype` (
  `idExitType` int(11) NOT NULL AUTO_INCREMENT,
  `exitTypeName` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idExitType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exittype`
--

LOCK TABLES `exittype` WRITE;
/*!40000 ALTER TABLE `exittype` DISABLE KEYS */;
INSERT INTO `exittype` VALUES (1,'pas de sortie',0),(2,'Emploi indépendant',0),(3,'Recherche d\'emploi',0),(4,'En enseignement ou en formation',0),(5,'Sortie non connue',0),(6,'Abandon',0);
/*!40000 ALTER TABLE `exittype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familyreunion`
--

DROP TABLE IF EXISTS `familyreunion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familyreunion` (
  `idFamilyReunion` int(11) NOT NULL AUTO_INCREMENT,
  `FamilyReunionType` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idFamilyReunion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyreunion`
--

LOCK TABLES `familyreunion` WRITE;
/*!40000 ALTER TABLE `familyreunion` DISABLE KEYS */;
INSERT INTO `familyreunion` VALUES (1,'Conjoint',0),(2,'Ascendant',0),(3,'Descendant',0),(4,'Tiers',0);
/*!40000 ALTER TABLE `familyreunion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `idFile` int(11) NOT NULL AUTO_INCREMENT,
  `registrationDate` date DEFAULT NULL,
  `modificationDate` date DEFAULT NULL,
  `fileStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idFile`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `formation`
--

DROP TABLE IF EXISTS `formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formation` (
  `idFormation` int(11) NOT NULL AUTO_INCREMENT,
  `formationName` varchar(100) NOT NULL,
  `equIntro` tinyint(1) DEFAULT 0,
  `equObt` tinyint(1) DEFAULT 0,
  `foreignFormation` tinyint(1) DEFAULT 0,
  `foreignHUE` tinyint(1) DEFAULT 0,
  `formationDate` date DEFAULT NULL,
  `idFormationType` int(11) NOT NULL,
  PRIMARY KEY (`idFormation`),
  KEY `Formation_FormationType_FK` (`idFormationType`),
  CONSTRAINT `Formation_FormationType_FK` FOREIGN KEY (`idFormationType`) REFERENCES `formationtype` (`idFormationType`)
) ENGINE=InnoDB AUTO_INCREMENT=1781 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formation`
--

LOCK TABLES `formation` WRITE;
/*!40000 ALTER TABLE `formation` DISABLE KEYS */;
INSERT INTO `formation` VALUES (1,'Doctorat biostatistiques',NULL,NULL,0,NULL,NULL,8),(2,'CEB',NULL,NULL,0,NULL,NULL,2),(3,'CESS ',NULL,NULL,0,NULL,NULL,5),(4,'',NULL,NULL,0,NULL,NULL,5),(5,'spécialisation en finance',NULL,NULL,0,NULL,NULL,8),(6,'Master en Gestion',NULL,NULL,0,NULL,NULL,8),(7,'Formation en Logistique',NULL,NULL,0,NULL,NULL,5),(8,'',NULL,NULL,0,NULL,NULL,5),(9,'Soins infirmiers',NULL,NULL,0,NULL,NULL,7),(10,'',NULL,NULL,0,NULL,NULL,10),(11,'aide soignante',NULL,NULL,0,NULL,NULL,5),(12,'CEB',NULL,NULL,0,NULL,NULL,2),(13,'',NULL,NULL,0,NULL,NULL,5),(14,'CESS couture',NULL,NULL,0,NULL,NULL,5),(15,'',NULL,NULL,0,NULL,NULL,5),(16,'',NULL,NULL,0,NULL,NULL,2),(17,'Bachelier en comptabilité et finances',NULL,NULL,0,NULL,NULL,7),(18,'Certificat d\'enseignement secondaire du supérieur',NULL,NULL,0,NULL,NULL,5),(19,'Gestion des risques naturels',NULL,NULL,0,NULL,NULL,8),(20,'',NULL,NULL,0,NULL,NULL,5),(21,'',NULL,NULL,0,NULL,NULL,7),(22,'',NULL,NULL,0,NULL,NULL,5),(23,'Ingénieur',NULL,NULL,0,NULL,NULL,8),(24,'Sciences biomédicales et pharmaceutiques',NULL,NULL,0,NULL,NULL,8),(25,'bachelier en gestion',1,NULL,1,NULL,NULL,7),(26,'CESS',1,NULL,1,NULL,NULL,5),(27,'CESS',0,0,1,NULL,NULL,5),(28,'Bachelier',NULL,NULL,1,NULL,NULL,7),(29,'Bachelier en Commerce ',0,NULL,1,NULL,NULL,7),(30,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(31,'bachelier en science',0,NULL,1,1,NULL,7),(32,'CESS',NULL,NULL,1,NULL,NULL,5),(33,'CESS',NULL,NULL,1,NULL,NULL,5),(34,'Bachelier en informatique',NULL,NULL,1,NULL,NULL,7),(35,'master',0,NULL,1,NULL,NULL,8),(36,'CESS',0,NULL,1,NULL,NULL,5),(37,'Bachelier Comptabilité (4ans)',0,0,1,1,NULL,7),(38,'CESS',0,NULL,1,NULL,NULL,5),(39,'BACHELIER',0,0,1,NULL,NULL,7),(40,'CESDD',0,NULL,1,NULL,NULL,4),(41,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(42,'Graduat Technical Institute',0,NULL,1,NULL,NULL,7),(43,'Bachelier Economie',NULL,NULL,1,NULL,NULL,7),(44,'CE1D',0,NULL,1,NULL,NULL,3),(45,'Bachelier journaliste',0,0,1,NULL,NULL,7),(46,'MASTER',0,NULL,1,NULL,NULL,8),(47,'Master en Pharmacie',0,NULL,1,1,NULL,8),(48,'CE1D',NULL,NULL,1,NULL,NULL,3),(49,'Master Education de l\'art',0,NULL,1,NULL,NULL,8),(50,'CESS',NULL,NULL,1,NULL,NULL,5),(51,'certificat d\'études de base',NULL,NULL,1,1,NULL,2),(52,'CEB',0,NULL,1,NULL,NULL,2),(53,'Ecole militaire',NULL,NULL,1,NULL,NULL,10),(54,'CESS',NULL,NULL,1,NULL,NULL,5),(55,'CEB',0,0,1,NULL,NULL,2),(56,'CE1D',NULL,NULL,1,NULL,NULL,3),(57,'CESDD',0,0,1,NULL,NULL,4),(58,'',NULL,NULL,1,NULL,NULL,5),(59,'BACHELIER EN COMPTABILITE',0,0,1,NULL,NULL,7),(60,'CESDD',0,NULL,1,NULL,NULL,4),(61,'CESDD',0,NULL,1,1,NULL,4),(62,'Master Economie (statistique)',NULL,NULL,1,1,NULL,8),(63,'CESS',0,0,1,NULL,NULL,5),(64,'Bachelier Business administration et économie',0,NULL,1,1,NULL,7),(65,'CEB',NULL,NULL,1,NULL,NULL,2),(66,'CE1D',NULL,NULL,1,NULL,NULL,3),(67,'CESS',0,NULL,1,NULL,NULL,5),(68,'CESDD',NULL,NULL,1,NULL,NULL,4),(69,'CESS',0,0,1,NULL,NULL,5),(70,'CESS',0,NULL,1,NULL,NULL,5),(71,'CESS',0,NULL,1,NULL,NULL,5),(72,'MASTER: Ingénieur civil ',0,0,1,NULL,NULL,8),(73,'Bachelier traduction arabe-anglais',0,NULL,1,1,NULL,7),(74,'CESDD',0,NULL,1,NULL,NULL,4),(75,'Bachelier en droit',1,NULL,1,NULL,NULL,7),(76,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(77,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(78,'Sans diplôme',0,NULL,1,NULL,NULL,1),(79,'CESDD',NULL,NULL,1,NULL,NULL,4),(80,'CEB',NULL,NULL,1,NULL,NULL,2),(81,'',NULL,NULL,1,NULL,NULL,1),(82,'BACHELIER',0,0,1,NULL,NULL,7),(83,'CESS',NULL,NULL,1,NULL,NULL,5),(84,'couturier',0,NULL,1,1,NULL,5),(85,'bachelier en pharmacie',0,NULL,1,NULL,NULL,7),(86,'master en bio-chimie',0,0,1,1,NULL,8),(87,'CE1D',NULL,NULL,1,NULL,NULL,3),(88,'CE1D',NULL,NULL,1,NULL,NULL,3),(89,'',NULL,NULL,1,1,NULL,2),(90,'Infirmière',0,0,1,1,NULL,8),(91,'Certificat d\'enseignement secondaire du supérieur',NULL,NULL,1,1,NULL,5),(92,'BACHELIER',1,1,1,NULL,NULL,7),(93,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(94,'CESS',1,NULL,1,NULL,NULL,5),(95,'CESS',0,NULL,1,1,NULL,5),(96,'CESS',NULL,NULL,1,NULL,NULL,5),(97,'Bachelier en physique',0,NULL,1,NULL,NULL,7),(98,'Master en management',0,0,1,NULL,NULL,8),(99,'CESS',0,0,1,NULL,NULL,5),(100,'master',0,NULL,1,NULL,NULL,8),(101,'Institutrice maternelle',NULL,NULL,1,1,NULL,8),(102,'Bachelier',1,NULL,1,NULL,NULL,7),(103,'master comptabilité-finance',NULL,NULL,1,1,NULL,8),(104,'Bachelier/graduat - Institutrice',NULL,NULL,1,NULL,NULL,7),(105,'MASTER',0,0,1,NULL,NULL,8),(106,'CESS',1,1,1,NULL,NULL,5),(107,'Master : maritime',0,NULL,1,NULL,NULL,8),(108,'CESS',0,NULL,1,1,NULL,5),(109,'CESDD',NULL,NULL,1,NULL,NULL,4),(110,'CESS',1,1,1,NULL,NULL,5),(111,'CESI',0,NULL,1,NULL,NULL,10),(112,'CEB',0,NULL,1,NULL,NULL,2),(113,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(114,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(115,'CEB',0,0,1,NULL,NULL,2),(116,'Master en économie',0,NULL,1,NULL,NULL,8),(117,'CESS',0,0,1,NULL,NULL,5),(118,'Master en anglais',0,0,1,NULL,NULL,8),(119,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(120,'Bachelier en droit',0,0,1,NULL,NULL,7),(121,'Master en Informatique',0,0,1,NULL,NULL,8),(122,'CESS',0,NULL,1,NULL,NULL,5),(123,'CESS',NULL,NULL,1,NULL,NULL,5),(124,'bachelier',0,NULL,1,NULL,NULL,7),(125,'CE1D',NULL,NULL,1,NULL,NULL,3),(126,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(127,'CESS',NULL,NULL,1,NULL,NULL,5),(128,'CESDD',NULL,NULL,1,NULL,NULL,4),(129,'Littérature arabe',1,1,1,NULL,NULL,10),(130,'Bachelier en littérature française',1,1,1,NULL,NULL,7),(131,'CESS',NULL,NULL,1,NULL,NULL,5),(132,'BAC + 2 BTS en gestion des entreprises',1,NULL,1,1,NULL,6),(133,'MASTER EN ECONOMIE',0,NULL,1,NULL,NULL,8),(134,'CESS',NULL,NULL,1,1,NULL,5),(135,'CEB',0,NULL,1,NULL,NULL,2),(136,'CESS',0,NULL,1,NULL,NULL,5),(137,'',0,0,1,1,NULL,7),(138,'Bachelier infirmier',NULL,NULL,1,NULL,NULL,7),(139,'',NULL,NULL,1,NULL,NULL,1),(140,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(141,'Certificat d\'enseignement secondaire du supérieur',0,0,1,1,NULL,5),(142,'CESS',0,0,1,NULL,NULL,5),(143,'bachelier',0,NULL,1,NULL,NULL,7),(144,'CEB',NULL,NULL,1,NULL,NULL,2),(145,'bachelier en droit',0,NULL,1,NULL,NULL,7),(146,'Master en marketing',0,NULL,1,1,NULL,8),(147,'CESS',NULL,NULL,1,NULL,NULL,5),(148,'Ens. Sec. Compl.',0,NULL,1,NULL,NULL,6),(149,'BACHELIER',1,0,1,NULL,NULL,7),(150,'MASTER EN AEROTIQUE',0,NULL,1,NULL,NULL,8),(151,'CESS',0,0,1,NULL,NULL,5),(152,'BACHELIER EN ARCHITECTURE',0,NULL,1,NULL,NULL,7),(153,'CESS',0,NULL,1,NULL,NULL,5),(154,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(155,'CEB',NULL,NULL,1,NULL,NULL,2),(156,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(157,'certificat d\'études du premier degré de l\'enseignement secondaire ',0,NULL,1,NULL,NULL,3),(158,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(159,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(160,'bachelier en commerce',NULL,NULL,1,NULL,NULL,7),(161,'Droit ',0,0,1,NULL,NULL,10),(162,'master en finance',0,NULL,1,1,NULL,8),(163,'master en pharmacie',0,NULL,1,1,NULL,8),(164,'CE1DD',0,1,1,NULL,NULL,3),(165,'CESDD',NULL,NULL,1,NULL,NULL,4),(166,'bachelier',NULL,NULL,1,NULL,NULL,7),(167,'Bachelier institutrice',NULL,NULL,1,NULL,NULL,7),(168,'Primaire',0,NULL,1,NULL,NULL,10),(169,'CESS',0,NULL,1,NULL,NULL,5),(170,'Bachelier en géographie',0,NULL,1,NULL,NULL,7),(171,'CEB',0,0,1,NULL,NULL,2),(172,'Doctorat en littérature arabe',0,NULL,1,1,NULL,8),(173,'MASTER',0,0,1,NULL,NULL,8),(174,'CEB',NULL,NULL,1,NULL,NULL,2),(175,'CESS',0,0,1,NULL,NULL,5),(176,'Master- Doctorat',NULL,NULL,1,NULL,NULL,8),(177,'Master ',0,0,1,NULL,NULL,8),(178,'CESS',1,1,1,NULL,NULL,5),(179,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',NULL,NULL,1,NULL,NULL,5),(180,'Ingénieur Mécanique',1,NULL,1,NULL,NULL,10),(181,'Pharmaceutique',0,0,1,1,NULL,8),(182,'Bachelier informatique',NULL,NULL,1,NULL,NULL,7),(183,'MASTER',0,0,1,NULL,NULL,8),(184,'CESS',1,NULL,1,NULL,NULL,5),(185,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(186,'CESS',0,NULL,1,NULL,NULL,5),(187,'CESS',0,NULL,1,NULL,NULL,5),(188,'Ingénieur',0,0,1,NULL,NULL,10),(189,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(190,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(191,'CESS',0,NULL,1,NULL,NULL,5),(192,'Baccalauréat',0,NULL,1,NULL,NULL,10),(193,'Bachelier en physique',0,NULL,1,NULL,NULL,7),(194,'Master ingénieur électrique et électromécanique',0,NULL,1,1,NULL,8),(195,'bachelier en économie et finances',NULL,NULL,1,NULL,NULL,7),(196,'CEB',NULL,NULL,1,NULL,NULL,2),(197,'Master',0,NULL,1,NULL,NULL,8),(198,'CESS',0,NULL,1,NULL,NULL,5),(199,'Bachelier en littérature anglaise',0,NULL,1,NULL,NULL,7),(200,'CEB',NULL,NULL,1,NULL,NULL,2),(201,'CEB',NULL,NULL,1,NULL,NULL,2),(202,'bachelier',NULL,NULL,1,NULL,NULL,7),(203,'CEB',0,0,1,NULL,NULL,2),(204,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(205,'Bachelier en littérature française',0,NULL,1,NULL,NULL,7),(206,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(207,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(208,'DOCTORAT EN MEDECINE',0,0,1,NULL,NULL,10),(209,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(210,'CESS',0,0,1,NULL,NULL,5),(211,'bachelier en industrie alimentaire',NULL,NULL,1,NULL,NULL,7),(212,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(213,'MASTER: ingénieur environnement',0,0,1,NULL,NULL,8),(214,'CESS',0,NULL,1,1,NULL,5),(215,'CESS',NULL,NULL,1,NULL,NULL,5),(216,'Bachelier Finance (4ans)',1,1,1,1,NULL,7),(217,'CESS',0,NULL,1,NULL,NULL,5),(218,'CESS',NULL,NULL,1,NULL,NULL,5),(219,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(220,'Bachelier en sciences informatiques',0,NULL,1,NULL,NULL,7),(221,'bachelier',1,NULL,1,NULL,NULL,7),(222,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(223,'Sans Diplôme',0,NULL,1,NULL,NULL,1),(224,'',NULL,NULL,1,NULL,NULL,1),(225,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(226,'Master en sciences',NULL,NULL,1,1,NULL,8),(227,'MASTER EN DROIT',0,0,1,NULL,NULL,8),(228,'Bachelier en langue anglaise',1,NULL,1,1,NULL,7),(229,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(230,'Bachelier en gestion d\'entreprise et gestion informatisée',0,NULL,1,1,NULL,7),(231,'bachelier',1,NULL,1,NULL,NULL,7),(232,'Médecin',0,0,1,1,NULL,8),(233,'CEB',0,NULL,1,NULL,NULL,2),(234,'Bachelier en chimie/physique (2ans)',NULL,NULL,1,1,NULL,7),(235,'CESS',0,0,1,NULL,NULL,5),(236,'CESS',0,NULL,1,1,NULL,5),(237,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(238,'BACHELIER',0,0,1,NULL,NULL,7),(239,'CESS',NULL,NULL,1,NULL,NULL,5),(240,'CESS',1,1,1,NULL,NULL,5),(241,'',NULL,NULL,1,NULL,NULL,1),(242,'CESS',NULL,NULL,1,NULL,NULL,5),(243,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(244,'CESS',NULL,NULL,1,NULL,NULL,5),(245,'Master en Biochimie',1,1,1,NULL,NULL,8),(246,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(247,'Master en électricité',NULL,NULL,1,1,NULL,8),(248,'Bachelier en physique',0,NULL,1,1,NULL,7),(249,'Master en littérature arabe',0,NULL,1,NULL,NULL,8),(250,'CESS',NULL,NULL,1,NULL,NULL,5),(251,'CESS',0,0,1,NULL,NULL,5),(252,'CESDD',0,NULL,1,NULL,NULL,4),(253,'Bachelier',0,NULL,1,NULL,NULL,7),(254,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(255,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(256,'Bachelier',0,NULL,1,NULL,NULL,7),(257,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(258,'master en management',0,0,1,NULL,NULL,8),(259,'Master',1,1,1,NULL,NULL,8),(260,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(261,'bachelier en chirurgie dentaire',1,1,1,NULL,NULL,7),(262,'Master Spécialisation Théatrale (réalisateur)',NULL,NULL,1,NULL,NULL,8),(263,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(264,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(265,'Bachelier/graduat en journalisme',NULL,NULL,1,NULL,NULL,7),(266,'Bachelier en économie',0,NULL,1,NULL,NULL,7),(267,'CESS',0,0,1,NULL,NULL,5),(268,'Enseignement Secondaire Complémentaire',0,NULL,1,NULL,NULL,6),(269,'CE1D',NULL,NULL,1,NULL,NULL,3),(270,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(271,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(272,'master en affaires',0,NULL,1,NULL,NULL,8),(273,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(274,'bachelier',NULL,NULL,1,NULL,NULL,7),(275,'Master EN INFORMATIQUE',1,1,1,NULL,NULL,8),(276,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(277,'CESS',0,NULL,1,NULL,NULL,5),(278,'CESS',1,1,1,NULL,NULL,5),(279,'Master en banque et finance',0,NULL,1,1,NULL,8),(280,'Bachelier en hotelerie',0,0,1,NULL,NULL,7),(281,'CE1D',0,NULL,1,NULL,NULL,3),(282,'Bachelier',0,NULL,1,NULL,NULL,7),(283,'CESDD',NULL,NULL,1,NULL,NULL,4),(284,'master',1,NULL,1,NULL,NULL,8),(285,'cess',0,NULL,1,1,NULL,5),(286,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(287,'CERTIFICAT D\'ETUDE DU PREMIER DEGRE',0,NULL,1,NULL,NULL,3),(288,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(289,'CESDD',0,NULL,1,NULL,NULL,4),(290,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(291,'master en administration',0,NULL,1,NULL,NULL,8),(292,'MASTER EN AGRONOMIE',0,0,1,NULL,NULL,8),(293,'CESS',0,NULL,1,1,NULL,5),(294,'CESS',0,NULL,1,NULL,NULL,5),(295,'Master vétérinaire',0,NULL,1,1,NULL,8),(296,'Niveau BAC + 2 ans infi ',NULL,NULL,1,1,NULL,5),(297,'CESS',0,0,1,1,NULL,5),(298,'enseignement supérieur',0,NULL,1,NULL,NULL,6),(299,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(300,'CESS',0,NULL,1,NULL,NULL,5),(301,'Master Science de l\'agriculture',0,0,1,NULL,NULL,8),(302,'CESS',0,0,1,NULL,NULL,5),(303,'CESS',0,NULL,1,1,NULL,5),(304,'MASTER',1,1,1,NULL,NULL,8),(305,'bachelier en management et commercial',0,NULL,1,NULL,NULL,7),(306,'Bachelier Ingénieur mécanique',1,1,1,1,NULL,7),(307,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(308,'CESS',NULL,NULL,1,NULL,NULL,5),(309,'MASTER',1,1,1,NULL,NULL,8),(310,'',NULL,NULL,1,NULL,NULL,6),(311,'bachelier',0,NULL,1,NULL,NULL,7),(312,'bachelier sage femme',0,0,1,1,NULL,7),(313,'CESS',NULL,NULL,1,NULL,NULL,5),(314,'CESS',0,0,1,NULL,NULL,5),(315,'CEB',NULL,NULL,1,NULL,NULL,2),(316,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(317,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(318,'Master audit et contrôle de gestion',0,NULL,1,1,NULL,8),(319,'Master en comptabilité',0,0,1,NULL,NULL,8),(320,'CESS',1,1,1,NULL,NULL,5),(321,'CESS',0,NULL,1,NULL,NULL,5),(322,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(323,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(324,'BACHELIER',0,0,1,NULL,NULL,7),(325,'CESS',0,NULL,1,NULL,NULL,5),(326,'sans diplôme',0,0,1,NULL,NULL,1),(327,'Bachelier en pharmacie',0,NULL,1,NULL,NULL,7),(328,'cess',1,NULL,1,1,NULL,5),(329,'CEB',NULL,NULL,1,NULL,NULL,2),(330,'Master en épidémiologie et santé publique (PAK) + Master en Sciences de l\'Environnement (France)',NULL,NULL,1,1,NULL,8),(331,'master',0,NULL,1,NULL,NULL,8),(332,'Bachelier Technologue en laboratoire',1,NULL,1,NULL,NULL,7),(333,'Bachelier',1,NULL,1,NULL,NULL,7),(334,'Bachelier',0,0,1,NULL,NULL,7),(335,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(336,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(337,'Master en langue anglaise',0,NULL,1,NULL,NULL,8),(338,'CESS',0,NULL,1,1,NULL,5),(339,'CESS',0,0,1,NULL,NULL,5),(340,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(341,'CESDD',0,NULL,1,1,NULL,4),(342,'bachelier',0,NULL,1,NULL,NULL,7),(343,'Master en aviation civil',NULL,NULL,1,NULL,NULL,8),(344,'DENTISTE',NULL,NULL,1,NULL,NULL,10),(345,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(346,'LICENCE en spécialisé en sciences vétérinaires tropicales',0,0,1,NULL,NULL,8),(347,'Doctorat en médecine',0,NULL,1,NULL,NULL,10),(348,'CE1D',0,0,1,NULL,NULL,3),(349,'CE1D',NULL,NULL,1,NULL,NULL,3),(350,'CESS',0,0,1,NULL,NULL,5),(351,'',NULL,NULL,1,1,NULL,1),(352,'Informatique',NULL,NULL,1,NULL,NULL,10),(353,'Master gestion d\'entreprise',0,NULL,1,NULL,NULL,8),(354,'',NULL,NULL,1,1,NULL,2),(355,'CEB',0,NULL,1,NULL,NULL,2),(356,'CESS technique (aide-comptable)',0,0,1,NULL,NULL,5),(357,'CEB',0,0,1,NULL,NULL,2),(358,'cess',0,NULL,1,1,NULL,5),(359,'cesdd',0,NULL,1,1,NULL,4),(360,'bachelier en commerce extérieur',NULL,NULL,1,NULL,NULL,7),(361,'bachelier en construction',NULL,NULL,1,NULL,NULL,7),(362,'CESS option science de la nature',0,0,1,NULL,NULL,5),(363,'MASTER',0,0,1,NULL,NULL,8),(364,'CESS',0,NULL,1,NULL,NULL,5),(365,'CE1D',NULL,NULL,1,NULL,NULL,3),(366,'Imagérie médicale',NULL,NULL,1,NULL,NULL,10),(367,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(368,'Master en droit',0,NULL,1,1,NULL,8),(369,'CESDD',0,NULL,1,NULL,NULL,4),(370,'CESS',1,NULL,1,NULL,NULL,5),(371,'MASTER EN DROIT',0,0,1,NULL,NULL,8),(372,'CEB',0,0,1,NULL,NULL,2),(373,'Master en droit',NULL,NULL,1,NULL,NULL,8),(374,'MASTER ',1,1,1,NULL,NULL,8),(375,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(376,'CESS',0,NULL,1,0,NULL,5),(377,'CESS',1,NULL,1,NULL,NULL,5),(378,'Master',1,NULL,1,NULL,NULL,8),(379,'Master en enseignement ',0,NULL,1,NULL,NULL,8),(380,'MASTER',0,0,1,NULL,NULL,8),(381,'Sans diplôme',0,NULL,1,NULL,NULL,1),(382,'CESS',NULL,NULL,1,NULL,NULL,5),(383,'CESS',1,1,1,NULL,NULL,5),(384,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(385,'Bachelier Commerce international',0,NULL,1,NULL,NULL,7),(386,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(387,'CESS',0,0,1,NULL,NULL,5),(388,'CESDD',0,0,1,NULL,NULL,4),(389,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(390,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(391,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(392,'master en design intérieur',NULL,NULL,1,NULL,NULL,8),(393,'BTS secrétaire de direction',0,NULL,1,1,NULL,6),(394,'CESS',0,NULL,1,1,NULL,5),(395,'BTS secrétariat de direction',1,NULL,1,1,NULL,6),(396,'',NULL,NULL,1,1,NULL,2),(397,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(398,'CESS',NULL,NULL,1,NULL,NULL,5),(399,'CESS',1,1,1,NULL,NULL,5),(400,'Master en logopédie',1,NULL,1,NULL,NULL,8),(401,'CESDD',NULL,NULL,1,NULL,NULL,4),(402,'CEB',0,NULL,1,1,NULL,2),(403,'CESDD',NULL,NULL,1,NULL,NULL,4),(404,'PROFESSEUR ARTS PLASTIQUES',1,NULL,1,NULL,NULL,10),(405,'',NULL,NULL,1,1,NULL,8),(406,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(407,'CESS',0,NULL,1,NULL,NULL,5),(408,'bachelier',1,1,1,NULL,NULL,7),(409,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(410,'Esthétique',NULL,NULL,1,NULL,NULL,10),(411,'CESS en lettre',1,NULL,1,1,NULL,5),(412,'master en économie',0,NULL,1,1,NULL,8),(413,'MASTER EN LITTÉRATURE',0,0,1,NULL,NULL,8),(414,'CESDD',0,NULL,1,NULL,NULL,4),(415,'licence en anglais',0,NULL,1,1,NULL,8),(416,'CESS',0,0,1,NULL,NULL,5),(417,'licence en droit français',0,NULL,1,NULL,NULL,10),(418,'certificat d\'études du premier degré de l\'enseignement secondaire ',0,NULL,1,NULL,NULL,3),(419,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(420,'licence assistante de direction ',0,0,1,NULL,NULL,10),(421,'Bachelier en sciences de la vie et de la terre',0,NULL,1,NULL,NULL,7),(422,'CESS',0,0,1,NULL,NULL,5),(423,'bachelier en histoire',1,1,1,NULL,NULL,7),(424,'CESDD',NULL,NULL,1,NULL,NULL,4),(425,'CESS',0,NULL,1,1,NULL,5),(426,'CERTIFICAT D\'ETUDE DU PREMIER DEGRE',0,NULL,1,NULL,NULL,3),(427,'BAC +2',0,NULL,1,1,NULL,7),(428,'Bachelier',0,NULL,1,NULL,NULL,7),(429,'CESS',NULL,NULL,1,NULL,NULL,5),(430,'CE1D',0,NULL,1,NULL,NULL,3),(431,'Master en littérature anglaise',0,0,1,NULL,NULL,8),(432,'BACHELIER',0,NULL,1,NULL,NULL,7),(433,'Master',0,NULL,1,NULL,NULL,8),(434,'CESS',0,0,1,NULL,NULL,5),(435,'CEB',NULL,NULL,1,NULL,NULL,2),(436,'licence en informatique',0,0,1,NULL,NULL,8),(437,'CEB',0,0,1,NULL,NULL,2),(438,'Master en Management',1,NULL,1,NULL,NULL,8),(439,'Master en Biologie',0,NULL,1,NULL,NULL,8),(440,'Bachelier en gestion socio-administrative',0,NULL,1,NULL,NULL,7),(441,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(442,'CESS',NULL,NULL,1,NULL,NULL,5),(443,'Formation professionnelle Technologue de laboratoire (2 ans)',0,NULL,1,NULL,NULL,10),(444,'MASTER',0,0,1,NULL,NULL,8),(445,'CESS',0,0,1,NULL,NULL,5),(446,'Bachelier en gestion',0,0,1,NULL,NULL,7),(447,'CESDD',NULL,NULL,1,NULL,NULL,4),(448,'MASTER EN CHIMIE FONDAMENTALE',0,0,1,NULL,NULL,8),(449,'Bachelier',NULL,NULL,1,NULL,NULL,7),(450,'CESS',0,NULL,1,NULL,NULL,5),(451,'Sciences de gestion',0,0,1,NULL,NULL,10),(452,'Master en gestion de l\'environnement',0,0,1,NULL,NULL,8),(453,'CESDD',NULL,NULL,1,NULL,NULL,4),(454,'CESS',0,NULL,1,NULL,NULL,5),(455,'licence en sciences et gestion',0,0,1,1,NULL,8),(456,'',NULL,NULL,1,1,NULL,5),(457,'CESDD',0,NULL,1,NULL,NULL,4),(458,'master en commerce internationale',NULL,NULL,1,1,NULL,8),(459,'CEB',NULL,NULL,1,NULL,NULL,2),(460,'CESS',NULL,NULL,1,1,NULL,5),(461,'bACHELIER',0,0,1,NULL,NULL,7),(462,'bachelier',1,1,1,NULL,NULL,7),(463,'LICENCE en science économique et gestion ',0,0,1,NULL,NULL,8),(464,'Sans diplôme',0,NULL,1,NULL,NULL,1),(465,'MASTER EN COMMUNICATION',1,1,1,NULL,NULL,8),(466,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(467,'Bachelier Marketing',0,0,1,NULL,NULL,7),(468,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(469,'BACHELIER',1,1,1,NULL,NULL,7),(470,'CERTIFICAT D\'ETUDE DU PREMIER DEGRE',0,NULL,1,NULL,NULL,3),(471,'CESS',0,NULL,1,NULL,NULL,5),(472,'CESS',0,NULL,1,1,NULL,5),(473,'Bachelier Economie familiale et sociale ',NULL,NULL,1,NULL,NULL,7),(474,'CESS',0,0,1,NULL,NULL,5),(475,'CESS',0,0,1,NULL,NULL,5),(476,'CESS',0,NULL,1,NULL,NULL,5),(477,'CESS',0,NULL,1,NULL,NULL,5),(478,'CESS',1,NULL,1,1,NULL,5),(479,'Informatique ',1,1,1,NULL,NULL,10),(480,'Relations internationales/géographiques',NULL,NULL,1,1,NULL,8),(481,'Master',0,0,1,NULL,NULL,8),(482,'LICENCE',0,0,1,NULL,NULL,10),(483,'Bachelier ',0,0,1,NULL,NULL,7),(484,'Master en anthropologie',0,NULL,1,NULL,NULL,8),(485,'CESS',0,0,1,NULL,NULL,5),(486,'licence en pholologie angue étrangère',0,NULL,1,0,NULL,8),(487,'CESS',0,NULL,1,1,NULL,5),(488,'Bachelier en publicité',NULL,NULL,1,NULL,NULL,7),(489,'Bachelier en droit',0,NULL,1,NULL,NULL,7),(490,'CESS professionnel',0,0,1,NULL,NULL,5),(491,'CESS',0,0,1,NULL,NULL,5),(492,'Sans Diplôme',NULL,NULL,1,NULL,NULL,1),(493,'Bachelier',NULL,NULL,1,NULL,NULL,7),(494,'CEB',0,NULL,1,NULL,NULL,2),(495,'CESDD',0,0,1,NULL,NULL,4),(496,'CEB',0,NULL,1,NULL,NULL,2),(497,'sans diplôme',0,NULL,1,NULL,NULL,1),(498,'Ens. Sec. Compl. Aide-soignante',NULL,NULL,1,NULL,NULL,6),(499,'',NULL,NULL,1,1,NULL,5),(500,'CESS',0,NULL,1,NULL,NULL,5),(501,'MASTER EN ELECTRONIQUE',0,NULL,1,NULL,NULL,8),(502,'CESS',NULL,NULL,1,NULL,NULL,5),(503,'master en journalisme',NULL,NULL,1,NULL,NULL,8),(504,'Bachelier en compataébilité',0,NULL,1,NULL,NULL,7),(505,'juriste avocat',0,NULL,1,1,NULL,8),(506,'Master Sciences Sociales',0,0,1,NULL,NULL,8),(507,'CESS',NULL,NULL,1,NULL,NULL,5),(508,'CESDD',0,NULL,1,NULL,NULL,4),(509,'Master en Droit',NULL,NULL,1,NULL,NULL,8),(510,'Master en Art',1,0,1,NULL,NULL,8),(511,'CEB',NULL,NULL,1,NULL,NULL,2),(512,'Bachelier ',0,0,1,NULL,NULL,7),(513,'bachelier',NULL,NULL,1,NULL,NULL,7),(514,'BAC + 2 Marketing',NULL,NULL,1,1,NULL,6),(515,'Bachelier Assistant Ingénieur électronique',1,NULL,1,NULL,NULL,7),(516,'Master en arts visuels, plastiques et de l\'espace',0,NULL,1,NULL,NULL,8),(517,'CEB',NULL,NULL,1,NULL,NULL,2),(518,'Master en Communication électronique',0,NULL,1,NULL,NULL,8),(519,'CESS',NULL,NULL,1,NULL,NULL,5),(520,'CESS',0,0,1,NULL,NULL,5),(521,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(522,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(523,'BACHELIER',0,0,1,NULL,NULL,7),(524,'CESS',0,0,1,NULL,NULL,5),(525,'Master en économie',0,NULL,1,NULL,NULL,8),(526,'CESDD',NULL,NULL,1,NULL,NULL,4),(527,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(528,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(529,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(530,'Master en Sciences de  Gestion',1,1,1,NULL,NULL,8),(531,'CESS',NULL,NULL,1,NULL,NULL,5),(532,'BACHELIER',0,0,1,NULL,NULL,7),(533,'Bachelier enseignant primaire',0,0,1,NULL,NULL,7),(534,'master en administration',0,NULL,1,NULL,NULL,8),(535,'Bachelier en marketing',NULL,NULL,1,NULL,NULL,7),(536,'Bachelier Ingénieur biomédical',0,NULL,1,NULL,NULL,7),(537,'Bachelier soins infirmiers',0,NULL,1,1,NULL,7),(538,'Bachelier en Topographie ',NULL,NULL,1,1,NULL,7),(539,'Bachelier en relations internationales',0,NULL,1,1,NULL,7),(540,'Langue et Littérature Roumaine',0,0,1,NULL,NULL,10),(541,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(542,'CESS',NULL,NULL,1,NULL,NULL,5),(543,'bachelier d\'enseignante',0,NULL,1,NULL,NULL,7),(544,'Bachelier - infirmière',0,NULL,1,NULL,NULL,7),(545,'CESS',NULL,NULL,1,NULL,NULL,5),(546,'Gastronomie et Hotellerie',1,NULL,1,NULL,NULL,10),(547,'CESS',NULL,NULL,1,NULL,NULL,5),(548,'CEB',0,NULL,1,NULL,NULL,2),(549,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(550,'Licence en Economie',NULL,NULL,1,NULL,NULL,10),(551,' industruel',0,NULL,1,1,NULL,8),(552,'CESS',1,NULL,1,NULL,NULL,5),(553,'Sans diplôme',0,NULL,1,NULL,NULL,1),(554,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(555,'',NULL,NULL,1,1,NULL,5),(556,'Bachelier infirmière',0,NULL,1,NULL,NULL,7),(557,'CESS',0,0,1,NULL,NULL,5),(558,'Master en Comptabilité et Contrôle',1,NULL,1,NULL,NULL,8),(559,'CESS',NULL,NULL,1,NULL,NULL,5),(560,'sans diplôme',0,NULL,1,NULL,NULL,1),(561,'Master en Economie du Travail ',1,1,1,NULL,NULL,8),(562,'CESS',NULL,NULL,1,NULL,NULL,5),(563,'bachelier en génie mécanique',1,NULL,1,NULL,NULL,7),(564,'BACHELIER',0,0,1,NULL,NULL,7),(565,'CESS',NULL,NULL,1,0,NULL,5),(566,'CESDD',0,NULL,1,NULL,NULL,4),(567,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(568,'Master en Gestion',NULL,NULL,1,1,NULL,8),(569,'Bachelier gestion informatique',NULL,NULL,1,NULL,NULL,7),(570,'CESS',0,0,1,NULL,NULL,5),(571,'CESDD',NULL,NULL,1,NULL,NULL,4),(572,'CEB',NULL,NULL,1,NULL,NULL,2),(573,'Sans diplôme',0,NULL,1,NULL,NULL,1),(574,'Technicienne de laboratoire biomédica',0,NULL,1,1,NULL,8),(575,'Sans Diplôme',0,NULL,1,NULL,NULL,1),(576,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(577,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(578,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(579,'Bachelier en sociologie de l\'environnement',0,NULL,1,1,NULL,7),(580,'CESS',NULL,NULL,1,NULL,NULL,5),(581,'licence en économie',0,NULL,1,1,NULL,8),(582,'Sans Diplôme',0,0,1,NULL,NULL,1),(583,'licence en droit des affaires',NULL,NULL,1,1,NULL,7),(584,'CE1D',NULL,NULL,1,NULL,NULL,3),(585,'MASTER',0,0,1,NULL,NULL,8),(586,'Master Comptabilité',1,0,1,NULL,NULL,8),(587,'Master en Banque Assurance',NULL,NULL,1,NULL,NULL,8),(588,'BACHELIER EN DROIT',0,NULL,1,NULL,NULL,7),(589,'Master en Ingénierie, technique de laboratoire biologie',0,NULL,1,NULL,NULL,8),(590,'Master Rel Int',NULL,NULL,1,NULL,NULL,8),(591,'',NULL,NULL,1,NULL,NULL,5),(592,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(593,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(594,'CESS',NULL,NULL,1,NULL,NULL,5),(595,'Master en langue modernes',0,0,1,NULL,NULL,8),(596,'Master en logopédie',0,NULL,1,NULL,NULL,8),(597,'Master Ingénieur ',1,1,1,NULL,NULL,8),(598,'MASTER',0,0,1,NULL,NULL,8),(599,'Bachelier en géographie',NULL,NULL,1,NULL,NULL,7),(600,'',NULL,NULL,1,NULL,NULL,5),(601,'doctorat en médécine',0,0,1,1,NULL,8),(602,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(603,'CESDD',0,0,1,NULL,NULL,4),(604,'CEB',0,0,1,NULL,NULL,2),(605,'CESDD',0,NULL,1,1,NULL,4),(606,'Administration économique et société',0,NULL,1,NULL,NULL,10),(607,'CESDD',0,NULL,1,NULL,NULL,4),(608,'CESS',NULL,NULL,1,1,NULL,5),(609,'Bachelier en communication',NULL,NULL,1,1,NULL,7),(610,'MASTER',1,0,1,NULL,NULL,8),(611,'Certificat d\'enseignement secondaire du supérieur',0,NULL,1,1,NULL,5),(612,'BACHELIER ',0,NULL,1,NULL,NULL,7),(613,'MASTER EN DROIT',0,NULL,1,NULL,NULL,8),(614,'CESS',NULL,NULL,1,NULL,NULL,5),(615,'',0,NULL,1,NULL,NULL,4),(616,'Ingénieur mécanique',1,1,1,1,NULL,8),(617,'Master en Finances',0,NULL,1,NULL,NULL,8),(618,'Master en banque ',NULL,NULL,1,NULL,NULL,8),(619,'CERTIFICAT D\'ETUDE DU PREMIER DEGRE',0,NULL,1,NULL,NULL,3),(620,'MASTER',0,0,1,NULL,NULL,8),(621,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(622,'Master en comptabilité et finances',0,NULL,1,NULL,NULL,8),(623,'CESS',NULL,NULL,1,NULL,NULL,5),(624,'Master en Nimatologie',NULL,NULL,1,NULL,NULL,8),(625,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(626,'certificat d\'enseignement secondaire du deuxiéme degré',NULL,NULL,1,1,NULL,4),(627,'CESS',0,0,1,NULL,NULL,5),(628,'Master en génie électrique',0,NULL,1,NULL,NULL,8),(629,'CESDD',NULL,NULL,1,NULL,NULL,4),(630,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(631,'Sans Diplôme',0,NULL,1,NULL,NULL,1),(632,'CESDD',0,NULL,1,NULL,NULL,4),(633,'CEB',NULL,NULL,1,NULL,NULL,2),(634,'Bachelier en Commerce ',NULL,NULL,1,NULL,NULL,7),(635,'master',0,0,1,NULL,NULL,8),(636,'CESS',0,0,1,NULL,NULL,5),(637,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(638,'Master en éducation',NULL,NULL,1,1,NULL,8),(639,'CESDD',0,NULL,1,1,NULL,4),(640,'MASTER: psychologie',0,0,1,NULL,NULL,8),(641,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(642,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(643,'DESSINATEUR EN BATIMENT',1,NULL,1,NULL,NULL,10),(644,'Arts du spectacle ',0,NULL,1,NULL,NULL,10),(645,'master en économie',1,0,1,1,NULL,8),(646,'BACHELIER EN INFORMATIQUE',0,0,1,NULL,NULL,7),(647,'bachelier en éducateur',0,NULL,1,1,NULL,7),(648,'CEB',0,NULL,1,1,NULL,2),(649,'CESDD',0,0,1,NULL,NULL,4),(650,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(651,'CESS',NULL,NULL,1,NULL,NULL,5),(652,'Bachelier gestion d\'entreprise',0,NULL,1,NULL,NULL,7),(653,'BACHELIER EN COMPTABILITE',0,0,1,NULL,NULL,7),(654,'',NULL,NULL,1,NULL,NULL,2),(655,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(656,'CESS',0,0,1,NULL,NULL,5),(657,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(658,'CESS',NULL,NULL,1,NULL,NULL,5),(659,'CESDD',NULL,NULL,1,NULL,NULL,4),(660,'CESS Technique secrétariat',NULL,NULL,1,1,NULL,5),(661,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(662,'bachelier en sciences juridiques et économiques',0,NULL,1,NULL,NULL,7),(663,'Coiffure',0,NULL,1,NULL,NULL,10),(664,'CE1D',NULL,NULL,1,NULL,NULL,3),(665,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(666,'master',NULL,NULL,1,NULL,NULL,8),(667,'CESS Sciences',1,NULL,1,NULL,NULL,5),(668,'BACHELIER INFIRMIER',0,0,1,NULL,NULL,7),(669,'BACHELIER ',0,NULL,1,NULL,NULL,7),(670,'CESDD',0,NULL,1,NULL,NULL,4),(671,'licence en littérature ',0,NULL,1,1,NULL,8),(672,'Enseignement complémentaire',0,0,1,NULL,NULL,6),(673,'bachelier',NULL,NULL,1,1,NULL,7),(674,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(675,'Sans diplôme',0,NULL,1,NULL,NULL,1),(676,'bachelier',NULL,NULL,1,NULL,NULL,7),(677,'Master en administration économique et sociale',0,NULL,1,NULL,NULL,8),(678,'CESS',0,NULL,1,1,NULL,5),(679,'niveau BAC non terminé',0,NULL,1,NULL,NULL,10),(680,'Master en tourisme',0,NULL,1,NULL,NULL,8),(681,'CESS',NULL,NULL,1,NULL,NULL,5),(682,'MASTER EN FINANCE',0,0,1,NULL,NULL,8),(683,'MASTER EN SCIENCES POLITIQUE',1,0,1,NULL,NULL,8),(684,'master',NULL,NULL,1,NULL,NULL,8),(685,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(686,'CESS',1,1,1,NULL,NULL,5),(687,'Master en médecine',0,NULL,1,1,NULL,8),(688,'CESS',0,NULL,1,NULL,NULL,5),(689,'CESS',0,0,1,NULL,NULL,5),(690,'bachelier en droit et relations internationales',1,NULL,1,NULL,NULL,7),(691,'MASTER',0,0,1,NULL,NULL,8),(692,'Bachelier Ingénieur aéro-spatial',0,NULL,1,NULL,NULL,7),(693,'',NULL,NULL,1,1,NULL,1),(694,'Sans diplôme',NULL,NULL,1,1,NULL,1),(695,'CESDD',NULL,NULL,1,1,NULL,4),(696,'CESS',NULL,NULL,1,NULL,NULL,5),(697,'CESS Technique',NULL,NULL,1,NULL,NULL,5),(698,'Master physique électronique',1,0,1,NULL,NULL,8),(699,'Master en chirurgie',0,0,1,NULL,NULL,8),(700,'master en psychologie',0,NULL,1,NULL,NULL,8),(701,'Economie',0,NULL,1,NULL,NULL,10),(702,'topographie',0,NULL,1,0,NULL,5),(703,'CE1D',NULL,NULL,1,NULL,NULL,3),(704,'bachelier',NULL,NULL,1,NULL,NULL,7),(705,'Master en droit immobilier',0,NULL,1,NULL,NULL,8),(706,'CESS',0,NULL,1,NULL,NULL,5),(707,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(708,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(709,'CESS',0,0,1,NULL,NULL,5),(710,'CE1D',0,0,1,NULL,NULL,3),(711,'bachelier',0,NULL,1,NULL,NULL,7),(712,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(713,'CESDD',0,NULL,1,NULL,NULL,4),(714,'CESS',0,NULL,1,NULL,NULL,5),(715,'BACHELIER',0,0,1,NULL,NULL,7),(716,'Sans diplôme',0,NULL,1,NULL,NULL,1),(717,'CESDD',0,NULL,1,NULL,NULL,4),(718,'licence en sciences politiques',0,0,1,1,NULL,8),(719,'licence en Droit',0,NULL,1,1,NULL,8),(720,'Master en Business Administration',NULL,NULL,1,1,NULL,8),(721,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(722,'CESS',NULL,NULL,1,NULL,NULL,5),(723,'',NULL,NULL,1,0,NULL,2),(724,'CESS',NULL,NULL,1,NULL,NULL,5),(725,'cess',0,NULL,1,1,NULL,5),(726,'Comptabilité de gestion',1,1,1,1,NULL,8),(727,'Baccalauréat ',0,NULL,1,NULL,NULL,10),(728,'CESS',NULL,NULL,1,NULL,NULL,5),(729,'BACHELIER ',0,NULL,1,NULL,NULL,7),(730,'BACHELIER',0,0,1,NULL,NULL,7),(731,'certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,1,NULL,4),(732,'certificat d\'enseignement secondaire supérieur',0,NULL,1,NULL,NULL,5),(733,'Master en economie et développement',0,0,1,NULL,NULL,8),(734,'CE1D',NULL,NULL,1,NULL,NULL,3),(735,'bachelier',NULL,NULL,1,NULL,NULL,7),(736,'Bachelier Institutrice primaire',0,NULL,1,1,NULL,7),(737,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(738,'',NULL,NULL,1,1,NULL,2),(739,'MASTER EN DROIT',0,0,1,NULL,NULL,8),(740,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(741,'CESS',0,NULL,1,NULL,NULL,5),(742,'infirmière',1,0,1,1,NULL,5),(743,'master',0,NULL,1,NULL,NULL,8),(744,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(745,'militaire',NULL,NULL,1,1,NULL,7),(746,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(747,'',NULL,NULL,1,1,NULL,4),(748,'certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,1,NULL,4),(749,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(750,'Master en médecine',0,NULL,1,1,NULL,8),(751,'MASTER EN LITTÉRATURE ',0,0,1,NULL,NULL,8),(752,'CEB',0,NULL,1,1,NULL,2),(753,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(754,'CESS',0,NULL,1,NULL,NULL,5),(755,'Sans diplôme',0,NULL,1,NULL,NULL,1),(756,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(757,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(758,'Bachelier Institutrice',1,NULL,1,NULL,NULL,7),(759,'Diplôme en sciences à orientation informatique',0,NULL,1,1,NULL,7),(760,'Certificat d\'enseignement secondaire du supérieur',0,0,1,1,NULL,5),(761,'CESDD',NULL,NULL,1,NULL,NULL,4),(762,'CESS',0,NULL,1,NULL,NULL,5),(763,'BACHELIER EN SOCIOLOGIE',0,0,1,NULL,NULL,7),(764,'Bioingénieur des Sciences et Technologie environementale',1,1,1,NULL,NULL,10),(765,'CEB',0,NULL,1,NULL,NULL,2),(766,'CESS',0,0,1,NULL,NULL,5),(767,'master en commerce international',0,0,1,1,NULL,8),(768,'CESS',0,NULL,1,1,NULL,5),(769,'BACHELIER EN PHARMACIE',0,0,1,NULL,NULL,7),(770,'Certificat d\'enseignement secondaire du deuxiéme degré',0,0,1,NULL,NULL,4),(771,'Bachelier Assistante de Direction',1,0,1,NULL,NULL,7),(772,'Aide mécanicien',NULL,NULL,1,NULL,NULL,10),(773,'Master en logopédie',0,0,1,NULL,NULL,8),(774,'sans diplôme',0,NULL,1,NULL,NULL,1),(775,'master en tourisme',0,0,1,1,NULL,8),(776,'CE1D',NULL,NULL,1,NULL,NULL,3),(777,'',NULL,NULL,1,1,NULL,2),(778,'CESDD',0,NULL,1,NULL,NULL,4),(779,'bachelier',1,NULL,1,NULL,NULL,7),(780,'MASTER',0,0,1,NULL,NULL,8),(781,'Médecin',0,NULL,1,NULL,NULL,10),(782,'bachelier en communication des entreprises',1,NULL,1,NULL,NULL,7),(783,'Histoire et géographie',0,NULL,1,NULL,NULL,10),(784,'CE1D',0,NULL,1,NULL,NULL,3),(785,'MASTER EN SCIENCES SOCIALES',0,0,1,NULL,NULL,8),(786,'CE1D',0,0,1,NULL,NULL,3),(787,'BACHELIER EN SECRETARIAT',0,0,1,NULL,NULL,7),(788,'Bachelier pharmaceutique',0,0,1,NULL,NULL,7),(789,'master',NULL,NULL,1,NULL,NULL,8),(790,'CESS',0,NULL,1,1,NULL,5),(791,'CESS Technique Professionnel Cuisine',0,0,1,1,NULL,5),(792,'MASTER EN PSYCHOLOGIE',0,0,1,NULL,NULL,8),(793,'CEB',NULL,NULL,1,NULL,NULL,2),(794,'CESDD',0,0,1,NULL,NULL,4),(795,'certificat d\'études de base',0,NULL,1,1,NULL,2),(796,'certificat d\'études de base',NULL,NULL,1,1,NULL,2),(797,'Master en Littérature Française',NULL,NULL,1,NULL,NULL,8),(798,'CESI',0,NULL,1,NULL,NULL,10),(799,'',NULL,NULL,1,1,NULL,2),(800,'CESDD',0,NULL,1,NULL,NULL,4),(801,'Graduat Technicien spécialisé en fabrication mécanique',0,NULL,1,NULL,NULL,7),(802,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(803,'Master en chimie',0,NULL,1,NULL,NULL,8),(804,'Bachelier infirmière',NULL,NULL,1,NULL,NULL,7),(805,'CESS',0,NULL,1,NULL,NULL,5),(806,'CE1D',0,0,1,NULL,NULL,3),(807,'Bavhelier en langue anglaise',1,NULL,1,1,NULL,7),(808,'CESDD',NULL,NULL,1,NULL,NULL,4),(809,'CESS',NULL,NULL,1,NULL,NULL,5),(810,'',NULL,NULL,1,NULL,NULL,1),(811,'Sciences économiques et administratives',0,NULL,1,NULL,NULL,10),(812,'CE1D',1,NULL,1,NULL,NULL,3),(813,'Bachelier Littérature',1,1,1,NULL,NULL,7),(814,'DOCTORAT EN MEDECINE',0,0,1,NULL,NULL,10),(815,'CESDD',NULL,NULL,1,NULL,NULL,4),(816,'Bachelier en sciences économiques',1,NULL,1,NULL,NULL,7),(817,'Bachelier en Economie',NULL,NULL,1,1,NULL,7),(818,'Sans diplôme',0,NULL,1,NULL,NULL,1),(819,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(820,'Master en génie civil',0,0,1,NULL,NULL,8),(821,'CESS',0,0,1,1,NULL,5),(822,'CESS',0,0,1,NULL,NULL,5),(823,'Master',0,NULL,1,NULL,NULL,8),(824,'Doctorat en Médecine',NULL,NULL,1,NULL,NULL,10),(825,'Bachelier langue',0,0,1,NULL,NULL,7),(826,'Sans Diplôme',0,0,1,NULL,NULL,1),(827,'',NULL,NULL,1,1,NULL,4),(828,'master',0,NULL,1,NULL,NULL,8),(829,'cess artistique',0,NULL,1,1,NULL,5),(830,'CESS',NULL,NULL,1,NULL,NULL,5),(831,'CESS',NULL,NULL,1,NULL,NULL,5),(832,'CESS infirmier',0,0,1,NULL,NULL,5),(833,'bachelier en gestion de ressouce humaine',0,NULL,1,1,NULL,7),(834,'certificat d\'enseignement secondaire supérieur',0,NULL,1,NULL,NULL,5),(835,'CE1D',0,0,1,NULL,NULL,3),(836,'CESS',0,NULL,1,NULL,NULL,5),(837,'Master en sciences alimentaire',0,0,1,NULL,NULL,8),(838,'Littérature Anglaise',NULL,NULL,1,NULL,NULL,10),(839,'CESS',0,0,1,NULL,NULL,5),(840,'CEB',0,NULL,1,NULL,NULL,2),(841,'Bachelier DROIT',NULL,NULL,1,NULL,NULL,7),(842,'CESS',0,0,1,NULL,NULL,5),(843,'BACHELIER',1,NULL,1,NULL,NULL,7),(844,'CESS',NULL,NULL,1,NULL,NULL,5),(845,'BACHELIER EN HOTELERIE',0,0,1,NULL,NULL,7),(846,'MASTER',0,0,1,NULL,NULL,8),(847,'master',0,NULL,1,NULL,NULL,8),(848,'CESS',0,0,1,NULL,NULL,5),(849,'CESDD',0,NULL,1,NULL,NULL,4),(850,'CESDD',NULL,NULL,1,NULL,NULL,4),(851,'bachelier',0,NULL,1,NULL,NULL,7),(852,'Master en sciences pharmacologiques',0,NULL,1,NULL,NULL,8),(853,'Master',0,0,1,NULL,NULL,8),(854,'enseignement supérieur',NULL,NULL,1,NULL,NULL,6),(855,'prothésiste dentaire',0,0,1,1,NULL,7),(856,'CESS',0,NULL,1,NULL,NULL,5),(857,'bachelier en gestion de ressouce humaine',0,NULL,1,1,NULL,7),(858,'Master Direction d\'Entreprise',NULL,NULL,1,1,NULL,8),(859,'CESS',0,NULL,1,NULL,NULL,5),(860,'bachelier',NULL,NULL,1,NULL,NULL,7),(861,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(862,'bachelier en gestion',0,0,1,NULL,NULL,7),(863,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(864,'bachelier',0,NULL,1,NULL,NULL,7),(865,'CESS',1,1,1,NULL,NULL,5),(866,'CESS',0,NULL,1,NULL,NULL,5),(867,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(868,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(869,'',NULL,NULL,1,1,NULL,4),(870,'master en chimie',NULL,NULL,1,1,NULL,8),(871,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(872,'CEB',NULL,NULL,1,NULL,NULL,2),(873,'CEB',NULL,NULL,1,NULL,NULL,2),(874,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(875,'CEB',NULL,NULL,1,NULL,NULL,2),(876,'CESS littéraire',1,NULL,1,NULL,NULL,5),(877,'CESS',0,0,1,NULL,NULL,5),(878,'CESS',0,0,1,NULL,NULL,5),(879,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(880,'Bachelier',0,NULL,1,NULL,NULL,7),(881,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(882,'Bachelier en santé publique',0,NULL,1,1,NULL,7),(883,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(884,'bachelier d\'infirmière',NULL,NULL,1,NULL,NULL,7),(885,'CESDD',0,0,1,NULL,NULL,4),(886,'',NULL,NULL,1,1,NULL,2),(887,'Bachelier en gestion et finance',0,NULL,1,1,NULL,7),(888,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(889,'CE1D',NULL,NULL,1,NULL,NULL,3),(890,'Master en informatique',0,0,1,NULL,NULL,8),(891,'Bachelier sociologie',0,0,1,NULL,NULL,7),(892,'Bachelier en Conservation du Patrimoine Culturel (4ans)',0,0,1,0,NULL,7),(893,'Master en Economie',NULL,NULL,1,NULL,NULL,8),(894,'CESS',1,1,1,NULL,NULL,5),(895,'LICENCE EN TOURISME',0,0,1,NULL,NULL,8),(896,'CESDD',0,NULL,1,NULL,NULL,4),(897,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(898,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(899,'CESS',0,NULL,1,NULL,NULL,5),(900,'Langue et littérature arabe',1,NULL,1,NULL,NULL,10),(901,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(902,'CESS',0,NULL,1,NULL,NULL,5),(903,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(904,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(905,'CESDD',0,NULL,1,NULL,NULL,4),(906,'CESS',0,0,1,NULL,NULL,5),(907,'',0,NULL,1,1,NULL,7),(908,'Bachelier en Pharmacie',NULL,NULL,1,1,NULL,7),(909,'Licencié en Economie : Commerce et Banque',0,0,1,NULL,NULL,8),(910,'Sans Diplôme',0,0,1,NULL,NULL,1),(911,'CESS',NULL,NULL,1,NULL,NULL,5),(912,'Bachelier en comptabilité et finance',0,NULL,1,NULL,NULL,7),(913,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(914,'BACHELIER EN JOURNALISME',0,0,1,NULL,NULL,7),(915,'CESS',NULL,NULL,1,NULL,NULL,5),(916,'CERTIFICAT D\'ETUDE DU PREMIER DEGRE',0,NULL,1,NULL,NULL,3),(917,'CEB',NULL,NULL,1,1,NULL,2),(918,'CESS',NULL,NULL,1,NULL,NULL,5),(919,'Master en Médecine Générale',NULL,NULL,1,NULL,NULL,8),(920,'CESS',NULL,NULL,1,NULL,NULL,5),(921,'licence en infirmière en laboratoire',1,1,1,1,NULL,7),(922,'Master Littérature française',NULL,NULL,1,NULL,NULL,8),(923,'BACHELIER EN STATISTIQUE',0,0,1,NULL,NULL,7),(924,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(925,'CE1D',0,NULL,1,NULL,NULL,3),(926,'CESS',0,NULL,1,NULL,NULL,5),(927,'MASTER EN PSYCHOLOGIE',0,0,1,NULL,NULL,8),(928,'CESS',0,NULL,1,NULL,NULL,5),(929,'MASTER',0,0,1,NULL,NULL,8),(930,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(931,'Certificat d\'enseignement secondaire du supérieur',0,NULL,1,0,NULL,5),(932,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(933,'CESS',NULL,NULL,1,NULL,NULL,5),(934,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(935,'CESS',NULL,NULL,1,NULL,NULL,5),(936,'Master en Agronomie',0,NULL,1,NULL,NULL,8),(937,'BACHELIER EN DROIT',0,0,1,NULL,NULL,7),(938,'',NULL,NULL,1,NULL,NULL,4),(939,'CE1D',0,0,1,NULL,NULL,3),(940,'CESDD',0,NULL,1,NULL,NULL,4),(941,'CEB',0,0,1,NULL,NULL,2),(942,'CESS',NULL,NULL,1,NULL,NULL,5),(943,'CESDD',0,NULL,1,1,NULL,4),(944,'MASTER',0,0,1,NULL,NULL,8),(945,'certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,1,NULL,4),(946,'Bachelier en design',0,NULL,1,1,NULL,7),(947,'licence en Droit',0,NULL,1,1,NULL,8),(948,'CESDD',0,NULL,1,1,NULL,4),(949,'CESS',NULL,NULL,1,NULL,NULL,5),(950,'CESS',0,0,1,NULL,NULL,5),(951,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(952,'CESDD',NULL,NULL,1,NULL,NULL,4),(953,'CESS',0,0,1,NULL,NULL,5),(954,'CESI',0,NULL,1,NULL,NULL,10),(955,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(956,'master en sciences de gestion',NULL,NULL,1,NULL,NULL,8),(957,'CESS',0,0,1,NULL,NULL,5),(958,'CE1D',0,NULL,1,NULL,NULL,3),(959,'MASTER',0,0,1,NULL,NULL,8),(960,'master en information et gestion',0,NULL,1,NULL,NULL,8),(961,'BTS  en marketing',0,NULL,1,1,NULL,5),(962,'Bachelier Relation Publique',NULL,NULL,1,NULL,NULL,7),(963,'Licence en nseignement de l\'informatique et des TIC',1,NULL,1,NULL,NULL,8),(964,'CESS',0,NULL,1,1,NULL,5),(965,'bachelier',NULL,NULL,1,NULL,NULL,7),(966,'Bachelier en comptabilité',NULL,NULL,1,NULL,NULL,7),(967,'CESS',0,0,1,NULL,NULL,5),(968,'Master en médecine',NULL,NULL,1,NULL,NULL,8),(969,'MASTER EN ELECTRONIQUE',0,0,1,NULL,NULL,8),(970,'CEB',NULL,NULL,1,NULL,NULL,2),(971,'Master en Informatique',NULL,NULL,1,1,NULL,8),(972,'CESS',NULL,NULL,1,NULL,NULL,5),(973,'CESS',NULL,NULL,1,NULL,NULL,5),(974,'CEB',0,NULL,1,NULL,NULL,2),(975,'CESDD',0,NULL,1,1,NULL,4),(976,'CESS',NULL,NULL,1,NULL,NULL,5),(977,'CESS',0,NULL,1,NULL,NULL,5),(978,'CEB',0,NULL,1,NULL,NULL,2),(979,'Bachelier en Sc Pol',NULL,NULL,1,NULL,NULL,7),(980,'CESS',NULL,NULL,1,NULL,NULL,5),(981,'CESS professionnel',NULL,NULL,1,NULL,NULL,5),(982,'Master en informatique',0,0,1,NULL,NULL,8),(983,'master en architecture',0,0,1,1,NULL,8),(984,'master-doctorat',1,1,1,NULL,NULL,8),(985,'CESS',NULL,NULL,1,NULL,NULL,5),(986,'Bachelier',0,NULL,1,NULL,NULL,7),(987,'CESS',1,NULL,1,NULL,NULL,5),(988,'Master',0,NULL,1,NULL,NULL,8),(989,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(990,'Bachelier Relation Internationale',0,0,1,1,NULL,7),(991,'CESDD',0,NULL,1,NULL,NULL,4),(992,'certificat d\'études de base',0,NULL,1,1,NULL,2),(993,'CESS',0,0,1,NULL,NULL,5),(994,'',1,1,1,1,NULL,5),(995,'CESDD',NULL,NULL,1,1,NULL,4),(996,'CESS',0,0,1,NULL,NULL,5),(997,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(998,'',NULL,NULL,1,NULL,NULL,1),(999,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1000,'Master en dentisterie chirurgicale',0,NULL,1,1,NULL,8),(1001,'bachelier',0,NULL,1,NULL,NULL,7),(1002,'CEB',0,0,1,NULL,NULL,2),(1003,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1004,'CESS',0,NULL,1,NULL,NULL,5),(1005,'SANS DIPLÔME',0,0,1,NULL,NULL,1),(1006,'Bachelier',1,1,1,NULL,NULL,7),(1007,'CE1D',NULL,NULL,1,NULL,NULL,3),(1008,'CESS',0,NULL,1,NULL,NULL,5),(1009,'CEB',NULL,NULL,1,NULL,NULL,2),(1010,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1011,'Certificat d\'enseignement secondaire du supérieur',0,0,1,1,NULL,5),(1012,'CESDD',0,0,1,NULL,NULL,4),(1013,'CESS',NULL,NULL,1,NULL,NULL,5),(1014,'CEB',NULL,NULL,1,1,NULL,2),(1015,'Master en économie',0,NULL,1,1,NULL,8),(1016,'MASTER  EN EDUCATION PHYSIQUE',0,0,1,NULL,NULL,8),(1017,'Master management',NULL,NULL,1,NULL,NULL,8),(1018,'CESDD',0,NULL,1,NULL,NULL,4),(1019,'',NULL,NULL,1,1,NULL,4),(1020,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(1021,'CESDD',0,NULL,1,NULL,NULL,4),(1022,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(1023,'CESDD',0,NULL,1,1,NULL,4),(1024,'CE1D',NULL,NULL,1,NULL,NULL,3),(1025,'CESS',1,0,1,NULL,NULL,5),(1026,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1027,'bachelier en biologie et chimie',0,NULL,1,NULL,NULL,7),(1028,'Médecine ',1,1,1,NULL,NULL,10),(1029,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1030,'Bachelier en géographie',0,NULL,1,NULL,NULL,7),(1031,'CESDD',NULL,NULL,1,NULL,NULL,4),(1032,'BACHELIER EN JOURNALISME',0,NULL,1,NULL,NULL,7),(1033,'Bachelier en informatique',0,NULL,1,NULL,NULL,7),(1034,'CESDD',0,NULL,1,1,NULL,4),(1035,'',NULL,NULL,1,NULL,NULL,1),(1036,'MASTER',0,0,1,NULL,NULL,8),(1037,'CEB',0,NULL,1,NULL,NULL,2),(1038,'Bachelier',NULL,NULL,1,NULL,NULL,7),(1039,'Bachelier éducatrice ',NULL,NULL,1,1,NULL,7),(1040,'Master ingénieur en construction',NULL,NULL,1,NULL,NULL,8),(1041,'Bachelier',NULL,NULL,1,NULL,NULL,7),(1042,'BACHELIER',0,0,1,NULL,NULL,7),(1043,'CESDD',0,NULL,1,NULL,NULL,4),(1044,'Master en Agronomie',1,1,1,NULL,NULL,8),(1045,'CESS',1,NULL,1,1,NULL,5),(1046,'Master en Economie-Management',0,NULL,1,NULL,NULL,8),(1047,'Bachelier en Administration des affaires',0,NULL,1,NULL,NULL,7),(1048,'Master médecine',1,NULL,1,1,NULL,8),(1049,'Master en didactique en langues FLE',NULL,NULL,1,0,NULL,8),(1050,'Bachelier Aide-soignante',0,NULL,1,NULL,NULL,7),(1051,'CESS',NULL,NULL,1,NULL,NULL,5),(1052,'Doctorat en bioinformatique + MBA',0,NULL,1,1,NULL,8),(1053,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1054,'Bachelier',1,NULL,1,NULL,NULL,7),(1055,'BACHELIER',0,0,1,NULL,NULL,7),(1056,'MASTER',0,NULL,1,NULL,NULL,8),(1057,'Certificat d\'enseignement secondaire du supérieur',0,NULL,1,1,NULL,5),(1058,'Bachelier dans l\'éducation',0,0,1,NULL,NULL,7),(1059,'CESDD',0,NULL,1,1,NULL,4),(1060,'MASTER EN MARKETING',0,0,1,NULL,NULL,8),(1061,'BACHELIER',0,0,1,NULL,NULL,7),(1062,'CESS',NULL,NULL,1,NULL,NULL,5),(1063,'bachelier en lettres modernes',NULL,NULL,1,NULL,NULL,7),(1064,'CESS',0,NULL,1,NULL,NULL,5),(1065,'',NULL,NULL,1,NULL,NULL,1),(1066,'certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,1,NULL,4),(1067,'',0,NULL,1,NULL,NULL,1),(1068,'CESDD',NULL,NULL,1,NULL,NULL,4),(1069,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1070,'CEB',0,NULL,1,NULL,NULL,2),(1071,'MASTER EN DROIT',0,0,1,NULL,NULL,8),(1072,'MASTER',0,0,1,NULL,NULL,8),(1073,'Baccaluaréat + 2ans de droit',1,NULL,1,NULL,NULL,10),(1074,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1075,'CESS',0,NULL,1,NULL,NULL,5),(1076,'bachelier en archéologie',0,NULL,1,NULL,NULL,7),(1077,'CESS',0,NULL,1,1,NULL,5),(1078,'Bachelier Assistant social',1,1,1,NULL,NULL,7),(1079,'Graduat design (vêtements)',0,NULL,1,NULL,NULL,7),(1080,'CESS',0,NULL,1,NULL,NULL,5),(1081,'BACHELIER INFIRMIER',0,0,1,NULL,NULL,7),(1082,'CESS',0,NULL,1,NULL,NULL,5),(1083,'Master en informatique',0,NULL,1,NULL,NULL,8),(1084,'CESS',NULL,NULL,1,NULL,NULL,5),(1085,'Master interprête',1,1,1,NULL,NULL,8),(1086,'Bachelier gestion économie appliquée',0,0,1,NULL,NULL,7),(1087,'CESS',NULL,NULL,1,NULL,NULL,5),(1088,'bachelier en administration',0,NULL,1,NULL,NULL,7),(1089,'Master en philologie',1,1,1,NULL,NULL,8),(1090,'Bachelier en agrenomie',0,0,1,NULL,NULL,7),(1091,'',NULL,NULL,1,NULL,NULL,1),(1092,'CESDD',NULL,NULL,1,1,NULL,4),(1093,'Master en danse pro',NULL,NULL,1,1,NULL,8),(1094,'CESS',1,0,1,NULL,NULL,5),(1095,'sans diplôme',0,0,1,NULL,NULL,1),(1096,'CEB',0,0,1,NULL,NULL,2),(1097,'BAC + 2 Informatique',1,0,1,1,NULL,6),(1098,'CE1D',0,0,1,NULL,NULL,3),(1099,'Brevet Infirmier Hospitalier ',NULL,NULL,1,NULL,NULL,10),(1100,'CEB',NULL,NULL,1,NULL,NULL,2),(1101,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(1102,'BACHELIER ',0,NULL,1,NULL,NULL,7),(1103,'CESS',0,NULL,1,NULL,NULL,5),(1104,'CESS',0,0,1,NULL,NULL,5),(1105,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1106,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1107,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1108,'CESDD',0,0,1,NULL,NULL,4),(1109,'Compta (pas eu le bac)',NULL,NULL,1,1,NULL,5),(1110,'Médecin',0,NULL,1,NULL,NULL,10),(1111,'CESS',0,0,1,NULL,NULL,5),(1112,'Master en économie',0,0,1,NULL,NULL,8),(1113,'Aide-soignant ',NULL,NULL,1,1,NULL,4),(1114,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1115,'Master en langue française',NULL,NULL,1,NULL,NULL,8),(1116,'BACHELIER EN ECONOMIE',0,0,1,NULL,NULL,7),(1117,'Doctorat en biologie',0,NULL,1,1,NULL,8),(1118,'Bachelier infirmier',1,1,1,NULL,NULL,7),(1119,'CESS',NULL,NULL,1,1,NULL,5),(1120,'CESS',0,NULL,1,1,NULL,5),(1121,'Master Banque et finances',0,NULL,1,NULL,NULL,8),(1122,'Bachelier en Communication',NULL,NULL,1,NULL,NULL,7),(1123,'CESS',NULL,NULL,1,NULL,NULL,5),(1124,'Bachelier en audiovisuel',1,NULL,1,1,NULL,7),(1125,'CESS',0,0,1,NULL,NULL,5),(1126,'CESS',1,1,1,NULL,NULL,5),(1127,'Master',NULL,NULL,1,NULL,NULL,8),(1128,'CESS',1,NULL,1,NULL,NULL,5),(1129,'CEB',0,NULL,1,NULL,NULL,2),(1130,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1131,'CEB',0,NULL,1,NULL,NULL,2),(1132,'CESS',0,NULL,1,NULL,NULL,5),(1133,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1134,'Droit',0,0,1,NULL,NULL,10),(1135,'CESS',0,NULL,1,NULL,NULL,5),(1136,'CESS',1,NULL,1,NULL,NULL,5),(1137,'mASTER',0,0,1,NULL,NULL,8),(1138,'CESDD',0,NULL,1,NULL,NULL,4),(1139,'Bachelier',0,0,1,NULL,NULL,7),(1140,'Master en gestion',NULL,NULL,1,NULL,NULL,8),(1141,'CESS',NULL,NULL,1,NULL,NULL,5),(1142,'CESS',NULL,NULL,1,NULL,NULL,5),(1143,'CESS',1,0,1,NULL,NULL,5),(1144,'Graduat Ingénieur biomédical',0,NULL,1,NULL,NULL,7),(1145,'bachelier en oligofrenopédagogie',0,NULL,1,NULL,NULL,7),(1146,'CESS',NULL,NULL,1,1,NULL,5),(1147,'CE1D',0,NULL,1,NULL,NULL,3),(1148,'CESS',NULL,NULL,1,NULL,NULL,5),(1149,'',NULL,NULL,1,1,NULL,2),(1150,'',NULL,NULL,1,NULL,NULL,1),(1151,'Economie',1,1,1,NULL,NULL,10),(1152,'Bachelier en électronique',1,0,1,NULL,NULL,7),(1153,'BACHELIER EN SCIENCES COMMERCIALE',1,1,1,NULL,NULL,7),(1154,'Certificat d\'Enseignement Secondaire du Deuxième Degré',0,NULL,1,NULL,NULL,4),(1155,'CESS',0,0,1,NULL,NULL,5),(1156,'Master en éducation physique',0,NULL,1,NULL,NULL,8),(1157,'',NULL,NULL,1,NULL,NULL,2),(1158,'CESS',0,NULL,1,NULL,NULL,5),(1159,'CESDD',0,NULL,1,NULL,NULL,4),(1160,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1161,'',NULL,NULL,1,0,NULL,5),(1162,'Master Comptabilité',NULL,NULL,1,NULL,NULL,8),(1163,'CEB',NULL,NULL,1,NULL,NULL,2),(1164,'Travaux publics',NULL,NULL,1,1,NULL,7),(1165,'Bachelier en art',NULL,NULL,1,NULL,NULL,7),(1166,'CESS',0,0,1,NULL,NULL,5),(1167,'CESS',0,NULL,1,1,NULL,5),(1168,'CESS PRO maintenance industrielle',1,0,1,1,NULL,5),(1169,'CESDD',NULL,NULL,1,NULL,NULL,4),(1170,'Master en sciences sociales ',NULL,NULL,1,NULL,NULL,8),(1171,'Master professeur en éducation commercial',1,NULL,1,NULL,NULL,8),(1172,'Bachelier ',0,NULL,1,NULL,NULL,7),(1173,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1174,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1175,'master',0,NULL,1,NULL,NULL,8),(1176,'CESDD',0,0,1,NULL,NULL,4),(1177,'CESDD',NULL,NULL,1,NULL,NULL,4),(1178,'CESS',NULL,NULL,1,NULL,NULL,5),(1179,'CESS',0,0,1,NULL,NULL,5),(1180,'CESS',0,NULL,1,NULL,NULL,5),(1181,'CESDD',0,0,1,NULL,NULL,4),(1182,'master',1,1,1,NULL,NULL,8),(1183,'Graduat aide-soignante',0,NULL,1,NULL,NULL,7),(1184,'CE1D',0,0,1,NULL,NULL,3),(1185,'CE1D',NULL,NULL,1,NULL,NULL,3),(1186,'CESS',0,NULL,1,1,NULL,5),(1187,'Master en droit',1,NULL,1,NULL,NULL,8),(1188,'CESS',NULL,NULL,1,NULL,NULL,5),(1189,'CESDD',NULL,NULL,1,NULL,NULL,4),(1190,'Master en français',0,0,1,NULL,NULL,8),(1191,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1192,'Licence en DROIT',0,NULL,1,NULL,NULL,10),(1193,'sans diplôme',0,NULL,1,NULL,NULL,1),(1194,'Bachelier comptabilité',NULL,NULL,1,NULL,NULL,7),(1195,'CE1D',NULL,NULL,1,NULL,NULL,3),(1196,'CESDD',0,NULL,1,1,NULL,4),(1197,'CEB',NULL,NULL,1,1,NULL,2),(1198,'',NULL,NULL,1,NULL,NULL,1),(1199,'CEB',0,NULL,1,NULL,NULL,2),(1200,'Master en finance',1,0,1,NULL,NULL,8),(1201,'CESS',0,0,1,NULL,NULL,5),(1202,'Sans Diplôme',0,0,1,NULL,NULL,1),(1203,'CESS',NULL,NULL,1,NULL,NULL,5),(1204,'CESDD',0,0,1,NULL,NULL,4),(1205,'Master en sciences du language',0,0,1,NULL,NULL,8),(1206,'CESS',0,NULL,1,NULL,NULL,5),(1207,'Sans Diplôme',0,0,1,NULL,NULL,1),(1208,'CESS Technique Compta',0,0,1,1,NULL,5),(1209,'Ingénieur Agronome',0,0,1,NULL,NULL,10),(1210,'CESS',0,NULL,1,NULL,NULL,5),(1211,'Professeur de Mathématiques',NULL,NULL,1,1,NULL,8),(1212,'Master en comptabilité',0,NULL,1,1,NULL,8),(1213,'master en ressources humaines',NULL,NULL,1,NULL,NULL,8),(1214,'CESS',0,NULL,1,NULL,NULL,5),(1215,'CESS',0,0,1,NULL,NULL,5),(1216,'cesdd',NULL,NULL,1,1,NULL,4),(1217,'Master en sciences politiques',0,NULL,1,NULL,NULL,8),(1218,'',0,NULL,1,1,NULL,5),(1219,'CESDD',NULL,NULL,1,NULL,NULL,4),(1220,'',NULL,NULL,1,1,NULL,2),(1221,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1222,'CESDD',0,0,1,NULL,NULL,4),(1223,'Master en architecture',0,NULL,1,NULL,NULL,8),(1224,'commerce',0,NULL,1,1,NULL,5),(1225,'Bachelier',0,0,1,NULL,NULL,7),(1226,'Bachelier en marketing',0,0,1,NULL,NULL,7),(1227,'',0,NULL,1,1,NULL,5),(1228,'CESDD',NULL,NULL,1,NULL,NULL,4),(1229,'CE1D',0,NULL,1,NULL,NULL,3),(1230,'CESS',1,1,1,NULL,NULL,5),(1231,'Bachelier infirmière',NULL,NULL,1,NULL,NULL,7),(1232,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1233,'infirmière',0,NULL,1,1,NULL,7),(1234,'CEB',0,NULL,1,NULL,NULL,2),(1235,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(1236,'Licence en Droit ',0,NULL,1,1,NULL,8),(1237,'Sage-femme',0,0,1,NULL,NULL,10),(1238,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1239,'Master',1,0,1,NULL,NULL,8),(1240,'CESS',NULL,NULL,1,NULL,NULL,5),(1241,'CESDD',NULL,NULL,1,NULL,NULL,4),(1242,'CEB',NULL,NULL,1,NULL,NULL,2),(1243,'CESS',0,0,1,NULL,NULL,5),(1244,'CESS',NULL,NULL,1,NULL,NULL,5),(1245,'CESS',0,NULL,1,NULL,NULL,5),(1246,'Bachelier en comptabilité',1,1,1,NULL,NULL,7),(1247,'MASTER EN GESTION D\'ENTREPRISE',0,NULL,1,NULL,NULL,8),(1248,'Bachelier en informatique de gestion',NULL,NULL,1,1,NULL,7),(1249,'MASTER',0,0,1,NULL,NULL,8),(1250,'CESS',0,0,1,NULL,NULL,5),(1251,'Bachelier en communication',0,0,1,NULL,NULL,7),(1252,'Gestion',0,NULL,1,NULL,NULL,10),(1253,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1254,'Bachelier en comptabilité et finances',1,1,1,1,NULL,7),(1255,'MASTER EN SOCIOLOGIE',0,0,1,NULL,NULL,8),(1256,'certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,1,NULL,4),(1257,'CESS',0,0,1,NULL,NULL,5),(1258,'CEB',0,NULL,1,NULL,NULL,2),(1259,'CESS institutrice primaire',0,0,1,1,NULL,5),(1260,'Finance et compta',0,0,1,NULL,NULL,10),(1261,'Ingénieur électrique et électronique',0,NULL,1,1,NULL,7),(1262,'CESS informatique des télécommunications',0,NULL,1,1,NULL,5),(1263,'CESS',0,NULL,1,1,NULL,5),(1264,'MASTER',0,0,1,NULL,NULL,8),(1265,'certificat d\'enseignement secondaire du deuxiéme degré',NULL,NULL,1,1,NULL,4),(1266,'CESS',NULL,NULL,1,NULL,NULL,5),(1267,'Bachelier lettres',1,NULL,1,NULL,NULL,7),(1268,'CESS',NULL,NULL,1,NULL,NULL,5),(1269,'master en droit',0,NULL,1,NULL,NULL,8),(1270,'CEB',NULL,NULL,1,NULL,NULL,2),(1271,'Licence en Economie',NULL,NULL,1,NULL,NULL,10),(1272,'CESS',0,NULL,1,NULL,NULL,5),(1273,'',NULL,NULL,1,1,NULL,4),(1274,'master',NULL,NULL,1,NULL,NULL,8),(1275,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1276,'CESS',0,0,1,NULL,NULL,5),(1277,'Technicien ',0,0,1,NULL,NULL,10),(1278,'CESS',NULL,NULL,1,NULL,NULL,5),(1279,'CESDD',0,NULL,1,NULL,NULL,4),(1280,'Bachelier en Histoire',0,0,1,NULL,NULL,7),(1281,'CESS',0,0,1,NULL,NULL,5),(1282,'Bachelier (science éco)',0,NULL,1,NULL,NULL,7),(1283,'CE1D',NULL,NULL,1,NULL,NULL,3),(1284,'Master en droit',NULL,NULL,1,1,NULL,8),(1285,'Bachelier en droit',0,NULL,1,NULL,NULL,7),(1286,'Bachelier en droit privé et judiciaire',0,NULL,1,NULL,NULL,7),(1287,'MASTER EN PSYCHOLOGIE CLINIQUE',0,0,1,NULL,NULL,8),(1288,'licence en droit',0,NULL,1,1,NULL,8),(1289,'bachelier',0,NULL,1,NULL,NULL,7),(1290,'master en gestion et administration des entreprises',0,NULL,1,NULL,NULL,8),(1291,'bachelier',0,NULL,1,NULL,NULL,7),(1292,'bachelier',NULL,NULL,1,NULL,NULL,7),(1293,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1294,'CESDD',0,NULL,1,NULL,NULL,4),(1295,'bachelier',0,0,1,NULL,NULL,7),(1296,'',NULL,NULL,1,NULL,NULL,1),(1297,'BACHELIER',0,0,1,NULL,NULL,7),(1298,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1299,'Master en Droit',1,NULL,1,1,NULL,8),(1300,'Master: Gestion informatique',0,0,1,NULL,NULL,8),(1301,'Bachelier Administration publique',0,NULL,1,NULL,NULL,7),(1302,'CESS',0,0,1,NULL,NULL,5),(1303,'CESS',0,NULL,1,NULL,NULL,5),(1304,'Master',0,NULL,1,NULL,NULL,8),(1305,'BACHELIER',0,0,1,NULL,NULL,7),(1306,'Sans diplôme',0,NULL,1,NULL,NULL,1),(1307,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',1,1,1,NULL,NULL,5),(1308,'BACHELIER',0,0,1,NULL,NULL,7),(1309,'Master architecte',0,NULL,1,1,NULL,8),(1310,'Master en Sciences Politique',NULL,NULL,1,NULL,NULL,8),(1311,'Licence en Sc. De la Communication',0,0,1,NULL,NULL,8),(1312,'bachelier en gestion administrative des entreprises',0,NULL,1,NULL,NULL,7),(1313,'MASTER',0,NULL,1,NULL,NULL,8),(1314,'Master en chimie',0,0,1,NULL,NULL,8),(1315,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1316,'CESS',0,0,1,NULL,NULL,5),(1317,'MASTER EN COMMUNICATION',0,NULL,1,NULL,NULL,8),(1318,'LICENCE EN DROIT',1,1,1,NULL,NULL,10),(1319,'licence en droit',0,0,1,1,NULL,8),(1320,'MASTER EN DROIT',0,0,1,NULL,NULL,8),(1321,'bachelier en littérature',0,NULL,1,1,NULL,7),(1322,'Bachelier Infirmier',0,0,1,NULL,NULL,7),(1323,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE DU DEUXIEME DEGRE ',0,NULL,1,NULL,NULL,4),(1324,'CEB',0,0,1,NULL,NULL,2),(1325,'Bachelier commercial',NULL,NULL,1,NULL,NULL,7),(1326,'CESS',0,NULL,1,NULL,NULL,5),(1327,'CESS Enseignante primaire',0,NULL,1,NULL,NULL,5),(1328,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1329,'CESS',0,NULL,1,NULL,NULL,5),(1330,'CESDD',0,0,1,NULL,NULL,4),(1331,'Bachelier Assistant social',0,NULL,1,NULL,NULL,7),(1332,'LICENCE en biologie',0,NULL,1,NULL,NULL,10),(1333,'Architecte',1,1,1,0,NULL,8),(1334,'master',0,0,1,1,NULL,8),(1335,'Bachelier',1,1,1,NULL,NULL,7),(1336,'Informatique de gestion',0,NULL,1,NULL,NULL,10),(1337,'CESS',0,NULL,1,NULL,NULL,5),(1338,'Master en Commerce International ',0,NULL,1,NULL,NULL,8),(1339,'Bachelier Assistant social',1,1,1,NULL,NULL,7),(1340,'Master en orthopédie',NULL,NULL,1,NULL,NULL,8),(1341,'CESS',1,0,1,NULL,NULL,5),(1342,'master en pharmacie',0,0,1,NULL,NULL,8),(1343,'',0,NULL,1,NULL,NULL,5),(1344,'CEB',NULL,NULL,1,NULL,NULL,2),(1345,'CESS',0,NULL,1,1,NULL,5),(1346,'CESDD',0,0,1,NULL,NULL,4),(1347,'CESS',0,NULL,1,NULL,NULL,5),(1348,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1349,'Bachelier ingénieur mécanique',0,NULL,1,0,NULL,7),(1350,'',NULL,NULL,1,1,NULL,4),(1351,'CEB',0,0,1,NULL,NULL,2),(1352,'CESS',0,NULL,1,NULL,NULL,5),(1353,'',1,1,1,1,NULL,5),(1354,'Bachelier sage-femme',0,NULL,1,NULL,NULL,7),(1355,'Master',NULL,NULL,1,NULL,NULL,8),(1356,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1357,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1358,'CEB',NULL,NULL,1,NULL,NULL,2),(1359,'CESS',1,1,1,NULL,NULL,5),(1360,'CESDD',NULL,NULL,1,NULL,NULL,4),(1361,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1362,'CESS',NULL,NULL,1,NULL,NULL,5),(1363,'Bachelier en Psychologie (en cours)',1,1,1,NULL,NULL,7),(1364,'',NULL,NULL,1,1,NULL,2),(1365,'Bachelier en Langues',0,NULL,1,NULL,NULL,7),(1366,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1367,'licence en Sciende de la communication',NULL,NULL,1,1,NULL,8),(1368,'BACHELIER',0,0,1,NULL,NULL,7),(1369,'Master en droit',0,NULL,1,NULL,NULL,8),(1370,'Bachelier assistante de Direction ',0,0,1,NULL,NULL,7),(1371,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1372,'Master en anglais',1,NULL,1,NULL,NULL,8),(1373,'CESDD',NULL,NULL,1,NULL,NULL,4),(1374,'CESS',0,NULL,1,NULL,NULL,5),(1375,'Ens. Sec. Compl. - cuisine',1,1,1,NULL,NULL,6),(1376,'Master en Finances',0,NULL,1,NULL,NULL,8),(1377,'CESS',0,0,1,NULL,NULL,5),(1378,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1379,'MASTER EN AUDIO VISUEL',1,1,1,NULL,NULL,8),(1380,'CESS',NULL,NULL,1,NULL,NULL,5),(1381,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1382,'CESS',NULL,NULL,1,1,NULL,5),(1383,'CESS',0,0,1,NULL,NULL,5),(1384,'MASTER',0,0,1,NULL,NULL,8),(1385,'Master linguistique',NULL,NULL,1,NULL,NULL,8),(1386,'Bachelier Génie Civil',NULL,NULL,1,NULL,NULL,7),(1387,'CESS',0,NULL,1,NULL,NULL,5),(1388,'',0,0,1,1,NULL,5),(1389,'CESS',1,1,1,NULL,NULL,5),(1390,'',NULL,NULL,1,NULL,NULL,1),(1391,'CESDD',NULL,NULL,1,NULL,NULL,4),(1392,'MASTER EN ADMINISTRATION',0,0,1,NULL,NULL,8),(1393,'Master ',0,0,1,NULL,NULL,8),(1394,'Bachelier ',NULL,NULL,1,1,NULL,7),(1395,'CESS',0,NULL,1,NULL,NULL,5),(1396,'CESS',0,NULL,1,NULL,NULL,5),(1397,'Bachelier sage-femme',NULL,NULL,1,NULL,NULL,7),(1398,'Master',1,0,1,NULL,NULL,8),(1399,'Master en Journalisme ',0,NULL,1,NULL,NULL,8),(1400,'Juriste',0,0,1,NULL,NULL,10),(1401,'BACHELIER',0,0,1,NULL,NULL,7),(1402,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1403,'Avocat',1,NULL,1,1,NULL,8),(1404,'CESS',0,0,1,NULL,NULL,5),(1405,'CESS',NULL,NULL,1,NULL,NULL,5),(1406,'',NULL,NULL,1,NULL,NULL,4),(1407,'Master en marketing',NULL,NULL,1,1,NULL,8),(1408,'LICENCE EN DROIT',0,0,1,NULL,NULL,10),(1409,'CESS',NULL,NULL,1,NULL,NULL,5),(1410,'Master en Langues modernes',0,NULL,1,1,NULL,8),(1411,'CESS',1,NULL,1,NULL,NULL,5),(1412,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1413,'',NULL,NULL,1,1,NULL,5),(1414,'bachelier en business administration',0,0,1,1,NULL,7),(1415,'Ens. Sec. Compl.',0,NULL,1,NULL,NULL,6),(1416,'CE1D',0,0,1,NULL,NULL,3),(1417,'master',NULL,NULL,1,NULL,NULL,8),(1418,'Bachelier en science du service social (Assistante sociale)',0,NULL,1,NULL,NULL,7),(1419,'Master',1,1,1,NULL,NULL,8),(1420,'enseignement compl en Art',0,NULL,1,1,NULL,6),(1421,'CESS',NULL,NULL,1,NULL,NULL,5),(1422,'master en pharmacie',1,NULL,1,1,NULL,8),(1423,'CE1D',NULL,NULL,1,NULL,NULL,3),(1424,'Bachelier en droit',NULL,NULL,1,NULL,NULL,7),(1425,'MASTER',0,0,1,NULL,NULL,8),(1426,'Baccalauréat + licence science de la matière physique',1,NULL,1,NULL,NULL,10),(1427,'CESDD',NULL,NULL,1,NULL,NULL,4),(1428,'CESDD',0,NULL,1,NULL,NULL,4),(1429,'BACHELIER ',0,NULL,1,NULL,NULL,7),(1430,'Master en banque et finances ',0,NULL,1,NULL,NULL,8),(1431,'CESS',NULL,NULL,1,NULL,NULL,5),(1432,'BACHELIER EN MARKETING',1,0,1,1,NULL,7),(1433,'Bachelier en sciences économiques et de gestion',0,NULL,1,NULL,NULL,7),(1434,'CESS',0,0,1,NULL,NULL,5),(1435,'CESDD',NULL,NULL,1,NULL,NULL,4),(1436,'MASTER',0,NULL,1,NULL,NULL,8),(1437,'CESS',0,0,1,NULL,NULL,5),(1438,'bachelier en génie des ressources naturelles',0,NULL,1,NULL,NULL,7),(1439,'Master',0,NULL,1,NULL,NULL,8),(1440,'CESS',NULL,NULL,1,1,NULL,5),(1441,'master',NULL,NULL,1,NULL,NULL,8),(1442,'CESS',0,NULL,1,1,NULL,5),(1443,'Master',1,0,1,NULL,NULL,8),(1444,'CESDD',0,0,1,NULL,NULL,4),(1445,'',NULL,NULL,1,NULL,NULL,1),(1446,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1447,'MASTER',0,0,1,NULL,NULL,8),(1448,'',NULL,NULL,1,NULL,NULL,1),(1449,'CESDD',0,NULL,1,NULL,NULL,4),(1450,'Master',NULL,NULL,1,NULL,NULL,8),(1451,'Licence en administration',0,NULL,1,1,NULL,7),(1452,'Bachelier',0,0,1,NULL,NULL,7),(1453,'CESDD',0,NULL,1,NULL,NULL,4),(1454,'EnS. Sec. Compl. Stylisme, modélisme, coupe et couture',0,NULL,1,NULL,NULL,6),(1455,'CEB',NULL,NULL,1,NULL,NULL,2),(1456,'CESDD',0,NULL,1,1,NULL,4),(1457,'CESS',0,0,1,NULL,NULL,5),(1458,'Certificat Aide-soignante',0,NULL,1,NULL,NULL,10),(1459,'BACHELIER EN ECONOMIE',1,1,1,NULL,NULL,7),(1460,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1461,'Master en Economie',1,NULL,1,1,NULL,8),(1462,'bachelier',NULL,NULL,1,NULL,NULL,7),(1463,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1464,'Psychologie du travail',NULL,NULL,1,1,NULL,8),(1465,'Licence',0,0,1,NULL,NULL,10),(1466,'master en médecine',0,NULL,1,NULL,NULL,8),(1467,'CESDD',0,0,1,NULL,NULL,4),(1468,'Master',0,NULL,1,NULL,NULL,8),(1469,'bachelier en génie mécanique',0,NULL,1,1,NULL,7),(1470,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(1471,'CEB',NULL,NULL,1,NULL,NULL,2),(1472,'Master',0,0,1,NULL,NULL,8),(1473,'BACHELIER INFIRMIER',0,NULL,1,NULL,NULL,7),(1474,'',NULL,NULL,1,1,NULL,4),(1475,'Bachelier en drroit',0,NULL,1,NULL,NULL,7),(1476,'CESS',0,0,1,NULL,NULL,5),(1477,'CESDD',0,NULL,1,1,NULL,4),(1478,'CESS',0,NULL,1,1,NULL,5),(1479,'',NULL,NULL,1,1,NULL,2),(1480,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1481,'CESS',0,NULL,1,NULL,NULL,5),(1482,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1483,'',NULL,NULL,1,NULL,NULL,1),(1484,'CESS',NULL,NULL,1,NULL,NULL,5),(1485,'BEP',0,0,1,NULL,NULL,10),(1486,'master en histoire',NULL,NULL,1,1,NULL,8),(1487,'Master en science islamique',0,NULL,1,NULL,NULL,8),(1488,'CEB',0,0,1,NULL,NULL,2),(1489,'CESS',NULL,NULL,1,NULL,NULL,5),(1490,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1491,'CESS',0,0,1,NULL,NULL,5),(1492,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1493,'bachelier en secrétariat',0,0,1,1,NULL,7),(1494,'pas de diplôme',0,NULL,1,NULL,NULL,1),(1495,'Bachelier economie',NULL,NULL,1,NULL,NULL,7),(1496,'CEB',0,NULL,1,NULL,NULL,2),(1497,'CESS',0,0,1,NULL,NULL,5),(1498,'CESS',0,NULL,1,NULL,NULL,5),(1499,'CESS',NULL,1,1,NULL,NULL,5),(1500,'CESS',0,NULL,1,NULL,NULL,5),(1501,'CESS',0,NULL,1,NULL,NULL,5),(1502,'Master Pharmaceutique',0,0,1,NULL,NULL,8),(1503,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1504,'CESDD',0,NULL,1,NULL,NULL,4),(1505,'CESS',0,0,1,NULL,NULL,5),(1506,'Bachelier en maintenace technique aéronautique',1,NULL,1,NULL,NULL,7),(1507,'Sans diplôme',0,0,1,NULL,NULL,1),(1508,'CE1D',0,NULL,1,NULL,NULL,3),(1509,'Bachelier en comptabilité',0,0,1,NULL,NULL,7),(1510,'CE1D',NULL,NULL,1,NULL,NULL,3),(1511,'Licence ',0,0,1,NULL,NULL,10),(1512,'Bachelier sciences islamiques',0,NULL,1,NULL,NULL,7),(1513,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1514,'CESS',0,NULL,1,NULL,NULL,5),(1515,'Master en sciences économiques et de gestion',0,NULL,1,NULL,NULL,8),(1516,'BACHELIER',0,NULL,1,NULL,NULL,7),(1517,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1518,'Master journaliste',NULL,NULL,1,NULL,NULL,8),(1519,'Master',NULL,NULL,1,NULL,NULL,8),(1520,'CESDD',0,NULL,1,NULL,NULL,4),(1521,'Master en relation internationale',1,NULL,1,1,NULL,8),(1522,'CESS',0,0,1,NULL,NULL,5),(1523,'CEB',0,0,1,NULL,NULL,2),(1524,'CESS',0,NULL,1,NULL,NULL,5),(1525,'MASTER',0,0,1,NULL,NULL,8),(1526,'CESS',NULL,NULL,1,NULL,NULL,5),(1527,'CESDD',NULL,NULL,1,1,NULL,4),(1528,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1529,'CESS professeur d\'école de musique, chef d\'orchestre et d\'ensemble',0,NULL,1,1,NULL,5),(1530,'bachelier en anglais',0,NULL,1,NULL,NULL,7),(1531,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1532,'Bachelier commerce',NULL,NULL,1,NULL,NULL,7),(1533,'bachelier',0,NULL,1,NULL,NULL,7),(1534,'CESS',0,NULL,1,NULL,NULL,5),(1535,'CEB',0,NULL,1,NULL,NULL,2),(1536,'Sans Diplôme',0,0,1,NULL,NULL,1),(1537,'CESS',0,NULL,1,1,NULL,5),(1538,'CESS',NULL,NULL,1,NULL,NULL,5),(1539,'master en pharmacie',0,0,1,NULL,NULL,8),(1540,'Bachelier en management (hôtellerie)',0,NULL,1,NULL,NULL,7),(1541,'Master en chimie',0,NULL,1,NULL,NULL,8),(1542,'CERTIFICAT D\'ETUDE DE BASE',1,NULL,1,NULL,NULL,2),(1543,'Master en économie',NULL,NULL,1,1,NULL,8),(1544,'CESDD',NULL,NULL,1,NULL,NULL,4),(1545,'CEB',0,NULL,1,NULL,NULL,2),(1546,'CESDD',0,0,1,NULL,NULL,4),(1547,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1548,'Bachelier en droit des affaires',1,1,1,1,NULL,7),(1549,'',NULL,NULL,1,NULL,NULL,2),(1550,'CEB',0,0,1,NULL,NULL,2),(1551,'master',0,NULL,1,NULL,NULL,8),(1552,'MASTER',0,0,1,NULL,NULL,8),(1553,'Bachelier en Gestion commerciale et industrielle',0,NULL,1,NULL,NULL,7),(1554,'MASTER',0,0,1,NULL,NULL,8),(1555,'MASTER',0,0,1,NULL,NULL,8),(1556,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1557,'SANS DIPLOME',0,0,1,NULL,NULL,1),(1558,'Master Gestion',1,1,1,NULL,NULL,8),(1559,'MASTER EN DROIT',0,NULL,1,NULL,NULL,8),(1560,'licence en économie',0,NULL,1,1,NULL,8),(1561,'Graduat aide-soignante',0,NULL,1,NULL,NULL,7),(1562,'Master réalisatrice',0,NULL,1,1,NULL,8),(1563,'master en chimie',1,0,1,NULL,NULL,8),(1564,'CESS',1,1,1,NULL,NULL,5),(1565,'CESDD',NULL,NULL,1,NULL,NULL,4),(1566,'CESDD',0,NULL,1,NULL,NULL,4),(1567,'Bachelier graphic web designer',0,0,1,1,NULL,7),(1568,'Master Comptabilité',1,NULL,1,NULL,NULL,8),(1569,'MASTER EN ELECTRONIQUE',0,0,1,NULL,NULL,8),(1570,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1571,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1572,'CESS',0,NULL,1,NULL,NULL,5),(1573,'Bachelier en Marketing',1,0,1,NULL,NULL,7),(1574,'',0,NULL,1,1,NULL,2),(1575,'bachelier en économie et finances',0,NULL,1,NULL,NULL,7),(1576,'master',NULL,NULL,1,NULL,NULL,8),(1577,'BACHELIER',0,0,1,NULL,NULL,7),(1578,'Certificat d\'enseignement secondaire du supérieur',0,0,1,1,NULL,5),(1579,'Sans diplôme',0,NULL,1,NULL,NULL,1),(1580,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1581,'Bachelier',0,NULL,1,NULL,NULL,7),(1582,'Bachelier Business Administration',0,NULL,1,1,NULL,7),(1583,'3eme année carrosserie Promotion sociale',0,NULL,1,NULL,NULL,10),(1584,'BACHELIER EN GESTION',0,NULL,1,NULL,NULL,7),(1585,'Sans Dipôme',0,0,1,NULL,NULL,1),(1586,'CESS',0,NULL,1,1,NULL,5),(1587,'Master en droit',NULL,NULL,1,NULL,NULL,8),(1588,'BACHELIER',0,0,1,NULL,NULL,7),(1589,'BACHELIER',0,0,1,NULL,NULL,7),(1590,'Master Médecine Générale',0,0,1,NULL,NULL,8),(1591,'MASTER',0,0,1,NULL,NULL,8),(1592,'Bachelier linguistique',0,0,1,NULL,NULL,7),(1593,'Eau, forêt et environnement',NULL,NULL,1,1,NULL,7),(1594,'Master Chimie',0,0,1,NULL,NULL,8),(1595,'Master en management',0,NULL,1,NULL,NULL,8),(1596,'CEB',NULL,NULL,1,1,NULL,2),(1597,'CESS',0,NULL,1,NULL,NULL,5),(1598,'Bachelier en Construction (en cours)',1,1,1,NULL,NULL,7),(1599,'Bachelier Banque et Finance',0,0,1,NULL,NULL,7),(1600,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1601,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1602,'CESS',NULL,NULL,1,NULL,NULL,5),(1603,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1604,'CESS',0,NULL,1,1,NULL,5),(1605,'CESS',1,1,1,NULL,NULL,5),(1606,'Master en santé public',1,NULL,1,NULL,NULL,8),(1607,'Aide soignante',NULL,NULL,1,1,NULL,7),(1608,'Master en gestion totale de la qualité',NULL,NULL,1,NULL,NULL,8),(1609,'Doctorat médical et Master en Gestion de la santé',0,NULL,1,1,NULL,8),(1610,'CESDD',NULL,NULL,1,NULL,NULL,4),(1611,'Master en relations internationales (Biélorussie)',1,1,1,NULL,NULL,8),(1612,'CESS',0,NULL,1,NULL,NULL,5),(1613,'CESS',1,1,1,NULL,NULL,5),(1614,'CESS',0,0,1,NULL,NULL,5),(1615,'CESS',NULL,NULL,1,NULL,NULL,5),(1616,'BTS Gestion',NULL,NULL,1,NULL,NULL,10),(1617,'Enseignant',NULL,NULL,1,1,NULL,8),(1618,'CESS',0,NULL,1,NULL,NULL,5),(1619,'CEB + 2 années de médecine',1,NULL,1,NULL,NULL,2),(1620,'master',0,NULL,1,NULL,NULL,8),(1621,'Bachelier en Informatique de gestion',0,0,1,NULL,NULL,7),(1622,'',1,1,1,1,NULL,5),(1623,'CESS',0,NULL,1,NULL,NULL,5),(1624,'CESDD',0,NULL,1,NULL,NULL,4),(1625,'CESS',0,NULL,1,NULL,NULL,5),(1626,'CESI',0,NULL,1,NULL,NULL,10),(1627,'CESDD',0,0,1,NULL,NULL,4),(1628,'CESS',0,NULL,1,NULL,NULL,5),(1629,'pas de diplôme',0,NULL,1,NULL,NULL,1),(1630,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1631,'CESS',NULL,NULL,1,NULL,NULL,5),(1632,'Ingénieur Agronome',NULL,NULL,1,NULL,NULL,10),(1633,'CE1D',NULL,NULL,1,NULL,NULL,3),(1634,'CESS',0,0,1,NULL,NULL,5),(1635,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1636,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1637,'CESS: option sicences naturelles',0,0,1,NULL,NULL,5),(1638,'CESS',0,NULL,1,1,NULL,5),(1639,'MASTER',0,0,1,NULL,NULL,8),(1640,'CESS',1,1,1,NULL,NULL,5),(1641,'CESS',0,NULL,1,NULL,NULL,5),(1642,'CESS',0,NULL,1,NULL,NULL,5),(1643,'Sciences pharmaceutiques',NULL,NULL,1,NULL,NULL,10),(1644,'Bachelier en administration, comptabilité et finances',NULL,NULL,1,1,NULL,7),(1645,'Master en Sc Physique',NULL,NULL,1,1,NULL,8),(1646,'Master',NULL,NULL,1,NULL,NULL,8),(1647,'Bachelier',1,1,1,NULL,NULL,7),(1648,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1649,'MASTER EN LANGUE',0,NULL,1,1,NULL,8),(1650,'CESS',NULL,NULL,1,NULL,NULL,5),(1651,'mASTER',0,0,1,NULL,NULL,8),(1652,'CESS',0,0,1,NULL,NULL,5),(1653,'Master en éducation',1,1,1,NULL,NULL,8),(1654,'Master Management',0,NULL,1,NULL,NULL,8),(1655,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1656,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',1,1,1,NULL,NULL,5),(1657,'Bachelier',NULL,NULL,1,NULL,NULL,7),(1658,'CESS  Educatrice A2',NULL,NULL,1,NULL,NULL,5),(1659,'MASTER',0,0,1,NULL,NULL,8),(1660,'CESS',NULL,NULL,1,NULL,NULL,5),(1661,'',NULL,NULL,1,NULL,NULL,1),(1662,'CESS',1,1,1,1,NULL,5),(1663,'CESS',NULL,NULL,1,NULL,NULL,5),(1664,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,0,1,NULL,NULL,5),(1665,'Médecine',1,1,1,NULL,NULL,10),(1666,'doctorante ingénieure agricole',0,NULL,1,NULL,NULL,10),(1667,'bachelier en infirmerie',0,0,1,1,NULL,7),(1668,'CESS',0,0,1,NULL,NULL,5),(1669,'Bachelier en économie',0,NULL,1,NULL,NULL,7),(1670,'CEB',NULL,NULL,1,NULL,NULL,2),(1671,'Bachelier gestion en informatique',NULL,NULL,1,1,NULL,7),(1672,'CESS',0,NULL,1,NULL,NULL,5),(1673,'CESS',0,NULL,1,1,NULL,5),(1674,'bachelier en Biomedical',0,NULL,1,1,NULL,7),(1675,'CESDD',0,NULL,1,1,NULL,4),(1676,'CESS',NULL,NULL,1,NULL,NULL,5),(1677,'MASTER',0,0,1,NULL,NULL,8),(1678,'CEB',0,NULL,1,NULL,NULL,2),(1679,'BACHELIER',0,NULL,1,NULL,NULL,7),(1680,'Licence en économie',0,NULL,1,NULL,NULL,10),(1681,'CESS',NULL,NULL,1,NULL,NULL,5),(1682,'BACHELIER',0,NULL,1,NULL,NULL,7),(1683,'CESS',0,NULL,1,NULL,NULL,5),(1684,'Bachelier',0,0,1,NULL,NULL,7),(1685,'Bachelier',0,NULL,1,NULL,NULL,7),(1686,'CESS',NULL,NULL,1,NULL,NULL,5),(1687,'sans diplôme',NULL,NULL,1,NULL,NULL,1),(1688,'CESS',NULL,NULL,1,NULL,NULL,5),(1689,'master en agronomie',0,NULL,1,1,NULL,8),(1690,'CESS',NULL,NULL,1,1,NULL,5),(1691,'CESS',0,NULL,1,NULL,NULL,5),(1692,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1693,'Bachelier en Sciences Pol.',1,1,1,NULL,NULL,7),(1694,'Bachelier en Marketing',0,NULL,1,1,NULL,7),(1695,'CESS',0,0,1,NULL,NULL,5),(1696,'CESS',0,NULL,1,NULL,NULL,5),(1697,'master',NULL,NULL,1,NULL,NULL,8),(1698,'',1,1,1,1,NULL,5),(1699,'CESDD',0,NULL,1,NULL,NULL,4),(1700,'BACHERLIER',0,0,1,NULL,NULL,7),(1701,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1702,'CESS',1,1,1,1,NULL,5),(1703,'Master en éducation ',0,0,1,1,NULL,8),(1704,'CESDD',0,NULL,1,1,NULL,4),(1705,'Master en Langues et Cultures Modernes',0,NULL,1,NULL,NULL,8),(1706,'Master en biochimie et biologie moléculaire et cellulaire',1,1,1,1,NULL,8),(1707,'CESS',1,NULL,1,NULL,NULL,5),(1708,'CESDD',NULL,NULL,1,NULL,NULL,4),(1709,'CESDD',0,NULL,1,1,NULL,4),(1710,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1711,'sans diplôme',0,0,1,NULL,NULL,1),(1712,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1713,'ARCHEOLOGUE',1,NULL,1,NULL,NULL,10),(1714,'BACHELIER',0,0,1,NULL,NULL,7),(1715,'Bachelier en journaliste',NULL,NULL,1,1,NULL,7),(1716,'CESS',1,1,1,1,NULL,5),(1717,'CE1D',NULL,NULL,1,NULL,NULL,3),(1718,'Sociologie ',NULL,NULL,1,NULL,NULL,10),(1719,'Bachelier economie',0,0,1,NULL,NULL,7),(1720,'Gestion HEC',NULL,NULL,1,NULL,NULL,10),(1721,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1722,'Graduat Assistant ingénieur en électronique',1,NULL,1,1,NULL,7),(1723,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1724,'CERTIFICAT D\'ENSEIGNEMENT SECONDAIRE SUPERIEUR',0,NULL,1,NULL,NULL,5),(1725,'Ens. Sec. Compl.',NULL,NULL,1,NULL,NULL,6),(1726,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1727,'CESS',0,NULL,1,NULL,NULL,5),(1728,'Bachelier kinésithérapie',0,NULL,1,NULL,NULL,7),(1729,'CESS',NULL,NULL,1,NULL,NULL,5),(1730,'CEB',NULL,NULL,1,NULL,NULL,2),(1731,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1732,'MASTER EN PHARMACIE',0,0,1,NULL,NULL,8),(1733,'master en anglais',0,NULL,1,NULL,NULL,8),(1734,'bachelier',0,NULL,1,NULL,NULL,7),(1735,'CESS',NULL,NULL,1,NULL,NULL,5),(1736,'CESS',NULL,NULL,1,1,NULL,5),(1737,'Bachelier Mathématique',0,0,1,NULL,NULL,7),(1738,'Master en sociologie',1,NULL,1,NULL,NULL,8),(1739,'CE1D',NULL,NULL,1,NULL,NULL,3),(1740,'CE1D',0,NULL,1,NULL,NULL,3),(1741,'Bachelier marketing',0,NULL,1,NULL,NULL,7),(1742,'master',1,1,1,NULL,NULL,8),(1743,'Sans diplôme',NULL,NULL,1,NULL,NULL,1),(1744,'Bachelier',0,NULL,1,NULL,NULL,7),(1745,'CERTIFICAT D\'ETUDE DE BASE',0,NULL,1,NULL,NULL,2),(1746,'Certificat d\'enseignement secondaire du deuxiéme degré',0,NULL,1,NULL,NULL,4),(1747,'infirmière',0,NULL,1,NULL,NULL,7),(1748,'réparateur des téléphones',NULL,NULL,0,NULL,NULL,13),(1749,'manœuvre polyvalent',NULL,NULL,0,NULL,NULL,11),(1750,'Bureautique',NULL,NULL,0,NULL,NULL,11),(1751,'Plafonnage',NULL,NULL,0,NULL,NULL,11),(1752,'Electromécanique',NULL,NULL,0,NULL,NULL,11),(1753,'auxilière de vie (personnes âgées) ,Italie',NULL,NULL,0,NULL,NULL,11),(1754,'technicienne de surface',NULL,NULL,0,NULL,NULL,11),(1755,'coiffure',NULL,NULL,0,NULL,NULL,11),(1756,'adjointe administrative',NULL,NULL,0,NULL,NULL,13),(1757,'assistance aux personnes ',NULL,NULL,0,NULL,NULL,13),(1758,'Gestion',NULL,NULL,0,NULL,NULL,11),(1759,'Formation en animation',NULL,NULL,0,NULL,NULL,11),(1760,'',NULL,NULL,0,NULL,NULL,13),(1761,'Français Métier chez AGORA',NULL,NULL,0,NULL,NULL,10),(1762,'Peintre en batiment ',NULL,NULL,0,NULL,NULL,13),(1763,'technicien de surface - agent de proprété',NULL,NULL,0,NULL,NULL,13),(1764,'Formation en vente',NULL,NULL,0,NULL,NULL,11),(1765,'Chauffage/Plomberie',NULL,NULL,0,NULL,NULL,13),(1766,'gestion de base',NULL,NULL,0,NULL,NULL,11),(1767,'animatrice enfant',NULL,NULL,0,NULL,NULL,13),(1768,'',NULL,NULL,0,NULL,NULL,11),(1769,'Assistant comptable IFAPME',NULL,NULL,0,NULL,NULL,11),(1770,'Monteur - cableur ',NULL,NULL,0,NULL,NULL,13),(1771,'formation professionnelle (cap emploi)',NULL,NULL,0,NULL,NULL,13),(1772,'',NULL,NULL,0,NULL,NULL,11),(1773,'formation nettoyage',NULL,NULL,0,NULL,NULL,11),(1774,'Formation aide ménagère',NULL,NULL,0,NULL,NULL,10),(1775,'TR et LOG',NULL,NULL,0,NULL,NULL,13),(1776,'Assistant logistique en milieu hospitalier',NULL,NULL,0,NULL,NULL,13),(1777,'technicienne de surface',NULL,NULL,0,NULL,NULL,11),(1778,'Magasinier-cariste',NULL,NULL,0,NULL,NULL,11),(1779,'',NULL,NULL,0,NULL,NULL,2),(1780,'',NULL,NULL,0,NULL,NULL,5);
/*!40000 ALTER TABLE `formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formationtype`
--

DROP TABLE IF EXISTS `formationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formationtype` (
  `idFormationType` int(11) NOT NULL AUTO_INCREMENT,
  `formationTypeName` varchar(50) NOT NULL,
  `isFormation` tinyint(1) NOT NULL DEFAULT 0,
  `isDelete` tinyint(1) NOT NULL,
  `idNiveauEtude` int(11) NOT NULL,
  PRIMARY KEY (`idFormationType`),
  KEY `FormationType_NiveauEtudefse_FK` (`idNiveauEtude`),
  CONSTRAINT `FormationType_NiveauEtudefse_FK` FOREIGN KEY (`idNiveauEtude`) REFERENCES `niveauetudefse` (`idNiveauEtude`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formationtype`
--

LOCK TABLES `formationtype` WRITE;
/*!40000 ALTER TABLE `formationtype` DISABLE KEYS */;
INSERT INTO `formationtype` VALUES (1,'Sans diplôme',0,0,0),(2,'CEB',0,0,0),(3,'CE1D',0,0,0),(4,'CESDD',0,0,0),(5,'CESS / CQ / CE6P / 7P',0,0,0),(6,'Ens. Sec. Compl.',0,0,0),(7,'Bachelier / graduat',0,0,0),(8,'Master / Licence',0,0,0),(9,'Doctorat / Post-doctorat',0,0,0),(10,'Inconnu / non reconnu',0,0,0),(11,'Formation d’adulte qualifiante',1,0,0),(12,'Formation en alternance',1,0,0),(13,'Formation professionnalisante',1,0,0),(14,'Formation continuée',1,0,0),(15,'Autre',1,1,0);
/*!40000 ALTER TABLE `formationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frenchtest`
--

DROP TABLE IF EXISTS `frenchtest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frenchtest` (
  `idFrenchTest` int(11) NOT NULL AUTO_INCREMENT,
  `frenchTestName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idFrenchTest`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frenchtest`
--

LOCK TABLES `frenchtest` WRITE;
/*!40000 ALTER TABLE `frenchtest` DISABLE KEYS */;
INSERT INTO `frenchtest` VALUES (1,'ELAO',0),(2,'Positionnement',0),(3,'SELOR',0),(4,'Autre',0);
/*!40000 ALTER TABLE `frenchtest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `household`
--

DROP TABLE IF EXISTS `household`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `household` (
  `idHousehold` int(11) NOT NULL AUTO_INCREMENT,
  `householdNumberAdult` int(11) NOT NULL,
  `householdNumberChildren` int(11) NOT NULL,
  `householdGuardNeeded` tinyint(1) DEFAULT 0,
  `householdIspNeeded` tinyint(1) DEFAULT 0,
  `householdIspName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idHousehold`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `household`
--

--
-- Table structure for table `incometype`
--

DROP TABLE IF EXISTS `incometype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incometype` (
  `idIncomeType` int(11) NOT NULL AUTO_INCREMENT,
  `incomeTypeName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idIncomeType`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incometype`
--

LOCK TABLES `incometype` WRITE;
/*!40000 ALTER TABLE `incometype` DISABLE KEYS */;
INSERT INTO `incometype` VALUES (1,'Sans revenu',0),(2,'RIS',0),(3,'Mutuelle',0),(4,'Pension',0),(5,'A charge de proche ',0),(6,'Aide sociale financière ',0),(7,'AVIQ (allocation handicap) ',0),(8,'Travail : Salarié',0),(9,'Travail : Indépendant',0),(10,'Travail : Job d’étudiant',0),(11,'Allocation chômage',0),(12,'CPAS : art. 60 - Art. 61',0),(13,'Conjoint aidant',0),(14,'Autre',0);
/*!40000 ALTER TABLE `incometype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `idLanguage` int(11) NOT NULL AUTO_INCREMENT,
  `languageName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idLanguage`),
  UNIQUE KEY `languageName` (`languageName`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'Français',0),(2,'Espagnol',0),(3,'Anglais',0),(4,'Hindi /Ourdou',0),(5,'Arabe',0),(6,'Bengali',0),(7,'Portugais',0),(8,'Russe',0),(9,'Panjabi',0),(10,'Japonais',0),(11,'Haoussa',0),(12,'Javanais',0),(13,'Télougou',0),(14,'Chinois Wu',0),(15,'Malais',0),(16,'Coréen',0),(17,'Chinois Mandarin',0),(18,'Allemand',0),(19,'Chinois Cantonais',0),(20,'Marathi',0),(21,'Turc',0),(22,'Vietnamien',0),(23,'Tamoul',0),(24,'Italien',0),(25,'Persan',0),(26,'Thaï',0),(27,'Gujarati',0),(28,'Chinois Jing',0),(29,'Chinois Min',0),(30,'Polonais',0),(31,'Pachtou',0),(32,'Kannada',0),(33,'Chinois Xiang',0),(34,'Malayalam',0),(35,'Soundanais',0),(36,'Oriya ',0),(37,'Birman',0),(38,'Chinois Hakka',0),(39,'Ukrainien',0),(40,'Bhojpouri',0),(41,'Filipino ',0),(42,'Yorouba',0),(43,'Maithili',0),(44,'Ouzbek',0),(45,'Sindhi',0),(46,'Amharique',0),(47,'Peul (Fulani)',0),(48,'Roumain',0),(49,'Oromo',0),(50,'Igbo (Ibo)',0),(51,'Azéri',0),(52,'Awadhi',0),(53,'Chinois Gan',0),(54,'Visayan (Cibuano)',0),(55,'Néerlandais',0),(56,'Kurde',0),(57,'Malgache',0),(58,'Saraiki',0),(59,'Chittagonien',0),(60,'Chinois Zhouang',0),(61,'Khmer',0),(62,'Turkmène',0),(63,'Assamais',0),(64,'Madourais',0),(65,'Somali',0),(66,'Marwari',0),(67,'Magahi',0),(68,'Haryanvi',0),(69,'Hongrois',0),(70,'Chhattisgarhi',0),(71,'Grec',0),(72,'Chewa ',0),(73,'Deccan',0),(74,'Akan',0),(75,'Kazakh',0),(76,'Chinois Min Bei',0),(77,'Sylheti',0),(78,'Zoulou',0),(79,'Tchèque',0),(80,'Créole Haïtien',0),(81,'Géorgien',0),(82,'Dari',0),(83,'Albanais',0),(84,'Tigrina',0),(85,'Fond',0),(86,'Malenke',0),(87,'Zerma',0),(88,'Kirundi',0),(89,'Yemba',0),(90,'Berbère',0),(91,'Lingala',0),(92,'Baoulé',0),(93,'Morré',0),(94,'Arménien',0),(96,'Benin',0),(97,'Kinyarwanda',0),(98,'Serbe',0),(99,'Croate',0),(100,'Ewe',0),(101,'Swahili',0);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locomotionmean`
--

DROP TABLE IF EXISTS `locomotionmean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locomotionmean` (
  `idLocomotionMean` int(11) NOT NULL AUTO_INCREMENT,
  `locomotionMeanName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idLocomotionMean`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locomotionmean`
--

LOCK TABLES `locomotionmean` WRITE;
/*!40000 ALTER TABLE `locomotionmean` DISABLE KEYS */;
INSERT INTO `locomotionmean` VALUES (1,'Voiture',0),(2,'Vélo',0),(3,'Bus',0),(4,'Moto',0),(5,'Train',0),(6,'Autre',0);
/*!40000 ALTER TABLE `locomotionmean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `idLog` int(11) NOT NULL AUTO_INCREMENT,
  `logger` varchar(50) NOT NULL,
  `logMessage` varchar(1000) NOT NULL,
  `logDate` datetime NOT NULL,
  PRIMARY KEY (`idLog`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nationality`
--

DROP TABLE IF EXISTS `nationality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nationality` (
  `idPerson` int(11) NOT NULL,
  `idCountry` int(11) NOT NULL,
  PRIMARY KEY (`idPerson`,`idCountry`),
  KEY `nationality_Country0_FK` (`idCountry`),
  CONSTRAINT `nationality_Country0_FK` FOREIGN KEY (`idCountry`) REFERENCES `country` (`idCountry`),
  CONSTRAINT `nationality_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `niveauetudefse`
--

DROP TABLE IF EXISTS `niveauetudefse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveauetudefse` (
  `idNiveauEtude` int(11) NOT NULL AUTO_INCREMENT,
  `niveauEtudeName` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idNiveauEtude`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveauetudefse`
--

LOCK TABLES `niveauetudefse` WRITE;
/*!40000 ALTER TABLE `niveauetudefse` DISABLE KEYS */;
INSERT INTO `niveauetudefse` VALUES (1,'Max. 1er cycle du secondaire (CITE 0 à 2)',0),(2,'Max. enseignement post-secondaire non supérieur ',1),(3,'Enseignement supérieur (CITE 5 à 8)',1);
/*!40000 ALTER TABLE `niveauetudefse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `idOrganization` int(11) NOT NULL AUTO_INCREMENT,
  `organizationName` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`idOrganization`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'FOREM',0),(2,'FSE',0),(3,'MENA',0),(4,'ASOS',0);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other`
--

DROP TABLE IF EXISTS `other`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `other` (
  `idOther` int(11) NOT NULL AUTO_INCREMENT,
  `otherIncome` varchar(100) DEFAULT NULL,
  `otherSituation` varchar(100) DEFAULT NULL,
  `otherLanguageTest` varchar(100) DEFAULT NULL,
  `otherCivilStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idOther`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `per_drl`
--

DROP TABLE IF EXISTS `per_drl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_drl` (
  `idDriverLicense` int(11) NOT NULL,
  `idPerson` int(11) NOT NULL,
  `belgiumValid` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idDriverLicense`,`idPerson`),
  KEY `Per_DrL_Person0_FK` (`idPerson`),
  CONSTRAINT `Per_DrL_DriverLicense_FK` FOREIGN KEY (`idDriverLicense`) REFERENCES `driverlicense` (`idDriverLicense`),
  CONSTRAINT `Per_DrL_Person0_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `per_for`
--

DROP TABLE IF EXISTS `per_for`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_for` (
  `idFormation` int(11) NOT NULL,
  `idPerson` int(11) NOT NULL,
  PRIMARY KEY (`idFormation`,`idPerson`),
  KEY `Per_For_Person0_FK` (`idPerson`),
  CONSTRAINT `Per_For_Formation_FK` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`),
  CONSTRAINT `Per_For_Person0_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

-
--
-- Table structure for table `per_inc`
--

DROP TABLE IF EXISTS `per_inc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_inc` (
  `idIncomeType` int(11) NOT NULL,
  `idPerson` int(11) NOT NULL,
  PRIMARY KEY (`idIncomeType`,`idPerson`),
  KEY `Per_Inc_Person0_FK` (`idPerson`),
  CONSTRAINT `Per_Inc_IncomeType_FK` FOREIGN KEY (`idIncomeType`) REFERENCES `incometype` (`idIncomeType`),
  CONSTRAINT `Per_Inc_Person0_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

-

--
-- Table structure for table `per_lan`
--

DROP TABLE IF EXISTS `per_lan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_lan` (
  `idPerson` int(11) NOT NULL,
  `idLanguage` int(11) NOT NULL,
  `langCom` tinyint(1) NOT NULL,
  `langLevel` varchar(50) NOT NULL,
  PRIMARY KEY (`idPerson`,`idLanguage`),
  KEY `Per_Lan_Language0_FK` (`idLanguage`),
  CONSTRAINT `Per_Lan_Language0_FK` FOREIGN KEY (`idLanguage`) REFERENCES `language` (`idLanguage`),
  CONSTRAINT `Per_Lan_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

-
--
-- Table structure for table `per_loc`
--

DROP TABLE IF EXISTS `per_loc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_loc` (
  `idLocomotionMean` int(11) NOT NULL,
  `idPerson` int(11) NOT NULL,
  PRIMARY KEY (`idLocomotionMean`,`idPerson`),
  KEY `Per_Loc_Person0_FK` (`idPerson`),
  CONSTRAINT `Per_Loc_LocomotionMean_FK` FOREIGN KEY (`idLocomotionMean`) REFERENCES `locomotionmean` (`idLocomotionMean`),
  CONSTRAINT `Per_Loc_Person0_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `per_skill`
--

DROP TABLE IF EXISTS `per_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_skill` (
  `idSkill` int(11) NOT NULL,
  `idPerson` int(11) NOT NULL,
  `skillLvl` varchar(50) NOT NULL,
  PRIMARY KEY (`idSkill`,`idPerson`),
  KEY `Per_Skill_Person0_FK` (`idPerson`),
  CONSTRAINT `Per_Skill_Person0_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`),
  CONSTRAINT `Per_Skill_Skill_FK` FOREIGN KEY (`idSkill`) REFERENCES `skill` (`idSkill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_skill`
--

LOCK TABLES `per_skill` WRITE;
/*!40000 ALTER TABLE `per_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `per_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `idPerson` int(11) NOT NULL AUTO_INCREMENT,
  `personFirstName` varchar(50) DEFAULT '',
  `personLastName` varchar(50) DEFAULT '',
  `personNISS` varchar(50) DEFAULT '',
  `personAddress` varchar(100) DEFAULT '',
  `personPhone` varchar(100) DEFAULT '',
  `personBirthDate` date DEFAULT NULL,
  `personMail` varchar(100) DEFAULT '',
  `personGender` varchar(10) DEFAULT '',
  `personArrivalDate` date DEFAULT NULL,
  `personHealthcare` varchar(50) DEFAULT '',
  `personNewsLetterWork` tinyint(1) DEFAULT 0,
  `personUnemployementDuration` varchar(50) DEFAULT '',
  `personForemInsDate` date DEFAULT NULL,
  `personOrientation` varchar(50) DEFAULT '',
  `personOrientationNote` varchar(100) DEFAULT '',
  `personWorkAccess` tinyint(4) DEFAULT 0,
  `personNotes` varchar(100) DEFAULT '',
  `personNotepad` text DEFAULT '',
  `personHasVehicle` tinyint(1) DEFAULT 0,
  `personIsDelete` tinyint(1) NOT NULL DEFAULT 0,
  `idSituationTerritory` int(11) DEFAULT NULL,
  `idCivilStatus` int(11) DEFAULT NULL,
  `idOther` int(11) DEFAULT NULL,
  `idDisability` int(11) DEFAULT NULL,
  `idEmployee` int(11) DEFAULT NULL,
  `idFile` int(11) DEFAULT NULL,
  `idFrenchTest` int(11) DEFAULT NULL,
  `idHousehold` int(11) DEFAULT NULL,
  `idWorkSearch` int(11) DEFAULT NULL,
  `idCountry` int(11) DEFAULT NULL,
  `idCountry_reunionNationality` int(11) DEFAULT NULL,
  `personIsBelgian` tinyint(1) NOT NULL DEFAULT 0,
  `idCity` int(11) DEFAULT NULL,
  `idSituationProf` int(11) DEFAULT NULL,
  `idDipa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPerson`),
  KEY `Person_SituationTerritory_FK` (`idSituationTerritory`),
  KEY `Person_SituationProf_FK` (`idSituationProf`),
  KEY `Person_civilStatus0_FK` (`idCivilStatus`),
  KEY `Person_Household2_FK` (`idHousehold`),
  KEY `Person_workSearch3_FK` (`idWorkSearch`),
  KEY `Person_Country4_FK` (`idCountry`),
  KEY `Person_Country5_FK` (`idCountry_reunionNationality`),
  KEY `Person_City6_FK` (`idCity`),
  KEY `Person_OtherIncome7_FK` (`idOther`),
  KEY `idDisability` (`idDisability`),
  KEY `PK_Employee9` (`idEmployee`),
  KEY `PK_File10` (`idFile`),
  KEY `PK_FrenchTest11` (`idFrenchTest`),
  KEY `person_dipa_FK` (`idDipa`),
  CONSTRAINT `PK_Employee9` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`idEmployee`),
  CONSTRAINT `PK_File10` FOREIGN KEY (`idFile`) REFERENCES `file` (`idFile`),
  CONSTRAINT `PK_FrenchTest11` FOREIGN KEY (`idFrenchTest`) REFERENCES `frenchtest` (`idFrenchTest`),
  CONSTRAINT `Person_City6_FK` FOREIGN KEY (`idCity`) REFERENCES `city` (`idCity`),
  CONSTRAINT `Person_Country4_FK` FOREIGN KEY (`idCountry`) REFERENCES `country` (`idCountry`),
  CONSTRAINT `Person_Country5_FK` FOREIGN KEY (`idCountry_reunionNationality`) REFERENCES `country` (`idCountry`),
  CONSTRAINT `Person_Household2_FK` FOREIGN KEY (`idHousehold`) REFERENCES `household` (`idHousehold`),
  CONSTRAINT `Person_OtherIncome7_FK` FOREIGN KEY (`idOther`) REFERENCES `other` (`idOther`),
  CONSTRAINT `Person_SituationProf_FK` FOREIGN KEY (`idSituationProf`) REFERENCES `situationprof` (`idSituationProf`),
  CONSTRAINT `Person_SituationTerritory_FK` FOREIGN KEY (`idSituationTerritory`) REFERENCES `situationterritory` (`idSituationTerritory`),
  CONSTRAINT `Person_civilStatus0_FK` FOREIGN KEY (`idCivilStatus`) REFERENCES `civilstatus` (`idCivilStatus`),
  CONSTRAINT `Person_workSearch3_FK` FOREIGN KEY (`idWorkSearch`) REFERENCES `worksearch` (`idWorkSearch`),
  CONSTRAINT `person_dipa_FK` FOREIGN KEY (`idDipa`) REFERENCES `dipa` (`idDipa`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`idDisability`) REFERENCES `disability` (`idDisability`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER trigger_person_insert
BEFORE INSERT
ON person FOR EACH ROW
begin 
	DECLARE lastId INT DEFAULT 0;
	
	if(new.idHousehold is NULL) then 
		INSERT INTO household (householdNumberAdult,householdNumberChildren) VALUES (0,0);
		SELECT last_insert_id() into lastId;
		set new.idHousehold=lastId;
	end if;

	if(new.idFile is NULL) then
		INSERT INTO file (file.registrationDate,file.modificationDate) VALUES (CURRENT_DATE(),CURRENT_DATE());
		SELECT last_insert_id() into lastId;
		set new.idFile=lastId;
	end if;

	if(new.idWorkSearch is NULL) then
		INSERT INTO worksearch (worksearch.isSearching) VALUES (0);
		SELECT last_insert_id() into lastId;
		set new.idWorkSearch=lastId;
	end if;

	if(new.idDisability is NULL) then
		INSERT INTO disability (disReco,disOther) VALUES (0,"");
		SELECT last_insert_id() into lastId;
		set new.idDisability=lastId;
	end if;

	if(new.idOther is NULL) then
		INSERT INTO other (otherIncome) VALUES (null);
		SELECT last_insert_id() into lastId;
		set new.idOther=lastId;
	end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER trigger_person_after_insert
AFTER INSERT
ON person FOR EACH ROW
begin 
	INSERT INTO person_history (idPerson,personFirstName,personLastName,personPhone,personAddress,personMail,idCity,idEmployee,actionType) 
		VALUES (new.idPerson,new.personFirstName,new.personLastName,new.personPhone,new.personAddress,new.personMail,new.idCity,new.idEmployee,'INSERT');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER trigger_person_update
BEFORE UPDATE
ON person FOR EACH ROW
BEGIN
	if(old.personPhone!=new.personPhone OR old.personFirstName!=new.personFirstName
	OR old.personLastName!=new.personLastName OR old.personAddress!=new.personAddress 
	OR old.personMail!=new.personMail OR old.idCity!=new.idCity OR old.idEmployee!=new.idEmployee) then
		INSERT INTO person_history (idPerson,personFirstName,personLastName,personPhone,personAddress,personMail,idCity,idEmployee,actionType) 
		VALUES (new.idPerson,new.personFirstName,new.personLastName,new.personPhone,new.personAddress,new.personMail,new.idCity,new.idEmployee,'UPDATE');
	end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 */ /*!50003 TRIGGER trigger_person_delete
AFTER DELETE
ON person FOR EACH ROW
begin 
	INSERT INTO person_history (idPerson,actionType) 
		VALUES (old.idPerson,'DELETE');	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `person_history`
--

DROP TABLE IF EXISTS `person_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_history` (
  `idHistorique` int(11) NOT NULL AUTO_INCREMENT,
  `idPerson` int(11) DEFAULT NULL,
  `dateTimeChange` datetime DEFAULT current_timestamp(),
  `actionType` varchar(20) DEFAULT NULL,
  `personLastName` varchar(50) DEFAULT NULL,
  `personFirstName` varchar(50) DEFAULT NULL,
  `personAddress` varchar(100) DEFAULT NULL,
  `personPhone` varchar(50) DEFAULT NULL,
  `personMail` varchar(50) DEFAULT NULL,
  `idCity` int(11) DEFAULT NULL,
  `idEmployee` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHistorique`),
  KEY `idPerson` (`idPerson`),
  KEY `oldIdCity` (`idCity`)
) ENGINE=InnoDB AUTO_INCREMENT=2468 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `postalcode`
--

DROP TABLE IF EXISTS `postalcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postalcode` (
  `idPostalCode` int(11) NOT NULL AUTO_INCREMENT,
  `postalCodeNumber` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPostalCode`),
  UNIQUE KEY `postalCodeNumber` (`postalCodeNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postalcode`
--

LOCK TABLES `postalcode` WRITE;
/*!40000 ALTER TABLE `postalcode` DISABLE KEYS */;
INSERT INTO `postalcode` VALUES (1,'4000',0),(2,'4020',0),(3,'4030',0),(4,'4031',0),(5,'4032',0),(6,'4040',0),(7,'4041',0),(8,'4042',0),(9,'4050',0),(10,'4051',0),(11,'4052',0),(12,'4053',0),(13,'4100',0),(14,'4101',0),(15,'4102',0),(16,'4120',0),(17,'4121',0),(18,'4122',0),(19,'4130',0),(20,'4140',0),(21,'4141',0),(22,'4160',0),(23,'4161',0),(24,'4162',0),(25,'4163',0),(26,'4170',0),(27,'4171',0),(28,'4180',0),(29,'4181',0),(30,'4190',0),(31,'4210',0),(32,'4217',0),(33,'4218',0),(34,'4219',0),(35,'4250',0),(36,'4252',0),(37,'4253',0),(38,'4254',0),(39,'4257',0),(40,'4260',0),(41,'4261',0),(42,'4263',0),(43,'4280',0),(44,'4287',0),(45,'4300',0),(46,'4317',0),(47,'4340',0),(48,'4342',0),(49,'4347',0),(50,'4350',0),(51,'4351',0),(52,'4357',0),(53,'4360',0),(54,'4367',0),(55,'4400',0),(56,'4420',0),(57,'4430',0),(58,'4431',0),(59,'4432',0),(60,'4450',0),(61,'4451',0),(62,'4452',0),(63,'4453',0),(64,'4458',0),(65,'4460',0),(66,'4470',0),(67,'4480',0),(68,'4500',0),(69,'4520',0),(70,'4530',0),(71,'4537',0),(72,'4540',0),(73,'4550',0),(74,'4557',0),(75,'4560',0),(76,'4570',0),(77,'4577',0),(78,'4590',0),(79,'4600',0),(80,'4601',0),(81,'4602',0),(82,'4606',0),(83,'4607',0),(84,'4608',0),(85,'4610',0),(86,'4620',0),(87,'4621',0),(88,'4623',0),(89,'4624',0),(90,'4630',0),(91,'4631',0),(92,'4632',0),(93,'4633',0),(94,'4650',0),(95,'4651',0),(96,'4652',0),(97,'4653',0),(98,'4654',0),(99,'4670',0),(100,'4671',0),(101,'4672',0),(102,'4680',0),(103,'4681',0),(104,'4682',0),(105,'4683',0),(106,'4684',0),(107,'4690',0),(108,'4700',0),(109,'4701',0),(110,'4710',0),(111,'4711',0),(112,'4720',0),(113,'4721',0),(114,'4728',0),(115,'4730',0),(116,'4731',0),(117,'4750',0),(118,'4760',0),(119,'4761',0),(120,'4770',0),(121,'4771',0),(122,'4780',0),(123,'4782',0),(124,'4783',0),(125,'4784',0),(126,'4790',0),(127,'4791',0),(128,'4800',0),(129,'4801',0),(130,'4802',0),(131,'4820',0),(132,'4821',0),(133,'4830',0),(134,'4831',0),(135,'4834',0),(136,'4837',0),(137,'4840',0),(138,'4841',0),(139,'4845',0),(140,'4850',0),(141,'4851',0),(142,'4852',0),(143,'4860',0),(144,'4861',0),(145,'4870',0),(146,'4877',0),(147,'4880',0),(148,'4890',0),(149,'4900',0),(150,'4910',0),(151,'4920',0),(152,'4950',0),(153,'4960',0),(154,'4970',0),(155,'4980',0),(156,'4983',0),(157,'4987',0),(158,'4990',0),(159,'3770',0);
/*!40000 ALTER TABLE `postalcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `required`
--

DROP TABLE IF EXISTS `required`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `required` (
  `idRequired` int(11) NOT NULL AUTO_INCREMENT,
  `requiredName` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idRequired`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `required`
--

LOCK TABLES `required` WRITE;
/*!40000 ALTER TABLE `required` DISABLE KEYS */;
INSERT INTO `required` VALUES (1,'Obtention d\'une qualification reconnue',0),(2,'Attestation de réussite',0),(3,'Attestation de participation',0),(4,'Sans objet',0);
/*!40000 ALTER TABLE `required` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residencepermit`
--

DROP TABLE IF EXISTS `residencepermit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `residencepermit` (
  `idResidencePermit` int(11) NOT NULL AUTO_INCREMENT,
  `residencePermitIssueDate` date DEFAULT NULL,
  `residencePermitValidityDate` date DEFAULT NULL,
  `residencePermitAnnex` varchar(50) DEFAULT NULL,
  `idPerson` int(11) NOT NULL,
  `idResidencePermitType` int(11) NOT NULL,
  PRIMARY KEY (`idResidencePermit`),
  UNIQUE KEY `ResidencePermit_Person_AK` (`idPerson`),
  UNIQUE KEY `UKqhxxq5rg2b5r1ep4gq69mi20o` (`idPerson`),
  KEY `ResidencePermit_ResidencePermitType0_FK` (`idResidencePermitType`),
  CONSTRAINT `ResidencePermit_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`),
  CONSTRAINT `ResidencePermit_ResidencePermitType0_FK` FOREIGN KEY (`idResidencePermitType`) REFERENCES `residencepermittype` (`idResidencePermitType`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `residencepermittype`
--

DROP TABLE IF EXISTS `residencepermittype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `residencepermittype` (
  `idResidencePermitType` int(11) NOT NULL AUTO_INCREMENT,
  `residencePermitTypeName` varchar(50) NOT NULL,
  `residencePermitNeedAnnex` tinyint(4) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idResidencePermitType`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residencepermittype`
--

LOCK TABLES `residencepermittype` WRITE;
/*!40000 ALTER TABLE `residencepermittype` DISABLE KEYS */;
INSERT INTO `residencepermittype` VALUES (1,'Carte A',0,0),(2,'Carte B',0,0),(3,'Carte C',0,0),(4,'Carte D',0,0),(5,'Carte E',0,0),(6,'Carte E+',0,0),(7,'Carte EU',0,0),(8,'Carte EU +',0,0),(9,'Carte F',0,0),(10,'Carte F+',0,0),(11,'Carte K',0,0),(12,'Carte L',0,0),(13,'Carte M',0,0),(14,'Carte N',0,0),(15,'Carte belge',0,0),(16,'Attestation d’immatriculation',0,0),(17,'Déclaration d’arrivée / présence',1,0),(18,'Sans titre de séjour',0,0),(19,'Autre',1,0),(20,'Annexe',1,1);
/*!40000 ALTER TABLE `residencepermittype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reunion`
--

DROP TABLE IF EXISTS `reunion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reunion` (
  `idPerson` int(11) NOT NULL,
  `idFamilyReunion` int(11) NOT NULL,
  PRIMARY KEY (`idPerson`,`idFamilyReunion`),
  KEY `reunion_FamilyReunion0_FK` (`idFamilyReunion`),
  CONSTRAINT `reunion_FamilyReunion0_FK` FOREIGN KEY (`idFamilyReunion`) REFERENCES `familyreunion` (`idFamilyReunion`),
  CONSTRAINT `reunion_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sea_avai`
--

DROP TABLE IF EXISTS `sea_avai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sea_avai` (
  `idWorkSearch` int(11) NOT NULL,
  `idAvailability` int(11) NOT NULL,
  PRIMARY KEY (`idWorkSearch`,`idAvailability`),
  KEY `Sea_Avai_Availability0_FK` (`idAvailability`),
  CONSTRAINT `Sea_Avai_Availability0_FK` FOREIGN KEY (`idAvailability`) REFERENCES `availability` (`idAvailability`),
  CONSTRAINT `Sea_Avai_workSearch_FK` FOREIGN KEY (`idWorkSearch`) REFERENCES `worksearch` (`idWorkSearch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sea_sec`
--

DROP TABLE IF EXISTS `sea_sec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sea_sec` (
  `idWorkSearch` int(11) NOT NULL,
  `idWorkSector` int(11) NOT NULL,
  PRIMARY KEY (`idWorkSearch`,`idWorkSector`),
  KEY `Sea_Sec_WorkSector0_FK` (`idWorkSector`),
  CONSTRAINT `Sea_Sec_WorkSector0_FK` FOREIGN KEY (`idWorkSector`) REFERENCES `worksector` (`idWorkSector`),
  CONSTRAINT `Sea_Sec_workSearch_FK` FOREIGN KEY (`idWorkSearch`) REFERENCES `worksearch` (`idWorkSearch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `situationprof`
--

DROP TABLE IF EXISTS `situationprof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `situationprof` (
  `idSituationProf` int(11) NOT NULL AUTO_INCREMENT,
  `situationProfName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSituationProf`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situationprof`
--

LOCK TABLES `situationprof` WRITE;
/*!40000 ALTER TABLE `situationprof` DISABLE KEYS */;
INSERT INTO `situationprof` VALUES (1,'Travailleur Salarié',0),(2,'Travailleur indépendant',0),(3,'Demandeur demploi (DE)',0),(4,'DE CPAS (y compris art. 60 et 61)',0),(5,'Inactif enseignement alternance',0),(6,'Inactif enseignement',0),(7,'Inactif autre',0);
/*!40000 ALTER TABLE `situationprof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situationterritory`
--

DROP TABLE IF EXISTS `situationterritory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `situationterritory` (
  `idSituationTerritory` int(11) NOT NULL AUTO_INCREMENT,
  `situationTerritoryName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSituationTerritory`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situationterritory`
--

LOCK TABLES `situationterritory` WRITE;
/*!40000 ALTER TABLE `situationterritory` DISABLE KEYS */;
INSERT INTO `situationterritory` VALUES (1,'Nationalité Belge',0),(2,'Asile',0),(3,'Etudiant',0),(4,'Travail',0),(5,'Regroupement familial',0),(6,'Né en Belgique',0),(7,'UE',0),(8,'MENA',0),(9,'Protection subsidiaire',0),(10,'T.E.H',0),(11,'Résidant longue durée',0),(12,'Irrégularité',0),(13,'Régularisation (9bis ou 9ter)',0),(14,'Autre',0);
/*!40000 ALTER TABLE `situationterritory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skil_sect`
--

DROP TABLE IF EXISTS `skil_sect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skil_sect` (
  `idWorkSector` int(11) NOT NULL,
  `idSkill` int(11) NOT NULL,
  `skilLvl` varchar(50) NOT NULL,
  PRIMARY KEY (`idWorkSector`,`idSkill`),
  KEY `Skil_Sect_Skill0_FK` (`idSkill`),
  CONSTRAINT `Skil_Sect_Skill0_FK` FOREIGN KEY (`idSkill`) REFERENCES `skill` (`idSkill`),
  CONSTRAINT `Skil_Sect_WorkSector_FK` FOREIGN KEY (`idWorkSector`) REFERENCES `worksector` (`idWorkSector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skil_sect`
--

LOCK TABLES `skil_sect` WRITE;
/*!40000 ALTER TABLE `skil_sect` DISABLE KEYS */;
/*!40000 ALTER TABLE `skil_sect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill` (
  `idSkill` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idSkill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `idTheme` int(11) NOT NULL AUTO_INCREMENT,
  `themeName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTheme`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'Adoption',0),(2,'Alpha',0),(3,'Logement',0),(4,'ISP',0),(5,'Permis de conduire',0),(6,'Regroupement familial',0),(7,'Séjour',0),(8,'Validation de compétences',0),(9,'Etude',0),(10,'Equivalence',0),(11,'FLE',0),(12,'Formation',0),(13,'Permis de travail',0),(14,'Sécurité sociale',0),(15,'Mariage/Cohabitation légale',0),(16,'Nationalité',0),(17,'Recherche emploi',0),(18,'Visa',0),(19,'Autre',0);
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `userPassword` varchar(200) NOT NULL,
  `userIsDelete` tinyint(1) NOT NULL,
  `idUserLevel` int(11) NOT NULL,
  `idEmployee` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idEmployee` (`idEmployee`),
  KEY `User_UserLevel_FK` (`idUserLevel`),
  KEY `User_Employee0_FK` (`idEmployee`),
  CONSTRAINT `User_Employee0_FK` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`idEmployee`),
  CONSTRAINT `User_UserLevel_FK` FOREIGN KEY (`idUserLevel`) REFERENCES `userlevel` (`idUserLevel`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Administrateur','fb0d20e05d4ec4f06eb0514801cae15dff717e195d3c3a13f1e9566f4828b042abeb1064aafd8f33',0,1,1),(2,'HON','d3a1394ec4164a53b25145f432773b81ae10d88272d806196aaf70308c6b6899130f2c534c076d5b',0,2,2),(3,'CEL','8d1ca09773c771462e685dad72cf263b93b091056a244e924dc1023a8393e16e8084a7aca669668a',0,2,3),(4,'MAR','bb1ade67aa0998f4d39a46c17b5de69f75a0edb9eb8fe5cc757155bf10c393f486781a94cf36be10',0,2,4),(5,'SAN','a097303ad257c088cda9b8d769e8e70c5448c205b9e3e0db394583c2abb6daa7b619775149064c71',0,2,5),(6,'JOR','04325c119a6f25778c25fd583f40b5347845314d43356da7de73329ae747e7099e674148eb107abe',0,2,6),(7,'CIN','00ed3751c54b800364febefcbf5f61bdf7bf2287ef36acdaf840bb5b4aeb93045281419c2013ed7d',0,2,7),(8,'TAM','2c93f7426cdc882932976065aa3477ae81d5ea9381e4876610ef6f2ce658d329371ed4d4acfea532',0,2,8),(9,'MNA','df576bb48cd56987eb91456075f3bafc5acc1e9c09d546a4e8cb5102ee1b4fb9cd1a70cd6161c155',0,2,9),(10,'X2','197b5d5eb81f0209d6379602c0b7f630c5874388077c745d08f60fbb4ebb5997f8f1d20aaebe7083',0,2,10),(11,'X1','b935eb927fdf8d82ceae0b30f827afb110ffdac989da35c4b977dd0d9a94d38bc56c6cf8a81844c1',0,1,11);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlevel`
--

DROP TABLE IF EXISTS `userlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlevel` (
  `idUserLevel` int(11) NOT NULL AUTO_INCREMENT,
  `userLevelName` varchar(50) NOT NULL,
  PRIMARY KEY (`idUserLevel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlevel`
--

LOCK TABLES `userlevel` WRITE;
/*!40000 ALTER TABLE `userlevel` DISABLE KEYS */;
INSERT INTO `userlevel` VALUES (1,'Administrateur'),(2,'Utilisateur'),(3,'Editeur');
/*!40000 ALTER TABLE `userlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workexperience`
--

DROP TABLE IF EXISTS `workexperience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workexperience` (
  `idWorkExp` int(11) NOT NULL AUTO_INCREMENT,
  `workExpName` varchar(100) NOT NULL,
  `workExpDurationMonth` int(11) NOT NULL DEFAULT 0,
  `workExpBelgium` tinyint(1) DEFAULT NULL,
  `workSector` varchar(100) NOT NULL,
  `idPerson` int(11) NOT NULL,
  `idWorkExpType` int(11) NOT NULL,
  PRIMARY KEY (`idWorkExp`),
  KEY `WorkExperience_Person_FK` (`idPerson`),
  KEY `WorkExperience_WorkExpType0_FK` (`idWorkExpType`),
  CONSTRAINT `WorkExperience_Person_FK` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`),
  CONSTRAINT `WorkExperience_WorkExpType0_FK` FOREIGN KEY (`idWorkExpType`) REFERENCES `workexptype` (`idWorkExpType`)
) ENGINE=InnoDB AUTO_INCREMENT=1362 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `workexptype`
--

DROP TABLE IF EXISTS `workexptype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workexptype` (
  `idWorkExpType` int(11) NOT NULL AUTO_INCREMENT,
  `workExpTypeName` varchar(50) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idWorkExpType`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workexptype`
--

LOCK TABLES `workexptype` WRITE;
/*!40000 ALTER TABLE `workexptype` DISABLE KEYS */;
INSERT INTO `workexptype` VALUES (1,'Stage',0),(2,'Job d\'étudiant',0),(3,'Emploi \"ordinaire\"',0),(4,'Pas de XP Pro',0),(5,'Non renseigné',0);
/*!40000 ALTER TABLE `workexptype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worksearch`
--

DROP TABLE IF EXISTS `worksearch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worksearch` (
  `idWorkSearch` int(11) NOT NULL AUTO_INCREMENT,
  `isSearching` tinyint(1) DEFAULT 0,
  `workSearchAnnex` varchar(100) DEFAULT '',
  `workSearchOtherAvailability` varchar(100) DEFAULT '',
  PRIMARY KEY (`idWorkSearch`)
) ENGINE=InnoDB AUTO_INCREMENT=1977 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `worksector`
--

DROP TABLE IF EXISTS `worksector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worksector` (
  `idWorkSector` int(11) NOT NULL AUTO_INCREMENT,
  `workSectorName` varchar(100) NOT NULL,
  `isDelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`idWorkSector`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worksector`
--

LOCK TABLES `worksector` WRITE;
/*!40000 ALTER TABLE `worksector` DISABLE KEYS */;
INSERT INTO `worksector` VALUES (1,'Activités des ménages',0),(2,'Activités extra-territoriales',0),(3,'Activités financières',0),(4,'Agriculture, chasse, sylviculture',0),(5,'Commerce; réparations automobiles et d’articles domestiques',0),(6,'Construction',0),(7,'Éducation et formation',0),(8,'Horeca',0),(9,'Industries extractives',0),(10,'Industries manufacturières',0),(11,'Pêche, Aquaculture',0),(12,'Production et distribution d’électricité, de gaz et d’eau',0),(13,'Santé et action sociale',0),(14,'Services aux entreprises, immobilier et location',0),(15,'Services collectifs, sociaux et personnels',0),(16,'Services et administrations publiques',0),(17,'Transports et communications',0),(18,'Autre',0);
/*!40000 ALTER TABLE `worksector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worktask`
--

DROP TABLE IF EXISTS `worktask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worktask` (
  `idWorkTask` int(11) NOT NULL AUTO_INCREMENT,
  `workTaskDescription` text NOT NULL,
  `idWorkExp` int(11) NOT NULL,
  PRIMARY KEY (`idWorkTask`),
  KEY `workTask_WorkExperience_FK` (`idWorkExp`),
  CONSTRAINT `workTask_WorkExperience_FK` FOREIGN KEY (`idWorkExp`) REFERENCES `workexperience` (`idWorkExp`)
) ENGINE=InnoDB AUTO_INCREMENT=471 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping routines for database 'jobway'
--
/*!50003 DROP PROCEDURE IF EXISTS `employeeCountHour` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE  PROCEDURE `employeeCountHour`(beginDate DATE,endDate DATE)
BEGIN

	

	SELECT idEmployee, empPseudo, sum(eventDuration)as Total_Heure_Unique FROM Event NATURAL JOIN emp_eve NATURAL JOIN employee WHERE idEvent IN(

	SELECT idEvent from emp_eve group by idEvent Having count(idEmployee)=1) AND eventDate>=beginDate AND eventDate<=endDate group by idEmployee;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `employeeCountSharedHour` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE  PROCEDURE `employeeCountSharedHour`(beginDate DATE,endDate DATE)
BEGIN

	SELECT idEmployee, empPseudo, sum(eventDuration)as Total_Heure_Partagee FROM Event NATURAL JOIN emp_eve NATURAL JOIN employee WHERE idEvent IN(

	SELECT idEvent from emp_eve group by idEvent Having count(idEmployee)>1) AND eventDate>=beginDate AND eventDate<=endDate group by idEmployee;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `personCountHour` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE  PROCEDURE `personCountHour`(beginDate DATE,endDate DATE)
BEGIN

	SELECT idPerson,personFirstName,personLastName, sum(eventDuration) 

	FROM event natural join person WHERE eventDate>=beginDate AND eventDate<=endDate GROUP BY idPerson;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-03 10:09:26

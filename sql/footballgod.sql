
CREATE TABLE CoachSpecialty (
                CoachSpecialtyId INT AUTO_INCREMENT NOT NULL,
                CoachSpecialtyName VARCHAR(20) NOT NULL,
                PRIMARY KEY (CoachSpecialtyId)
);


CREATE TABLE Coach (
                CoachId INT AUTO_INCREMENT NOT NULL,
                CoachName VARCHAR(50) NOT NULL,
                CoachValue INT NOT NULL,
                CoachSpecialtyId INT NOT NULL,
                CoachTier INT NOT NULL,
                CoachPicture LONGBLOB,
                PRIMARY KEY (CoachId)
);


CREATE TABLE PlayerPosition (
                PlayerPositionId INT AUTO_INCREMENT NOT NULL,
                PlayerPositionName VARCHAR(30) NOT NULL,
                PRIMARY KEY (PlayerPositionId)
);


CREATE TABLE Player (
                PlayerId INT AUTO_INCREMENT NOT NULL,
                PlayerName VARCHAR(50) NOT NULL,
                PlayerValue INT NOT NULL,
                PlayerHeightFeet INT NOT NULL,
                PlayerHeightInches INT NOT NULL,
                PlayerHeightWeight INT NOT NULL,
                YearsExperience INT NOT NULL,
                PlayerCollege VARCHAR(50),
                PlayerPositionId INT NOT NULL,
                PRIMARY KEY (PlayerId)
);


CREATE TABLE Team (
                TeamId INT AUTO_INCREMENT NOT NULL,
                PlayerId INT NOT NULL,
                PRIMARY KEY (TeamId)
);


CREATE TABLE TakeOverOrganization (
                TakeOverOrganizationId INT AUTO_INCREMENT NOT NULL,
                TakeOverOrganizationName VARCHAR(30) NOT NULL,
                OrganizationSalaryCap INT NOT NULL,
                Picture LONGBLOB,
                PRIMARY KEY (TakeOverOrganizationId)
);


CREATE TABLE Owner (
                OwnerId INT AUTO_INCREMENT NOT NULL,
                OwnerName VARCHAR(50) NOT NULL,
                OrganizationCity VARCHAR(50) NOT NULL,
                OrganizationName VARCHAR(50) NOT NULL,
                TakeOverOrganizationId INT NOT NULL,
                CoachId INT NOT NULL,
                TeamId INT NOT NULL,
                PRIMARY KEY (OwnerId)
);


ALTER TABLE Coach ADD CONSTRAINT coachspecialty_coach_fk
FOREIGN KEY (CoachSpecialtyId)
REFERENCES CoachSpecialty (CoachSpecialtyId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Owner ADD CONSTRAINT coach_owner_fk
FOREIGN KEY (CoachId)
REFERENCES Coach (CoachId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Player ADD CONSTRAINT playerposition_player_fk
FOREIGN KEY (PlayerPositionId)
REFERENCES PlayerPosition (PlayerPositionId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Team ADD CONSTRAINT player_team_fk
FOREIGN KEY (PlayerId)
REFERENCES Player (PlayerId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Owner ADD CONSTRAINT team_owner_fk
FOREIGN KEY (TeamId)
REFERENCES Team (TeamId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Owner ADD CONSTRAINT takeoverorganization_owner_fk
FOREIGN KEY (TakeOverOrganizationId)
REFERENCES TakeOverOrganization (TakeOverOrganizationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

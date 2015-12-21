set escape '\';

-- CLEANUP

DELETE FROM "EAT"."M_FORM_FIELD" WHERE FORM_NAME = 'Observation';
DELETE FROM "EAT"."M_FORM_BUTTON" WHERE FORM_NAME = 'Observation';
DELETE FROM "EAT"."M_FORM" WHERE FORM_NAME = 'Observation';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC LIKE 'field.inwi.observation.%';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC LIKE 'btn.inwi.observation.%';


-- FORM

INSERT INTO "EAT"."M_FORM" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT) 
VALUES ('Observation', 'Observation', 'srms.onair.forms.inwi.ObservationFormService', 'GRANITE', 'model.inwi.Observation', 'nomSiteInstId', 'INWI', '2');


-- FORM FIELDS SICS

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'nomSiteInstId', 'Nominal Site Inst Id', 'nomSiteInstId', 'field.empty', 'N', 10, 0, 0, 0, 'Y', 51, 'H', 'Y', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'nomSiteHumId', 'Nominal Site Humain Id', 'nomSiteHumId', 'field.empty', 'N', 0, 0, 0, 0, 'N', 52, 'H', 'Y', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'siteInstId', 'Site Inst Id', 'siteInstId', 'field.empty', 'N', 10, 0, 0, 0, 'N', 53, 'H', 'Y', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'ngoSrmsInstId', 'NGO Srms Inst Id', 'ngoSrmsInstId', 'field.empty', 'N', 10, 0, 0, 0, 'N', 54, 'H', 'Y', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'observationName', 'Observation Nom', 'observationName', 'field.inwi.observation.observationname', 'Y', 0, 0, 0, 0, 'N', 55, 'H', 'N', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'observationPhone', 'Observation Phone', 'observationPhone', 'field.inwi.observation.observationphone', 'Y', 0, 0, 0, 0, 'N', 56, 'H', 'N', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'observationEmail', 'Observation Email', 'observationEmail', 'field.inwi.observation.observationemail', 'Y', 0, 0, 0, 0, 'N', 57, 'H', 'N', '*', '*', 'INWI', 140, 0, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)
VALUES
('Observation', 'miseEnObservationLabel', 'Date Observation', 'miseEnObservation', 'field.inwi.observation.m312miseenobservation', 'Y', 0, 99, 8, 'Y', 10, 80, 'INWI');
INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'miseEnObservation', 'Date Observation', 'miseEnObservation', 'field.inwi.observation.m312miseenobservation', 'Y', 4, 4, 0, 0, 'N', 58, 'Y', 'N', '*', '*', 'INWI', 150, 90, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)
VALUES
('Observation', 'observationIssuesLabel', 'Nombre des réserves lors de l\\''observation', 'observationIssues', 'field.inwi.observation.observationissues', 'Y', 0, 99, 9, 'Y', 310, 80, 'INWI');
INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'observationIssues', 'Nombre des réserves lors de l\\''observation', 'observationIssues', 'field.inwi.observation.observationissues', 'Y', 0, 2, 0, 0, 'N', 59, 'Y', 'N', '*', '*', 'INWI', 450, 90, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)
VALUES
('Observation', 'cleanupRadio2NameLabel', 'Nom du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Name', 'field.inwi.observation.cleanupradio2name', 'N', 0, 99, 10, 'Y', 10, 0, 'INWI');
INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'cleanupRadio2Name', 'Nom du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Name', 'field.inwi.observation.cleanupradio2name', 'N', 0, 0, 0, 0, 'N', 60, 'Y', 'Y', '*', '*', 'INWI', 150, 10, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)
VALUES
('Observation', 'cleanupRadio2PhoneLabel', 'Téléphone du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Phone', 'field.inwi.observation.cleanupradio2phone', 'N', 0, 99, 11, 'Y', 310, 0, 'INWI');
INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'cleanupRadio2Phone', 'Téléphone du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Phone', 'field.inwi.observation.cleanupradio2phone', 'N', 0, 0, 0, 0, 'N', 61, 'Y', 'Y', '*', '*', 'INWI', 450, 10, 140, 20);

INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, PRESENTATION_INDEX, VISIBLE, POS_X, POS_Y, DOMAIN)
VALUES
('Observation', 'cleanupRadio2EmailLabel', 'Email du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Email', 'field.inwi.observation.cleanupradio2email', 'N', 0, 99, 12, 'Y', 610, 0, 'INWI');
INSERT INTO "EAT"."M_FORM_FIELD"
(FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, PK, PRESENTATION_INDEX, VISIBLE, READ_ONLY, UPDATE_PRIVILEGE, VIEW_PRIVILEGE, DOMAIN, POS_X, POS_Y, WIDTH, HEIGHT)
VALUES
('Observation', 'cleanupRadio2Email', 'Email du Resp. Clean up Radio supplémentaire', 'cleanupRadio2Email', 'field.inwi.observation.cleanupradio2email', 'N', 0, 0, 0, 0, 'N', 62, 'Y', 'Y', '*', '*', 'INWI', 750, 10, 140, 20);


-- FORM FIELDS SICS

INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.observationname', 'FR', 'Observation Nom', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.observationphone', 'FR', 'Observation Phone', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.observationemail', 'FR', 'Observation Email', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.m312miseenobservation', 'FR', 'Date Observation', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.observationissues', 'FR', 'Nombre des réserves lors de l\\''observation', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.cleanupradio2name', 'FR', 'Nom du Resp. Clean up Radio supplémentaire', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.cleanupradio2phone', 'FR', 'Téléphone du Resp. Clean up Radio supplémentaire', 'INWI');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'field.inwi.observation.cleanupradio2email', 'FR', 'Email du Resp. Clean up Radio supplémentaire', 'INWI');


-- FORM BUTTONS

INSERT INTO "EAT"."M_FORM_BUTTON"(BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) 
VALUES ('Observation_confirm','Observation','Confirm','javascript:confirm()', 'N', 1, 'btn.inwi.observation.ok');
INSERT INTO "EAT"."I_DICTIONARY" (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL, 'btn.inwi.observation.ok', 'FR', 'Confirm', 'INWI');



COMMIT;
quit;
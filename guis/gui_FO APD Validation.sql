-- FORM

INSERT INTO "EAT"."M_FORM" (FORM_NAME, FORM_DESCRIPTION, MANAGER_CLASS, ENTITY_MANAGER, CLASS_NAME, PK_NAME, DOMAIN, FORM_LAYOUT, COLUMNS) VALUES ('FOAPDValidationForm', 'FO APD Validation', 'srms.acquisition.forms.inwi.gen.FOAPDValidationFormService', 'GRANITE', 'model.inwi.gen.FOAPDValidation', 'ROW_NUM', 'INWI', '1', '3');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'form.inwi.gen.foapdvalidationform','EN','FO APD Validation', 'INWI');
-- FORM SICS




-- FORM FIELDS

INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_SIC, PK, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE) VALUES ('FOAPDValidationForm', 'ROW_NUM', 'field.empty', 'Y', 'N', '10', '0', '0', '0', 'N');
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'detailsDuCandidatValide', 'Détails du candidat validé', 'Détails du candidat validé', 'field.form.inwi.gen.foapdvalidationform.detailsDuCandidatValide', 'N', '0', '0', '0', '0', 'Y',null,0);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'responsableStCwName', 'Responsable ST CW  Name', 'Responsable ST CW  Name', 'field.form.inwi.gen.foapdvalidationform.responsableStCwName', 'N', '0', '0', '0', '0', 'Y',null,1);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'responsableStCwPhone', 'Responsable ST CW  Phone', 'Responsable ST CW  Phone', 'field.form.inwi.gen.foapdvalidationform.responsableStCwPhone', 'N', '0', '0', '0', '0', 'Y',null,2);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'responsableStCwEmail', 'Responsable St CW  Email', 'Responsable St CW  Email', 'field.form.inwi.gen.foapdvalidationform.responsableStCwEmail', 'N', '0', '0', '0', '0', 'Y',null,3);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'apdLink', 'APD Link', 'APD Link', 'field.form.inwi.gen.foapdvalidationform.apdLink', 'N', '0', '0', '0', '0', 'Y',null,4);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'validationDeLapdDuSite', 'Validation  de l APD du site', 'Validation  de l APD du site', 'field.form.inwi.gen.foapdvalidationform.validationDeLapdDuSite', 'Y', '0', '13', '0', '0', 'Y','XLS DEF NOT FOUND',5);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'raisonDeRejetDeLapdDuSite', 'Raison de rejet de l APD du site', 'Raison de rejet de l APD du site', 'field.form.inwi.gen.foapdvalidationform.raisonDeRejetDeLapdDuSite', 'Y', '0', '13', '0', '0', 'Y','XLS DEF NOT FOUND',6);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'validationDeLapdFo', 'Validation  de l APD FO', 'Validation  de l APD FO', 'field.form.inwi.gen.foapdvalidationform.validationDeLapdFo', 'Y', '0', '0', '0', '0', 'Y',null,7);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'commentaire', 'Commentaire', 'Commentaire', 'field.form.inwi.gen.foapdvalidationform.commentaire', 'Y', '0', '0', '0', '0', 'Y',null,8);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'validateurFoApdnotToBeDisplayedInGui', 'Validateur FO  APD (NOT TO BE DISPLAYED IN GUI)', 'Validateur FO  APD (NOT TO BE DISPLAYED IN GUI)', 'field.form.inwi.gen.foapdvalidationform.validateurFoApdnotToBeDisplayedInGui', 'Y', '0', '0', '0', '0', 'N',null,9);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'validateurFoApdPhonenotToBeDisplayedInGui', 'Validateur FO  APD Phone (NOT TO BE DISPLAYED IN GUI)', 'Validateur FO  APD Phone (NOT TO BE DISPLAYED IN GUI)', 'field.form.inwi.gen.foapdvalidationform.validateurFoApdPhonenotToBeDisplayedInGui', 'Y', '0', '0', '0', '0', 'N',null,10);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'validateurFoApdEmailnotToBeDisplayedInGui', 'Validateur FO  APD   Email (NOT TO BE DISPLAYED IN GUI)', 'Validateur FO  APD   Email (NOT TO BE DISPLAYED IN GUI)', 'field.form.inwi.gen.foapdvalidationform.validateurFoApdEmailnotToBeDisplayedInGui', 'Y', '0', '0', '0', '0', 'N',null,11);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'commentaireDesistement', 'Commentaire désistement', 'Commentaire désistement', 'field.form.inwi.gen.foapdvalidationform.commentaireDesistement', 'Y', '0', '0', '0', '0', 'Y',null,12);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'apdValide', 'APD Validé', 'APD Validé', 'field.form.inwi.gen.foapdvalidationform.apdValide', 'Y', '0', '4', '0', '0', 'Y',null,13);
INSERT INTO "EAT"."M_FORM_FIELD" (FORM_NAME, FIELD_NAME, FIELD_DESCRIPTION, FIELD_CAPTION, FIELD_SIC, MANDATORY, FIELD_TYPE, FORM_FIELD_TYPE, MIN_LENGTH, MAX_LENGTH, VISIBLE, COMBO_NAME,PRESENTATION_INDEX) VALUES ('FOAPDValidationForm', 'apdRejete', 'APD rejeté', 'APD rejeté', 'field.form.inwi.gen.foapdvalidationform.apdRejete', 'Y', '0', '4', '0', '0', 'Y',null,14);
-- FORM FIELDS SICS

INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.responsableStCwName','EN','Responsable ST CW  Name', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.responsableStCwEmail','EN','Responsable St CW  Email', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.apdValide','EN','APD Validé', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.commentaireDesistement','EN','Commentaire désistement', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.commentaire','EN','Commentaire', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.detailsDuCandidatValide','EN','Détails du candidat validé', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.validateurFoApdPhonenotToBeDisplayedInGui','EN','Validateur FO  APD Phone (NOT TO BE DISPLAYED IN GUI)', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.validateurFoApdnotToBeDisplayedInGui','EN','Validateur FO  APD (NOT TO BE DISPLAYED IN GUI)', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.raisonDeRejetDeLapdDuSite','EN','Raison de rejet de l APD du site', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.responsableStCwPhone','EN','Responsable ST CW  Phone', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.validateurFoApdEmailnotToBeDisplayedInGui','EN','Validateur FO  APD   Email (NOT TO BE DISPLAYED IN GUI)', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.validationDeLapdFo','EN','Validation  de l APD FO', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.apdRejete','EN','APD rejeté', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.validationDeLapdDuSite','EN','Validation  de l APD du site', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'field.form.inwi.gen.foapdvalidationform.apdLink','EN','APD Link', 'INWI');




-- FORM BUTTONS

INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'btn.inwi.sic.FOAPDValidationForm.dowloadApd','EN','Dowload APD', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'btn.inwi.sic.FOAPDValidationForm.validerApd','EN','Valider APD', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'btn.inwi.sic.FOAPDValidationForm.ok','EN','OK', 'INWI');
INSERT INTO EAT.I_DICTIONARY (ID, SIC, LANG_ID, MESSAGE, DOMAIN) VALUES (I_DICTIONARY_ID.NEXTVAL,'btn.inwi.sic.FOAPDValidationForm.probleme','EN','Problème', 'INWI');
INSERT INTO EAT.M_FORM_BUTTON (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('dowloadApd', 'FOAPDValidationForm', 'Dowload APD', 'javascript:FOAPDValidationForm_dowloadApd()', 'N', '1', 'btn.inwi.sic.dowloadApd');
INSERT INTO EAT.M_FORM_BUTTON (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('validerApd', 'FOAPDValidationForm', 'Valider APD', 'javascript:FOAPDValidationForm_validerApd()', 'N', '1', 'btn.inwi.sic.validerApd');
INSERT INTO EAT.M_FORM_BUTTON (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('ok', 'FOAPDValidationForm', 'OK', 'javascript:FOAPDValidationForm_ok()', 'N', '1', 'btn.inwi.sic.ok');
INSERT INTO EAT.M_FORM_BUTTON (BUTTON_NAME, FORM_NAME, BUTTON_LABEL, ACTION, FORM_BIND, PRESENTATION_INDEX, BUTTON_SIC) VALUES ('probleme', 'FOAPDValidationForm', 'Problème', 'javascript:FOAPDValidationForm_probleme()', 'N', '1', 'btn.inwi.sic.probleme');




COMMIT;
quit;
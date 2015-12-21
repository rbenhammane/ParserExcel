DELETE FROM "EAT"."M_FORM" WHERE FORM_NAME = 'FOAPDValidationForm';

-- DELETE FORM SICS
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'form.inwi.gen.foapdvalidationform' and DOMAIN = 'INWI';
-- Delete Form FIELD
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'ROW_NUM' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_BUTTON" WHERE FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'detailsDuCandidatValide' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'responsableStCwName' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'responsableStCwPhone' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'responsableStCwEmail' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'apdLink' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'validationDeLapdDuSite' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'raisonDeRejetDeLapdDuSite' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'validationDeLapdFo' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'commentaire' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'validateurFoApdnotToBeDisplayedInGui' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'validateurFoApdPhonenotToBeDisplayedInGui' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'validateurFoApdEmailnotToBeDisplayedInGui' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'commentaireDesistement' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'apdValide' AND FORM_NAME = 'FOAPDValidationForm';
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FIELD_NAME = 'apdRejete' AND FORM_NAME = 'FOAPDValidationForm';
-- Delete FORM
DELETE FROM "EAT"."M_FORM_FIELD" WHERE FORM_NAME = 'FOAPDValidationForm';

-- DELETE FORM FIELD SICS
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.responsableStCwName' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.responsableStCwEmail' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.apdValide' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.commentaireDesistement' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.commentaire' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.detailsDuCandidatValide' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.validateurFoApdPhonenotToBeDisplayedInGui' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.validateurFoApdnotToBeDisplayedInGui' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.raisonDeRejetDeLapdDuSite' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.responsableStCwPhone' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.validateurFoApdEmailnotToBeDisplayedInGui' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.validationDeLapdFo' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.apdRejete' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.validationDeLapdDuSite' and DOMAIN = 'INWI';
DELETE FROM "EAT"."I_DICTIONARY" WHERE SIC = 'field.form.inwi.gen.foapdvalidationform.apdLink' and DOMAIN = 'INWI';
DELETE FROM EAT.I_DICTIONARY WHERE SIC like '%.foapdvalidationform%' and DOMAIN = 'INWI';
COMMIT;
quit;
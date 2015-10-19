<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="it.pride.eat.authentication.AuthenticatedUser"%>
<%@ taglib uri="/WEB-INF/tld/exttaglib.tld" prefix="exttaglib"%>
<script type="text/javascript" src="/SRMS/js-libs/inwi/common.js"></script>
<script type="text/javascript" src="/SRMS/js-libs/inwi/{**FORM-NAME**}.js"></script>

<%@ page import="srms.util.SRMSConstants"%>
<html>
<exttaglib:head>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/js-libs/extjs3.4/shared/extensions.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/js-libs/extjs3.4/ux/fileuploadfield/css/fileuploadfield.css" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js-libs/extjs3.4/ux/fileuploadfield/FileUploadField.js"></script>

	<exttaglib:formExt />
	<exttaglib:datagridExt />

	<script>  
	
	var contextPath = '${pageContext.request.contextPath}';
	var BEAN_SESSION_DOC_DOWNLOAD = '<%=SRMSConstants.BEAN_SESSION_DOC_DOWNLOAD%>';
	// submitEATForm('{**FORM-NAME**}','srms.acquisition.forms.inwi.gen.{**FORM-NAME**}','ok');
    {**OPERATIONS**}
    {**GETTERS**}
    {**SETTERS**}

    
    <exttaglib:datagrid gridName="CandidateListAssociated" jsVarGridName='_dataGrid' xtype="datagrid_xtype"  filter="false"  forceFit="false"  mandatoryFilters="true" rowLimit="false" where="  SN.SITE_INST_ID=${param.siteInstId} AND SC.STATUS='SELECTED' "/>
    <!-- exttaglib:filter formName="APDValidationCW" xtype="filter_xtype" gridId="APDValidationCW" -->
    <!-- start inwi -->
	<exttaglib:form formName="{**FORM-NAME**}" xtype="form_det_a_xtype" action="" pk="0" noButtons="true"  />
	 <!-- end Inwi -->
	
    // var _filter = {region:'north', id:'filterId',xtype:'filter_xtype', height:300, collapsible:true, title:'APD Validation CW'};
// 	var _dataGrid = {region:'center', id:'APDValidationCW', xtype:'datagrid_xtype', height : '300'};
	var _form = {region:'north', id:'{**FORM-NAME**}', xtype:'form_det_a_xtype', height : '200'};
    //type:'vbox',align:'stretch',pack:'start'
    _dataGrid.id = 'datagridId';
     _dataGrid.buttons =  [
             { text: '<exttaglib:label sic="btn.srms.view" />',id:'btn_detail_site',handler:loadGuiDetails,disabled:false}
        ];
    Ext.onReady(function(){
        
        new Ext.Viewport({
        	layout: 'border',
			plain:true,
            items : [_form,_dataGrid],                   
            renderTo : Ext.getBody()
        });   

		// checkRejectReasonRadio();
	});
        
     function onChangeRadioVal(){
            checkRejectReasonRadio();
        }
    
    function DownloadAPD() {
        // var ngo_id = ${param.ngo_id};
        var action = 'downloadApd';
        var url = '${pageContext.request.contextPath}/jsonservice?javaclass=srms.acquisition.forms.inwi.{**FORM-NAME**}Service&cmd='+action+'&formid={**FORM-NAME**}&ngo_id='+ngo_id;
        sendRequestApd_Download(url);
        window.open( '<%=request.getContextPath()%>/updownfile?cmd=download&beanname=<%=SRMSConstants.BEAN_SESSION_DOC_DOWNLOAD%>');
    }
    function Ok() {
         if(checkFields()==false) {
         return;
         }
         else {
         	 Ext.Msg.show({
                        title:'Success '
                        ,msg:'APD Confirmation is OK'
                        ,modal:true
                        ,icon:Ext.Msg.INFO
                        ,buttons:Ext.Msg.OK
                        ,fn: function(){
                            
                        }
                    });
         }

                   
           
           
    }
    
    function checkRejectReasonRadio(){
             var cmbYesNo=Ext.getCmp("cmbYesNo").getValue();
             if(cmbYesNo=='<%=SRMSConstants.CMB_VAL_NO%>'){
                 Ext.getCmp("sarfSiteTypeList").setDisabled(false);
             }else{
                 Ext.getCmp("sarfSiteTypeList").setValue('');
                 Ext.getCmp("sarfSiteTypeList").setDisabled(true);
             }


         }
         
    function checkFields(){
            //Check APD
            if (valider_clicked == false) {
            	alert("Please validate or reject APD");
                    return false;
            }
            if(  Ext.getCmp("cmbYesNo")!=undefined) {
                if (Ext.getCmp("cmbYesNo").getValue() == '') {
                    alert("Please validate or reject APD")
                    return false;
                }
            }
            return true;
        }
        
    function Probleme() {
        alert('I am Prob');
        var action = 'probleme';
        var url = '${pageContext.request.contextPath}/jsonservice?javaclass=srms.acquisition.forms.inwi.{**FORM-NAME**}Service&cmd='+action;
        sendRequestApd(url);
    }
    var valider_clicked = false;
    function ValiderAPD() {
    	valider_clicked = true;
        var action = 'validerApd';
        var apdValider = Ext.getCmp('cmbYesNo').getRawValue();
        var apdRaisonRe = Ext.getCmp('sarfSiteTypeList').getRawValue();
        // var site_inst_id = ${param.siteInstId};
        // var site_inst_id_nom = ${param.siteInstIdNom};
        // var ngo_id = ${param.ngo_id};
        var url = '${pageContext.request.contextPath}/jsonservice?javaclass=srms.acquisition.forms.inwi.{**FORM-NAME**}Service&cmd='+action+'&cmbYesNo='+apdValider+'&apdRaisonRe='+apdRaisonRe+'&site_inst_id='+site_inst_id+'&formid={**FORM-NAME**}&site_inst_id_nom='+site_inst_id_nom+'&ngo_id='+ngo_id;
        sendRequestApd(url);
    }
    
    // to check
    
        function getIdSelectedCandidate(field){
        	var val = Ext.getCmp('datagridId').getSelectionModel().getSelections()[0].get('site_inst_id')
            return val;

        }

    
    	function loadGuiDetails () {
    		 var idKeyNom = getIdSelectedCandidate('site_inst_id');
    		 var action = 'loadFormApd';
    	     var url = '${pageContext.request.contextPath}/jsonservice?javaclass=srms.acquisition.forms.inwi.{**FORM-NAME**}Service&cmd='+action+'&site_inst_id='+idKeyNom;
    	     sendRequestHandler(url);
    	}


               
    </script>
</exttaglib:head>
<body>

</body>
</html>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="it.pride.eat.authentication.AuthenticatedUser" %>
<%@ taglib uri="/WEB-INF/tld/exttaglib.tld" prefix="exttaglib" %>

<html>
<exttaglib:head>   

    <exttaglib:formExt/> 
    <exttaglib:datagridExt/>
    
    <script>  

    <exttaglib:datagrid gridName="{**DATAGRID-NAME**}" xtype="datagrid_xtype"  filter="true" filterForm="{**FILTERFORM-NAME**}" forceFit="true"  mandatoryFilters="false" rowLimit="false"/>
    <!-- exttaglib:filter formName="{**FILTERFORM-NAME**}" xtype="filter_xtype" gridId="datagridId"/ -->
	<exttaglib:form formName="formId" xtype="form_det_a_xtype" action="view" pk="0" />
	
    // var _filter = {region:'north', id:'filterId',xtype:'filter_xtype', height:{**FILTER_HEIGHT**}, collapsible:true, title:'{**TITLE**}'};
	var _dataGrid = {region:'center', id:'datagridId', xtype:'datagrid_xtype'};
	var _form = {region:'north', id:'formId', height:300, xtype:'form_det_a_xtype'};
    
    Ext.onReady(function(){
        
        new Ext.Viewport({
			layout: 'border',
			plain:true,
            items : [_form,_dataGrid],                   
            renderTo : Ext.getBody()
        });   

	});
                   
    </script>              
</exttaglib:head>
<body>

</body>
</html>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<script language="javascript"> <!--
var color1 = "#eeeeee"; 
var color2 = "#ffffff";
var rownumber = 1;
var foldervalue;
var sFolder;
var sNomenclature;
var sUnitWeight;
var sRevision;
var sVersion;
var sModelerName;
var sCriteria;
var sOrCriteria;
var sName;
var sRelease;
var sExtension;
var sLatestObj;
var sServer;

function tablerow() { 
 var currentcolour; 
 if (rownumber % 2 == 0) { 
   currentcolor = color1; 
 } else { 
   currentcolor = color2; 
 } 
 rownumber = rownumber + 1; 
 return currentcolor;
}

function submitIt(){

	if (document.forms[0].ckLatest.checked){
		document.forms[0].selectedReportName.value=document.forms[0].serverInput.value + '-latest';
	} else {
		document.forms[0].selectedReportName.value=document.forms[0].serverInput.value;
	}

	setupCriteria();
	setFormCriteria();
	submitTheForm();
}


function setUser (inputVal){
	sNomenclature=inputVal;
}

function setfolder(foldervalue){
	sFolder=foldervalue;
	}

function setupCriteria(){
	sUnitWeight=document.forms[0].unitWeightInput.value;
	sNomenclature=document.forms[0].nomenclatureInput.value;
	sModelerName=document.forms[0].modelerNameInput.value;
	sRelease=document.forms[0].releaseInput.value;
	sVersion=document.forms[0].versionInput.value;
	sRevision=document.forms[0].revisionInput.value;
	sName=document.forms[0].nameInput.value;
	sExtension=document.forms[0].extensionInput.value;
	sLatestObj=document.forms[0].ckLatest.value;
	sServer=document.forms[0].serverInput.value;
	
	// if (document.forms[0].ckLatest.checked){
		// document.forms[0].selectedReportName.value=document.forms[0].serverInput.value + '-latest';
	// } else {
		// document.forms[0].selectedReportName.value=document.forms[0].serverInput.value;
	// }

	if (sFolder){
	sCriteria = 'Folder=' + sFolder;
	}

	if (document.forms[0].nameInput.value){
		if (!sCriteria){
				sCriteria= 'Name=*' + document.forms[0].nameInput.value + '*' + document.forms[0].extensionInput.value + '*';
		} else {
	  			sCriteria= sCriteria + ' & Name=*' + document.forms[0].nameInput.value + '*' + document.forms[0].extensionInput.value + '*';
		}
	}


	if (document.forms[0].unitWeightInput.value){
		if (!sCriteria){
			sCriteria = 'Unit_Weight=*' + document.forms[0].unitWeightInput.value + '*';
			// alert (sCriteria);
		} else {
			sCriteria = sCriteria + ' & Unit_Weight=*' + document.forms[0].unitWeightInput.value + '*';
			// alert (sCriteria);
		}
	}

	if (document.forms[0].nomenclatureInput.value){
		if (!sCriteria){
			sCriteria= 'Nomenclature=' + document.forms[0].nomenclatureInput.value;
		} else {
			sCriteria= sCriteria + ' & Nomenclature=' + document.forms[0].nomenclatureInput.value;
		}
	}

	if (document.forms[0].revisionInput.value){
		if (!sCriteria){
			sCriteria= 'Revision=' + document.forms[0].revisionInput.value;
		} else {
			sCriteria= sCriteria + ' & Revision=' + document.forms[0].revisionInput.value;
		}
	}

	if (document.forms[0].versionInput.value){
		if (!sCriteria){
			sCriteria= 'Version=' + document.forms[0].versionInput.value;
		} else {
			sCriteria= sCriteria + ' & Version=' + document.forms[0].versionInput.value;
		}
	}

	if (document.forms[0].releaseInput.value){
		if (!sCriteria){
			sCriteria= 'Release-Level=' + document.forms[0].releaseInput.value;
		} else {
			sCriteria= sCriteria + ' & Release-Level=' + document.forms[0].releaseInput.value;
		}
	}

	if (document.forms[0].modelerNameInput.value){

		if (!sCriteria){
			sCriteria='Modeler_Name_1=*' + document.forms[0].modelerNameInput.value + '*';
		} else {
			sCriteria= sCriteria + ' & Modeler_Name_1=*' + document.forms[0].modelerNameInput.value + '*';
		}
	}

}

function setFormCriteria(){
	if (!sOrCriteria){
		document.forms[0].criteria.value=sCriteria;
		// alert (document.forms[0].criteria.value);
	} else {
		document.forms[0].criteria.value=sOrCriteria;
		// alert (document.forms[0].criteria.value);
	}
	// document.forms[0].criteria.value=sCriteria;
}

function submitTheForm(){
	document.forms[0].submit();
}

function submitOr(){
	setupCriteria();
	if (!sOrCriteria){
		// alert ('first time');
		sOrCriteria='[ ' + sCriteria + ' ]';
	} else {
		// alert ('second time');
		sOrCriteria=sOrCriteria + ' | [ ' + sCriteria + ' ]';
	}
	// alert (sOrCriteria);
	sCriteria='';
}

function clrQuery(){
	alert('Clearing Query Criteria');
	sOrCriteria='';
	sCriteria='';
}

function showQuery(){
	if (!sOrCriteria){
		alert (sCriteria);
	} else {
		alert (sOrCriteria);
	}
}

function resetAll(){
	document.forms[0].nomenclatureInput.value='';
	document.forms[0].modelerNameInput.value='';
	document.forms[0].unitWeightInput.value='';
	document.forms[0].nameInput.value='*';
	document.forms[0].extensionInput.value='';
	document.forms[0].revisionInput.value='';
	document.forms[0].versionInput.value='';
	document.forms[0].releaseInput.value='';
	sFolder='';
}

//-->
</script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<link rel="stylesheet" href="../css/style.css">
<html:form action="/browse/index.do" focus="nameInput"> <html:hidden property="event" value="search"/> 
<script language="javascript"> <!--

function setUp(setFolderName){
	document.write('<INPUT TYPE="hidden" NAME="criteria" VALUE="' + setFolderName '">')
	}
//-->
</script>
 <html:hidden property="interactor" value="report"/> <%@ include file="/web/include/header.jsp"%> 
<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr align="left">
    <td class="pageTitle" align="left">Intralink Advanced Search </td>
  </tr>
</table>
<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr> 
    <td class="formTitle" align="left"> 
      <div align="left"><html:link href="javascript:send()" styleClass="formTitle"></html:link>
        <%-- <html:text property="criteria" size="48" onkeypress="return autoSubmit(this, event)"/> --%> 
        <html:hidden property="criteria"/><html:hidden property="selectedReportName" value="intralink-base"/> 
        <%-- <html:select name="hiReport" property="selectedReportName" styleClass="input" onchange="send()" onkeypress="autoSubmit(this,event)"> 
        	<html:options name="hiReport" property="reportNames"/> 
		</html:select> --%> &nbsp;&nbsp;&nbsp; </div>
    </td>
	<tr>
	<td> 
      <table width="100%" border="0" bgcolor="#FFFFFF">
        <tr> 
          <td rowspan="2"><applet code=com.carpathia.atc3d.AnimatedTreeControl.class archive=atc34d.jar width=350 height=250 MAYSCRIPT>
              <param name=bkgColorB value=255>
              <param name=bkgColorG value=255>
              <param name=bkgColorR value=255>
              <param name=treeStructureFile value="demo.zip">
              <param name=documentWindowName value=MainPage>
              <param name=openFolderIconFile value="/web/image/OPEN.gif">
              <param name=closeFolderIconFile value="/web/image/CLOSED.gif">
              <param name=leafIconFile value="/web/image/page.gif">
              <param name=useAnimation value=true>
              <param name=scrollBarUnitIncrement value=10>
              <param name=useCompressedInput value=true>
            </applet></td>
          <td rowspan="2"> 
            <table width="100%" border="0">
              <tr> 
                <td class="menuItem" colspan="2">
                  <input type="button" name="Clear Query" value="Clear Query" onClick="clrQuery()" class="button3" alt="Or Query Builder">
                  <input type="button" name="Show Query" value="Show Query" onClick="showQuery()" class="button3">
                  <input type="button" name="Reset Values" value="Reset Values" onClick="resetAll()" class="button3">
                </td>
              </tr>
              <tr> 
                <td class="menuItem">Select Server</td>
                <td class="menuItem"> 
                  <select name="serverInput">
                    <option value="TVS" selected>TVS-Enforcer</option>
                  </select>
                  <input type="checkbox" name="ckLatest" value="checkbox">
                  Latest Only </td>
              </tr>
              <tr> 
                <td class="menuItem">Enter Obj Name</td>
                <td class="menuItem"> 
                  <input type="text" name="nameInput" size="48" value="*">
                </td>
              </tr>
              <tr> 
                <td class="menuItem">Select Type</td>
                <td> 
                  <select name="extensionInput">
                    <option value="" selected>NONE</option>
                    <option value=".prt">ProE Part</option>
                    <option value=".asm">Proe Asm</option>
                    <option value=".drw">Proe Drw</option>
                    <option value=".pdf">Acrobat PDF</option>
                    <option value=".dwg">AutoCAD</option>
                    <option value=".doc">MS Word</option>
                    <option value=".xls">MS Excel</option>
                    <option value=".jpg">JPEG</option>
                    <option value=".gif">GIF</option>
                  </select>
                </td>
              </tr>
              <tr> 
                <td class="menuItem">Select Revision</td>
                <td> 
<select name="revisionInput">
<option value="" selected>NONE</option>
<option value="TVS">TVS</option>
<option value="X0">X0</option>
<option value="XA">XA</option>
<option value="XB">XB</option>
<option value="XC">XC</option>
<option value="XD">XD</option>
<option value="-">-</option>
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
<option value="E">E</option>
<option value="F">F</option>
<option value="G">G</option>
<option value="H">H</option>
<option value="J">J</option>
<option value="K">K</option>
<option value="L">L</option>
<option value="M">M</option>
<option value="N">N</option>
<option value="P">P</option>
<option value="R">R</option>
<option value="T">T</option>
<option value="U">U</option>
<option value="V">V</option>
<option value="W">W</option>
<option value="X">X</option>
<option value="Y">Y</option>
<option value="AB">AB</option>
<option value="AC">AC</option>
<option value="AD">AD</option>
<option value="AE">AE</option>
<option value="AF">AF</option>
<option value="AG">AG</option>
<option value="AH">AH</option>
<option value="AJ">AJ</option>
<option value="AK">AK</option>
<option value="AL">AL</option>
<option value="AM">AM</option>
<option value="AN">AN</option>
<option value="AP">AP</option>
<option value="AR">AR</option>
<option value="AT">AT</option>
<option value="AU">AU</option>
<option value="AV">AV</option>
<option value="AW">AW</option>
<option value="AX">AX</option>
<option value="AY">AY</option>
<option value="BA">BA</option>
<option value="BC">BC</option>
<option value="BD">BD</option>
<option value="BE">BE</option>
<option value="BF">BF</option>
<option value="BG">BG</option>
<option value="BH">BH</option>
<option value="BJ">BJ</option>
<option value="BK">BK</option>
<option value="BL">BL</option>
<option value="BM">BM</option>
<option value="BN">BN</option>
<option value="BP">BP</option>
<option value="BR">BR</option>
<option value="BT">BT</option>
<option value="BU">BU</option>
<option value="BV">BV</option>
<option value="BW">BW</option>
<option value="BX">BX</option>
<option value="BY">BY</option>
<option value="CA">CA</option>
<option value="CB">CB</option>
<option value="CD">CD</option>
<option value="CE">CE</option>
<option value="CF">CF</option>
<option value="CG">CG</option>
<option value="CH">CH</option>
<option value="CJ">CJ</option>
<option value="CK">CK</option>
<option value="CL">CL</option>
<option value="CM">CM</option>
<option value="CN">CN</option>
<option value="CP">CP</option>
<option value="CR">CR</option>
<option value="CT">CT</option>
<option value="CU">CU</option>
<option value="CV">CV</option>
<option value="CW">CW</option>
<option value="CX">CX</option>
<option value="CY">CY</option>
<option value="DA">DA</option>
<option value="DB">DB</option>
<option value="DC">DC</option>
<option value="DE">DE</option>
<option value="DF">DF</option>
<option value="DG">DG</option>
<option value="DH">DH</option>
<option value="DJ">DJ</option>
<option value="DK">DK</option>
<option value="DL">DL</option>
<option value="DM">DM</option>
<option value="DN">DN</option>
<option value="DP">DP</option>
<option value="DR">DR</option>
<option value="DT">DT</option>
<option value="DU">DU</option>
<option value="DV">DV</option>
<option value="DW">DW</option>
<option value="DX">DX</option>
<option value="DY">DY</option>
<option value="EA">EA</option>
<option value="EB">EB</option>
<option value="EC">EC</option>
<option value="ED">ED</option>
<option value="EF">EF</option>
<option value="EG">EG</option>
<option value="EH">EH</option>
<option value="EJ">EJ</option>
<option value="EK">EK</option>
<option value="EL">EL</option>
<option value="EM">EM</option>
<option value="EN">EN</option>
<option value="EP">EP</option>
<option value="ER">ER</option>
<option value="ET">ET</option>
<option value="EU">EU</option>
<option value="EV">EV</option>
<option value="EW">EW</option>
<option value="EX">EX</option>
<option value="EY">EY</option>
<option value="FA">FA</option>
<option value="FB">FB</option>
<option value="FC">FC</option>
<option value="FD">FD</option>
<option value="FE">FE</option>
<option value="FG">FG</option>
<option value="FH">FH</option>
<option value="FJ">FJ</option>
<option value="FK">FK</option>
<option value="FL">FL</option>
<option value="FM">FM</option>
<option value="FN">FN</option>
<option value="FP">FP</option>
<option value="FR">FR</option>
<option value="FT">FT</option>
<option value="FU">FU</option>
<option value="FV">FV</option>
<option value="FW">FW</option>
<option value="FX">FX</option>
<option value="FY">FY</option>
</select>

                </td>
              </tr>
              <tr> 
                <td class="menuItem">Enter Version</td>
                <td> 
<select name="versionInput">
<option value="" selected>NONE</option>
<option value="0">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
<option value="32">32</option>
<option value="33">33</option>
<option value="34">34</option>
<option value="35">35</option>
<option value="36">36</option>
<option value="37">37</option>
<option value="38">38</option>
<option value="39">39</option>
<option value="40">40</option>
<option value="41">41</option>
<option value="42">42</option>
<option value="43">43</option>
<option value="44">44</option>
<option value="45">45</option>
<option value="46">46</option>
<option value="47">47</option>
<option value="48">48</option>
<option value="49">49</option>
<option value="50">50</option>
<option value="51">51</option>
<option value="52">52</option>
<option value="53">53</option>
<option value="54">54</option>
<option value="55">55</option>
<option value="56">56</option>
<option value="57">57</option>
<option value="58">58</option>
<option value="59">59</option>
<option value="60">60</option>
<option value="61">61</option>
<option value="62">62</option>
<option value="63">63</option>
<option value="64">64</option>
<option value="65">65</option>
<option value="66">66</option>
<option value="67">67</option>
<option value="68">68</option>
<option value="69">69</option>
<option value="70">70</option>
<option value="71">71</option>
<option value="72">72</option>
<option value="73">73</option>
<option value="74">74</option>
<option value="75">75</option>
<option value="76">76</option>
<option value="77">77</option>
<option value="78">78</option>
<option value="79">79</option>
<option value="80">80</option>
<option value="81">81</option>
<option value="82">82</option>
<option value="83">83</option>
<option value="84">84</option>
<option value="85">85</option>
<option value="86">86</option>
<option value="87">87</option>
<option value="88">88</option>
<option value="89">89</option>
<option value="90">90</option>
<option value="91">91</option>
<option value="92">92</option>
<option value="93">93</option>
<option value="94">94</option>
<option value="95">95</option>
<option value="96">96</option>
<option value="97">97</option>
<option value="98">98</option>
<option value="99">99</option>
</select>

                </td>
              </tr>
              <tr> 
                <td class="menuItem">Select Release Level</td>
                <td> 
                  <select name="releaseInput">
                    <option value="" selected>NONE</option>
                    <option value="*SCRATCH*">SCRATCH</option>
                    <option value="*CONCEPT*">CONCEPT</option>
                    <option value="*WIP*">WIP</option>
                    <option value="*PENDING*">PENDING</option>
                    <option value="*RELEASED*">RELEASED</option>
                    <option value="*PRODUCTION*">PRODUCTION</option>
                    <option value="*ARCHIVE*">ARCHIVE</option>
                  </select>
                </td>
              </tr>
              <tr class="menuItem"> 
                <td>Enter Unit Weight</td>
                <td> 
                  <input type="text" name="unitWeightInput" value="" size="48">
                </td>
              </tr>
              <tr> 
                <td class="menuItem">Enter Modeler Name</td>
                <td> 
                  <input type="text" name="modelerNameInput" value="" size="48">
                </td>
              </tr>
              <tr> 
                <td class="menuItem">Enter Nomenclature</td>
                <td> 
                  <input type="text" name="nomenclatureInput" value="" size="48" >
                </td>
              </tr>
              <tr> 
                <td> 
                  <input type="button" name="Submit Search" value="Submit Search" onClick="submitIt()" class="button3">
                </td>
                <td> 
                  <input type="button" name="Or Builder" value="Add to Criteria" onClick="submitOr()" class="button3">
                </td>
              </tr>
            </table>
            
          </td>
        </tr>
        <tr> </tr>
      </table>
    </td>
	</tr>
  </tr>
  <tr> 
    <td> 
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr> 
          <th class="listHeader" align="center">Save</th>
          <logic:iterate id="fieldName" name="hiReport" property="metadataFields" type="java.lang.String"> 
          <th class="listHeader" align="left" nowrap><bean:write name="fieldName"/></th>
          </logic:iterate> </tr>
        <logic:iterate id="metadata" name="hiReport" property="items" type="zws.hi.report.MetadataAdapter"> 
        <script language="javascript"> <!--
 if (rownumber % 2 == 0) { 
   currentcolor = 0; 
 } else { 
   currentcolor = 1; 
 } 
 rownumber = rownumber + 1; 
 document.write ("<tr class='item"+currentcolor+"'>");
//-->
</script>


<script language="javascript"> <!--
 document.write ("<td align='center' class='item"+currentcolor+"'>");
//-->
</script><nobr> <html:link href="/browse/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="origin" styleClass="button"><img border="0" src="/web/image/download.jpg" width="18" height="16" alt="Save Local Copy of File"/></html:link>&nbsp; 
            <!-- <html:link href="/report/index.do?event=info" paramId="ID" paramName="metadata" paramProperty="origin" styleClass="button"><img border="0" src="/web/image/info.jpg" width="18" height="17"/></html:link> --> 
            </nobr> </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
<script language="javascript"> <!--
 document.write ("<td align='left' nowrap class='item"+currentcolor+"'>");
//-->
</script><bean:write name="field"/></td>
          </logic:iterate> </tr>
        </logic:iterate> 
      </table>
    </td>
  </tr>
</table>

</html:form> 
</body>
</html:html>

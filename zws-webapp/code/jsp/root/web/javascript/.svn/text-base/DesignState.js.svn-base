function goto(url) { window.location=url; }
function send() { document.forms[0].submit(); }
function sendEvent(ev) { document.forms[0].event.value=ev;send(); }
function sendEventX(ev,url){
  document.forms[0].event.value=ev;
 //name='event='+encodeURI(ev);
 //url= url+'?'+name+'='+ev;
 retrieveURL(url,document.forms[0].name);
}
function sendEventKey(ev,key) {
  document.forms[0].event.value=ev;
  document.forms[0].eventKey.value=key;
  send();
}
function sendEventID(ev,ID) {
  document.forms[0].event.value=ev;
  document.forms[0].ID.value=ID;
  send();
}

function sendEventIDKey(ev,ID, key) {
  document.forms[0].event.value=ev;
  document.forms[0].ID.value=ID;
  document.forms[0].eventKey.value=key;
  send();
}

function autoSubmit(formElement) {
  var keycode=getKeycode();
  if (keycode != 13) return true;
  send();
  return false;
}

function autoSubmitEvent(formElement, ev) {
  var keycode=getKeycode();
  if (keycode != 13) return true;
  sendEvent(ev);
  return false;
}

function add() { document.forms[0].event.value="add";send(); }
function edit() { document.forms[0].event.value="edit";send(); }
function next() { document.forms[0].event.value="next";send(); }
function editChoice(choice) {
  document.forms[0].choice.value=choice;
  document.forms[0].event.value="edit";
  send();
}
function choose() { //chooses all
  document.forms[0].event.value="choose";
  send();
}
function choose(id) {
  document.forms[0].ID.value=id;
  document.forms[0].event.value="choose";
  send();
}
function chooseFrom(listName, id) {
  document.forms[0].ID.value=id;
  document.forms[0].event.value="choose"+listName;
  send();
}
function pick(id) {
  document.forms[0].ID.value=id;
  document.forms[0].event.value="pick";
  send();
}
function unpick(id) {
  document.forms[0].ID.value=id;
  document.forms[0].event.value="unpick";
  send();
}

function sendForm(name) { document.forms[name].submit(); }
function sendFormEvent(name, ev) { document.forms[name].event.value=ev;sendForm(name); }
function autoSubmit(formElement,e){
  var keycode=getKeycode();
  if (keycode != 13) return true;
  formElement.form.submit();
  return false;
}

//set focus to next form field on enter
function tabOver(formElement,e){
  var idx;
  var keycode=getKeycode();
  if (keycode != 13) return true;
  elements = formElement.form.elements;
  idx = findElementIndex(formElement, elements);
  while(idx<elements.size && "hidden".equalsIgnoreCase(elements[++idx].type));
  if (idx<elements.length) elements[idx+1].focus();
}

function getKeycode(ev) {
  var keycode=-1;
  if (window.event) keycode = window.event.keyCode;
  else if (e) keycode = e.which;
  return keycode;
}

function findElementIndex(formElement, elements) {
  var i;
  for (i=0; i < elements.length; i++) if (formElement==elements[i]) return i;
}

function setFocus(formName, elementName){
  documents.forms[formName].elements[elementName].focus=true;
}

//Browser Detection
isN6 = document.getElementById;
isIE = document.all;
isN4 = document.layers;

//Screen Resolution
var canvasWidth, canvasHeight;
if (isIE){
  canvasWidth = document.body.clientWidth;
  canvasHeight = document.body.clientHeight;
}else if (isN4 || isN6){
  canvasWidth = innerWidth;
  canvasHeight = innerHeight;
}

//Array Creation
function materializeArray(length) {
 this.length = length;
 return this;
}

//String Functions
function trim(s) {
  while (s.substring(0,1) == ' ') s = s.substring(1,s.length);
  while (s.substring(s.length-1,s.length) == ' ')s = s.substring(0,s.length-1);
  return s;
}

function trimSingleQuotes(s) {
  val = trim(s)
  if (val.substring(0,1) == "'") val = val.substring(1,val.length);
  if (val.substring(val.length-1,val.length) == "'") val = val.substring(0,val.length-1);
  return val;
}


//Windowing
var popupWindows = new Array(50);
function launchWindow(href, key, width, height) {
  w = popupWindows[key];
  //if (w!=false) w.close();
  params = "status=0,toolbar=0,menubar=0,directories=0,resizable=1,scrollbars=1,height="+height+",width="+width;
  w = window.open(href, key, params);
  w.focus();
  popupWindows[key]=w;
  return false;
}

//scripts for showing ToolTip added by rahul
function showtip(current,e,text){
	if (document.all||document.getElementById){
	thetitle=text.split('<br>')
	if (thetitle.length>1){
	thetitles=''
	for (i=0;i<thetitle.length;i++)
	thetitles+=thetitle[i]
	current.title=thetitles
	}
	else
	current.title=text
	}

	else if (document.layers){
	document.tooltip.document.write('<layer bgColor="white" style="border:1px solid black;font-size:12px;">'+text+'</layer>')
	document.tooltip.document.close()
	document.tooltip.left=e.pageX+5
	document.tooltip.top=e.pageY+5
	document.tooltip.visibility="show"
	}
}

function hidetip(){
	if (document.layers)
	document.tooltip.visibility="hidden"
}
function showDiv(id,name, text){
		document.getElementById('notes').value = id;
		if(text != '') document.getElementById('notes').innerHTML="<b>" +name + "</b><br>" +text;
		alert("send");
		send();
}
function showNotes(index, type){
	for (i=0;i<document.forms[0].elements.length;i++) {
		var inps = document.forms[0].elements[i];
		if (index == inps.name) {
			document.getElementById('notes').innerHTML="<b>" +type + "</b><br>"+inps.value;
			// copy to clipboard
			copytoClipBoard('notes');
		}
	}
}

function clearForm(){
	for (i=0;i<document.forms[0].elements.length;i++) {
		var inps = document.forms[0].elements[i];
		inps.value ="";
	}
}


function clearField(field){
		document.getElementById(field).value ="";
}

function copytoClipBoard(field) { 
			// copy to clipboard
			if( window.clipboardData && window.clipboardData.setData ) {
				window.clipboardData.clearData();
				window.clipboardData.setData('Text',document.getElementById(field).value);
				//alert(document.getElementById(field).value + ' copied to clipboard');
			} else {
				// You have to sign the code to enable this or allow the action in about:config by changing
				//user_pref("signed.applets.codebase_principal_support", true);
				var clip = Components.classes["@mozilla.org/widget/clipboard;[[[[1]]]]"].createInstance(Components.interfaces.nsIClipboard);
				if (!clip) return;
				// create a transferable
				var trans = Components.classes["@mozilla.org/widget/transferable;[[[[1]]]]"].createInstance(Components.interfaces.nsITransferable);
				if (!trans) return;
				// specify the data we wish to handle. Plaintext in this case.
				trans.addDataFlavor('text/unicode');
				// To get the data from the transferable we need two new objects
				var str = new Object();
				var len = new Object();
				var str = Components.classes["@mozilla.org/supports-string;[[[[1]]]]"].createInstance(Components.interfaces.nsISupportsString);
				var copytext=meintext;
				str.data=inps.value;
				trans.setTransferData("text/unicode",str,copytext.length*[[[[2]]]]);
			}
} 

function hideDiv(){
		//document.getElementById('notes').innerHTML="";
}

function TrimString(sInString) {
  sInString = sInString.replace( /^\s+/g, "" );// strip leading
  return sInString.replace( /\s+$/g, "" );// strip trailing
}

function selectedMenuItem(item)
{
 var grpId= item.substring(item.indexOf('m')+1,item.indexOf('.'));
 //alert(grpId);
 var groupText= document.getElementById('cspbGroup'+grpId).innerHTML;
 //alert(groupText);
 //var itemrp= 'menuItem='+item.substring(item.indexOf('m')+1)+'"';
 var items= new Array();
 var browser=navigator.appName
if(browser=="Microsoft Internet Explorer")
 {
 items= groupText.split("<A class=");
 }
 else
 {
  items= groupText.split("<a class=");
 }
 var index= parseInt(item.substring(item.indexOf('.')+1));
 //alert(items[1]);
 var reptext= items[index].substring(items[index].indexOf('m')+1);
 //alert(reptext);
 if(browser=="Microsoft Internet Explorer")
 {
 reptext='cspbItmVisited'+reptext;
 }
 else
 {
 reptext='"cspbItmVisited'+reptext;
 }
 //alert(reptext);
 var newGroupText= groupText.replace(items[index],reptext,"gi");
 document.getElementById('cspbGroup'+grpId).innerHTML=newGroupText;
 //alert(newGroupText);
}


// Validation methods.

function validateSendSearch(form, ev,url)
{

	flag= true;
	var repositories= form.selectedRepositories;
	if(undefined!=repositories.length)
	{
	//alert("length="+repositories.length);
		for (i = 0; i < repositories.length; i++)
		{
			if(repositories[i].checked==true)
			{
				flag=false;
			}
		}
			if(flag)
			{
			alert("Please select atleast one repository");
			return false;
			}
	}
	else
	{
		if(repositories.checked ==false)
		{
			alert("Please select a repository");
			return false;
		}
	}
	var criteria= form.criteria.value;
	if(TrimString(criteria)=='')
	{
	 alert("Please enter a criteria");
	 return false;
	}
	flag=true;
	if(TrimString(criteria).indexOf('*')!=-1)
	{
		var affirm= confirm("You are about to do search that may take longer than a reqular search time.\nDo you wish to continue?");
		flag= affirm;
		if(affirm)
		{
		 sendEventX(ev,url);
		}else
		{
			return false;
		}
	}
	else
	{
		sendEventX(ev,url);
	}
	if(flag)
	{
	 var container=document.getElementById('details');
	 if(undefined!=container)
	 {
	 	container.innerHTML='';
	 }
	 document.getElementById('responsestatus').innerHTML="<img src=/web/image/hourglass.gif />";
 }
}

function validateSendMove(form, ev,url)
{
	var origins= form.selectedOrigins;
	flag= true;
	//alert("origins="+origins);
	if(undefined!=origins.length)
	{
	//alert("length="+origins.length);
			for (i = 0; i < origins.length; i++)
		 {
			 if(origins[i].checked ==true)
			 {
					flag=false;
			 }
		 }
			if(flag)
			{
			alert("Please select atleast one part to move to cart");
			return false;
			}
	}
	else
	if(origins.checked== false)
	{
		//alert("origins="+origins.checked);
		alert("Please select atleast one part to move to cart");
		return false;
	}
	//sendEventX(ev,url);
	sendEvent(ev);
}


function validateSendRemove(form, ev,url)
{
	var cartOrigins= form.selectedCartOrigins;
	flag=true;
	if(undefined!=cartOrigins.length)
	{
			for (i = 0; i < cartOrigins.length; i++)
			{
				 if(cartOrigins[i].checked ==true)
				 {
						flag=false;
					}
			}
				if(flag)
			 {
				alert("Please select atleast one part to be removed");
				return false;
			 }

	} else
	if(cartOrigins.checked== false)
	{
		alert("Please select atleast one part to be removed");
		return false;
	}
	sendEventX(ev,url);
}

function validateSendPublish(form, ev)
{
	var cartOrigins= form.selectedCartOrigins;
	flag=true;
	 if(undefined!=cartOrigins.length)
	{
		for (i = 0; i < cartOrigins.length; i++)
		{
				if(cartOrigins[i].checked ==true)
				{
						flag=false;
					}
		}
		if(flag)
		{
			alert("Please select atleast one part to be published");
			return false;
		}
	}
	else
	if(cartOrigins.checked== false)
	{
		alert("Please select atleast one part to be published");
		return false;
	}
	sendEvent(ev);
}
function resultAll(form)
{
	if(form.allResults.checked==true)
	{
		checkAll(form.selectedOrigins);
	}
	else
	{
		uncheckAll(form.selectedOrigins);
	}

}

function cartAll(form)
{
	if(form.allCarts.checked==true)
	{
		checkAll(form.selectedCartOrigins);
	}
	else
	{
		uncheckAll(form.selectedCartOrigins);
	}

}


function checkAll(field)
{
	if(undefined!=field.length)
	{
	for (i = 0; i < field.length; i++)
	field[i].checked = true ;
	}
	else
	{
		field.checked= true;
	}
}

function uncheckAll(field)
{
	if(undefined!=field.length)
	{
	for (i = 0; i < field.length; i++)
	field[i].checked = false ;
	}
	else
	{
		field.checked= false;
	}

}


function constructParams(formName){

  //Setup the return String
  returnString ="";

  //Get the form values
  formElements=document.forms[formName].elements;

  //loop through the array, building up the url
  //in the format '/strutsaction.do&name=value'

  for(var i=formElements.length-1;i>=0; --i ){
	  if(formElements[i].value.length >0) {
		  if(returnString.length ==0) {
			  returnString+=escape(formElements[i].name)+"="+escape(encodeURI(formElements[i].value));
		  } else {
				returnString+="&"+escape(formElements[i].name)+"="+escape(encodeURI(formElements[i].value));
		  }
	  }
	}
 //alert('return string '+returnString);
 return returnString;
}

function retrieveURL(url,nameOfFormToPost) {

  //convert the url to a string
  //url=url+getFormAsString(nameOfFormToPost);
	params=getFormAsString(nameOfFormToPost);
	//alert("params="+ params);
  //Do the AJAX call
  if (window.XMLHttpRequest) {

    // Non-IE browsers
    req = new XMLHttpRequest();
    req.onreadystatechange = processStateChange;
    try {
          req.open("POST", url, true);
          req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					req.setRequestHeader("Content-length", params.length);
					req.setRequestHeader("Connection", "close");
    } catch (e) {
      alert("Server Communication Problem\n"+e);
    }
    req.send(params);
  } else if (window.ActiveXObject) {
    // IE

    req = new ActiveXObject("Microsoft.XMLHTTP");
    if (req) {
      req.onreadystatechange=processStateChange;
      req.open("POST", url, true);
      req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.setRequestHeader("Content-length", params.length);
			req.setRequestHeader("Connection", "close");
      req.send(params);
    }
  }
}

function getFormAsString(formName){

  //Setup the return String
  returnString ="";

  //Get the form values
  formElements=document.forms[formName].elements;

  //loop through the array, building up the url
  //in the format '/strutsaction.do&name=value'

  for(var i=formElements.length-1;i>=0; --i ){
        //we escape (encode) each value
        var flag=true;
        if(formElements[i].type=="checkbox")
        {
        	//alert(formElements[i].type+" "+formElements[i].name);
        	if(formElements[i].checked==false)
        	{
        		flag=false;
        	}
        }
        if(flag)
        {
        //returnString+="&"+escape(formElements[i].name)+"="+escape(encodeURI(formElements[i].value));
		  if(returnString.length ==0) {
			  returnString+=escape(formElements[i].name)+"="+escape(encodeURI(formElements[i].value));
		  } else {
				returnString+="&"+escape(formElements[i].name)+"="+escape(encodeURI(formElements[i].value));
		  }
        }
 }

 //return the values
 //alert(returnString);
 return returnString;
}

function processStateChange() {

  if (req.readyState == 4) { // Complete
    if (req.status == 200) { // OK response
		//alert(req.responseText);
		if(undefined!=document.getElementById('responsestatus'))
		{
		 document.getElementById('responsestatus').innerHTML="";
		 }
    //Split the text response into Span elements
    spanElements =
        splitTextIntoSpan(req.responseText);
		//alert(spanElements);
    //Use these span elements to update the page
    replaceExistingWithNewHtml(spanElements);
		stripedTable();
    } else {
      alert("Problem with server response:\n "
        + req.statusText);
    }
  }
}

function replaceExistingWithNewHtml
        (newTextElements){

  //loop through newTextElements
  for(var i=newTextElements.length-1;i>=0;--i){

    //check that this begins with <span
    if(newTextElements[i].
        indexOf("<span")>-1){

          //get the span name - sits
      // between the 1st and 2nd quote mark
      //Make sure your spans are in the format
      //<span id="someName">NewContent</span>
          startNamePos=newTextElements[i].
              indexOf('"')+1;
      endNamePos=newTextElements[i].
              indexOf('"',startNamePos);
      name=newTextElements[i].
              substring(startNamePos,endNamePos);

      //get the content - everything
      // after the first > mark
      startContentPos=newTextElements[i].
               indexOf('>')+1;
      content=newTextElements[i].
               substring(startContentPos);

     //Now update the existing Document
     // with this element, checking that
     // this element exists in the document
     if(document.getElementById(name)){
                document.getElementById(name).
                innerHTML = content;
     }
  }
}
}
function splitTextIntoSpan(textToSplit){

  //Split the document
  returnElements=textToSplit.
            split("</span>")
	//alert(returnElements);
  //Process each of the elements
  for(var i=returnElements.length-1;i>=0;--i){

    //Remove everything before the 1st span
    spanPos = returnElements[i].
             indexOf("<span");

    //if we find a match, take out
    //everything before the span
    if(spanPos>0){
          subString=returnElements[i].
              substring(spanPos);
          returnElements[i]=subString;
    }
  }
  return returnElements;
}



function removeClassName (elem, className) {
	elem.className = elem.className.replace(className, "").trim();
}

function addCSSClass (elem, className) {
	removeClassName (elem, className);
	elem.className = (elem.className + " " + className).trim();
}

String.prototype.trim = function() {
	return this.replace( /^\s+|\s+$/, "" );
}

function stripedTable() {
//alert("got called");
	if (document.getElementById && document.getElementsByTagName) {
		var allTables = document.getElementsByTagName('table');
		if (!allTables) { return; }

		for (var i = 0; i < allTables.length; i++) {
			if (allTables[i].className.match(/[\w\s ]*tbl[\w\s ]*/)) {
				var trs = allTables[i].getElementsByTagName("tr");
				for (var j = 0; j < trs.length; j++) {
					removeClassName(trs[j], 'alternateRow');
					addCSSClass(trs[j], 'normalRow');
				}
				for (var k = 0; k < trs.length; k += 2) {
					removeClassName(trs[k], 'normalRow');
					addCSSClass(trs[k], 'alternateRow');
				}
			}
		}
	}
}
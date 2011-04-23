var critFields=new materializeArray(88);

function autoSubmitCriteria(formElement, ev) {
  var keycode=getKeycode();
  if (keycode != 13) return true;
  sendCriteria(ev);
  return false;
}

function sendCriteria(ev) {
  var idx=0;
  var key, name, value;
  var criteria = "( ";
  while(idx < critFields.length && critFields[idx]!=undefined) {
    key = "criteria"+idx;
    name = critFields[idx++]; 
    value = document.forms[0].elements[key].value;
    if (""!=value) value = trim(value);
    if (""!=value) {
     if (criteria == "( ")criteria += name+"='"+value+"'";
     else criteria += " + " +name+"='"+value+"'";
    }
  }
  criteria += " )";
  if (criteria == "(  )") criteria="";
  document.forms[0].criteria.value = criteria;
  sendEvent(ev);
}

function populateCriteria() {
  var key;
  var triggered;
  var c = document.forms[0].criteria.value;
  if (c.length>5) c=c.substring(2, c.length-2);
  var pairs = c.split("+");
  for(i=0; i < pairs.length; i++) {
    var pair = pairs[i].split("=");
    triggered=false;
    for (j=0;!triggered && j< critFields.length && critFields[j]!=undefined; j++) {
      if (critFields[j]==trim(pair[0])) {
        key = "criteria"+j; 
        document.forms[0].elements[key].value=trimSingleQuotes(pair[1]);
        triggered=true;
      }
    } 
  }
}


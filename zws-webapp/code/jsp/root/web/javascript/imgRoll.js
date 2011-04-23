/****************************************************************************************
Reference:
 Script to automate image rollovers and click effects, using just HTML
 v1.1 written by Mark Wilton-Jones, 30-31/12/2003
Please see http://www.howtocreate.co.uk/jslibs/ for details and a demo of this script
Please see http://www.howtocreate.co.uk/tutorials/jsexamples/testingRoll.html for examples
Please see http://www.howtocreate.co.uk/jslibs/termsOfUse.html for terms of use
*****************************************************************************************/
var gifImg;
var gifImgPath;
var pathSep = "/";
var imgRoot = "/web/button";
var theme = "zws";
var MWJ_img_cache = new Object();
var imageArray = document.images;
var nameIDX;
var themePath="";
for( var imgIDX = 0; imageArray[imgIDX]; imgIDX++ ) {
  gifImg = imageArray[imgIDX];
  themePath="";
  if( gifImg.getAttribute ) { 
    if (gifImg.src.indexOf("_nav")== (gifImg.src.length-4)) {
      nameIDX = gifImg.src.length;
      while(gifImg.src.charAt(nameIDX-1)!='/' && nameIDX > 0) nameIDX--;
      gifImg.gif = gifImg.src.substring(nameIDX);
      gifImg.path = "panel-bar/" + theme;
    }
    else gifImg.gif = gifImg.getAttribute('gif');
    if (!gifImg.gif) continue;
    gifImg.inactive = gifImg.getAttribute('inactive');
    gifImgPath=gifImg.path
    if (!gifImgPath) gifImgPath = gifImg.getAttribute('path'); 
    var gifLocation=imgRoot + pathSep;
    if (!gifImgPath) gifLocation += gifImg.gif;
    else gifLocation += gifImgPath + pathSep + gifImg.gif;
    gifImg.border=0;
    gifImg.src=gifLocation +".gif";
    if (gifImg.inactive) {
      gifImg.hoversrc=gifLocation +"_inactive.gif";
      var gifMsg = "This button is not active.";
      if (gifImg.getAttribute('message')){
        gifMsg += "\n" + gifImg.getAttribute('message');
      }
      gifImg.onmousedown = function () { alert(gifMsg); };
    }
    else {
      gifImg.hoversrc = gifLocation +"_on.gif";
      gifImg.activesrc = gifLocation +"_active.gif";
    }
  }
  if( gifImg.hoversrc || gifImg.activesrc ) {
    if( !MWJ_img_cache[gifImg.src] ) { MWJ_img_cache[gifImg.src] = new Image(); MWJ_img_cache[gifImg.src].src = gifImg.src; }
    gifImg.rootsrc = gifImg.src;
    gifImg.onmouseout = function () { this.src = this.rootsrc; };
  }
  if( gifImg.hoversrc ) {
    if( !MWJ_img_cache[gifImg.hoversrc] ) { MWJ_img_cache[gifImg.hoversrc] = new Image(); MWJ_img_cache[gifImg.hoversrc].src = gifImg.hoversrc; }
    gifImg.onmouseover = function () { this.src = this.hoversrc; };
  }
  if( gifImg.activesrc ) {
    if( !MWJ_img_cache[gifImg.activesrc] ) { MWJ_img_cache[gifImg.activesrc] = new Image(); MWJ_img_cache[gifImg.activesrc].src = gifImg.activesrc; }
    gifImg.onmousedown = function (e) {
      e = e ? e : window.event;
      if( e.button > 1 || e.which > 1 ) { return; }
      this.src = this.activesrc;
    };
    gifImg.onmouseup = function (e) {
      e = e ? e : window.event;
      if( e.button > 1 || e.which > 1 ) { return; }
      this.src = this.hoversrc ? this.hoversrc : this.rootsrc;
    };
  }
}

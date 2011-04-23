<?xml version="1.0" encoding="UTF-8"?>

<project name="cruise-control-project-path" basedir=".">

  <!-- cc-spool svn repository environment -->
  <property name="cc-spool" location="\var\spool\cruisecontrol\projects"/>
  <property name="svn-repository-name" value="zws"/>
  <property name="workspace" location="${cc-spool}\${svn-repository-name}"/>

  <!-- project base -->
  <property name="base" location="."/>
  
  <!-- zws build scripts-->
  <property name="build-scripts" location="${cc-spool}\zws\build\script"/>

  <!-- www directory -->
  <property name="www" location="\var\www"/>

  <!-- 3rd party jar dependencies-->
  <property name="deps" location="${cc-spool}\zws\build\dep"/> <!-- Set to www dir to download dependencies -->

  <!-- AXIS2 Home -->
  <property name="AXIS2_HOME" value="${deps}\Axis2-1.3"/>

  <!-- zws published dependencies -->
  <property name="published" location="${cc-spool}\zws\build\pub"/>

  <!-- ZWS compiled distribution -->
  <property name="dist" location="${workspace}\build\pub"/>

  <!-- releases -->
  <property name="released" location="${www}\build\release"/>
  
  <!-- build identifier -->
  <property name="cc-build" value="true"/>	
  <property name="eclipse-build" value="false"/>	

  <!-- debug mode -->
  <property name="debug-mode-on" value="true"/>

</project>
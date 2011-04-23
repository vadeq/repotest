package zws.securespace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 24, 2004, 2:01 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;
/*
 * SecureSpace Security Model
 *
 * Security model allows only for access.

 * UserSpaces represent "Areas" that users can belong who have privileges to access Data and its Actions
 * UserSpace examples: Roles, Groups, Sites, SecurityProfiles
 
 * Any Data (such as a report) has a corresponding DataSpace (1 to 1 for every instance of data)
 * Data must be named.
 * DataSpace represents access to information (reports) or services (Intralink, Web Modules)
 *  - DataSpaces examples: Domains, Applications, Repositories, Drive Locations, Servers, 
 
 * Data may have actions associated with it
 * Actions, since they act on data, also have a coresponding DataSpace (1 to 1 for any action on an instance of Data)
 * DataSpace actions represent an action that is available for a given DataSpace
 *  - DataSpaceAction exmaples: create, delete, checkin, navigate to module

 * A set of DataSpaceActions can be defined for a DataSpace
 * 1 - many : DataSpace to DataSpaceActions
 
 * Users are allowed into UserSpaces
 * DataSpaces are included into UserSpaces
 * Users have access to all DataSpaces that have been included in any of their UserSpace
 * This union defines what data a user can see and what actions they can perform on any data
 * - access to actions are defined by including DataSpaceActions (which are also DataSpaces) into a UserSpace
 * - DataSpaceActions can only be included in a UserSpace if their Parent DataSpace has been inluded
 
 */
//UserSpaces provide categories that groups of users can be a part of
//DataSpaces provide access to data
//DataSpaces may be password protected
//Domains are password protected dataspaces (not userspaces) that users can access
//Domains provide access to areas of data
public interface SecureSpace {
  public String getSpace();
  public Namespace getNamespace();
  public String getName();
  public String getType();
  public String getDescription();
  public boolean equals(SecureSpace space);
}

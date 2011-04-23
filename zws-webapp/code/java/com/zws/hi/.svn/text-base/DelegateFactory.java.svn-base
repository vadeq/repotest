package com.zws.hi; 
import com.zws.util.StringUtil;

/*
 DesignState - Design Compression Technology.
 @author: Rahul Deshmukh
 Created on Mar 30, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

/**
 * A <b>DelegateFactory </b> 
 *
 * @author Rahul Deshmukh
 * @version 1.0
 */
public class DelegateFactory {

    public static Object getBusinessObject(String property, Class type) {
        String className = property;
        Class bizobClass = null;
        Object bizobClassInstance = null;

        try {
            bizobClass = Class.forName(className);
            bizobClassInstance = bizobClass.newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        
        if (!(type.isAssignableFrom(bizobClass))) {
            throw new IllegalArgumentException("DelegateFactory:getBusinessObject:" + " Class represented by the "
                                               + "property " + property + "is not an instance of " + type.getName());

        }
        return bizobClassInstance;
    }
}
   

   


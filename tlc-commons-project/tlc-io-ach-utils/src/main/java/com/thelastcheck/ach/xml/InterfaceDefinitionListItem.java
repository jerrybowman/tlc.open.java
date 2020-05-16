/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id: InterfaceDefinitionListItem.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.ach.xml;

/**
 * Class InterfaceDefinitionListItem.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class InterfaceDefinitionListItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _interfaceDefinition.
     */
    private InterfaceDefinition _interfaceDefinition;


      //----------------/
     //- Constructors -/
    //----------------/

    public InterfaceDefinitionListItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'interfaceDefinition'.
     * 
     * @return the value of field 'InterfaceDefinition'.
     */
    public InterfaceDefinition getInterfaceDefinition(
    ) {
        return this._interfaceDefinition;
    }

    /**
     * Sets the value of field 'interfaceDefinition'.
     * 
     * @param interfaceDefinition the value of field
     * 'interfaceDefinition'.
     */
    public void setInterfaceDefinition(
            final InterfaceDefinition interfaceDefinition) {
        this._interfaceDefinition = interfaceDefinition;
    }

}

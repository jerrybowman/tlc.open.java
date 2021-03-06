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
 * $Id: InterfaceDefinitionItem.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class InterfaceDefinitionItem.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class InterfaceDefinitionItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _interfaceField.
     */
    private InterfaceField _interfaceField;


      //----------------/
     //- Constructors -/
    //----------------/

    public InterfaceDefinitionItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'interfaceField'.
     * 
     * @return the value of field 'InterfaceField'.
     */
    public InterfaceField getInterfaceField(
    ) {
        return this._interfaceField;
    }

    /**
     * Sets the value of field 'interfaceField'.
     * 
     * @param interfaceField the value of field 'interfaceField'.
     */
    public void setInterfaceField(
            final InterfaceField interfaceField) {
        this._interfaceField = interfaceField;
    }

}

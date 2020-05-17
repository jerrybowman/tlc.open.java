/*
 * ****************************************************************************
 *  Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *  <p/>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p/>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p/>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.io.base;

import org.junit.Assert;
import org.junit.Test;

public class FieldTypeTest {

    @Test
    public void getCode() {
        String a = FieldType.AMOUNT.getCode();
        Assert.assertEquals("A", a);
    }

    @Test
    public void forCode() {
        FieldType a = FieldType.forCode("A");
        Assert.assertEquals(FieldType.AMOUNT, a);
    }

    @Test
    public void forCodeBad() {
        try {
            FieldType.forCode("X");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("There is no mapping defined for: X, only know: S=STRING,B=BINARY,I=INT,L=LONG,N=NUMBER,D=DATE,T=TIME,R=ROUTING_NUMBER,U=ONUS,A=AMOUNT", e.getLocalizedMessage());
        }
    }
}
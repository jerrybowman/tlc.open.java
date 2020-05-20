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

package com.thelastcheck.commons.base.utils;

import org.junit.Test;

import static com.thelastcheck.commons.base.utils.CheckDigitVerifiers.Verifier.LuhnMod10;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckDigitLuhnMod10VerifierTest {

    @Test
    public void testIsValid() {
        CheckDigitVerifier verifier = CheckDigitVerifiers.getVerifier(LuhnMod10);
        assertTrue(verifier.isValid("79927398713"));
        assertTrue(verifier.isValid("49927398716"));
        assertFalse(verifier.isValid("49927398717"));
        assertTrue(verifier.isValid("1234567812345670"));
        assertFalse(verifier.isValid("1234567812345678"));
        assertFalse(verifier.isValid("294217778"));
    }
}

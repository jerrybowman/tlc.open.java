/*
 * Copyright (c) 2009-2024 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thelastcheck.commons.base.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CredentialsEncrypterTest {

    @Test
    public void testCipher() throws CredentialsEncryptionException {
        Credentials c = new Credentials("username", "password", "user data");
        CredentialsEncrypter encrypter = new CredentialsEncrypter();
        byte[] bytes = encrypter.encrypt(c);
        Credentials c2 = encrypter.decrypt(bytes);
        assertEquals(c, c2);
    }
}

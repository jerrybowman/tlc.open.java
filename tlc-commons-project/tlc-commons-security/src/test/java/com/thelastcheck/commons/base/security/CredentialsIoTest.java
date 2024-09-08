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

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CredentialsIoTest {

    private File workingDir = new File("target/work");

    @Before
    public void ensureWorkingDirExists() throws IOException {
        if (!workingDir.exists())
            FileUtils.forceMkdir(workingDir);
    }

    @Test
    public void testFile() throws IOException, CredentialsEncryptionException {
        Credentials c = new Credentials("username", "password", "user data");

        File file = new File(workingDir, "credentials.dat");

        CredentialsWriter writer = new CredentialsWriter(file);
        writer.write(c);

        CredentialsReader reader = new CredentialsReader(file);
        Credentials c2 = reader.read();

        assertEquals(c, c2);
    }
}

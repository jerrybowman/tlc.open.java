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
package com.thelastcheck.commons.base.security.main;


import com.thelastcheck.commons.base.security.Credentials;
import com.thelastcheck.commons.base.security.CredentialsEncryptionException;
import com.thelastcheck.commons.base.security.CredentialsWriter;

import java.io.File;
import java.io.IOException;

public class CreateCredentialsFileMain {
    
    public static void main(String[] args) {
        try {
            validateArgs(args);
            run(args);
        } catch (Exception e) {
            System.out.println("Problem creating credentials file: " + e.getMessage());
        }
    }

    public static void run(String[] args) throws IOException, CredentialsEncryptionException {
        File file = new File(args[0]);
        String username = args[1];
        String password = args[2];
        String userData = null;
        
        if (args.length >= 4)
            userData = args[3];
        
        Credentials cred = new Credentials(username, password, userData);
        CredentialsWriter writer = new CredentialsWriter(file);
        writer.write(cred);
        
        System.out.println("Saved credentials in file: " + file.getAbsolutePath() );
    }

    private static void validateArgs(String[] args) {
        if (args.length < 3 || args.length > 4)
            throw new IllegalArgumentException("Invalid usage: must specify output_file username password [userdata]");
    }
}

/**
 * ****************************************************************************
 * The Last Check, LLC
 * 9499 Grove Trail Lane
 * Germantown, TN 38139
 * <p/>
 * Unauthorized distribution, adaptation or use may be subject to civil and
 * criminal penalties.
 * <p/>
 * Copyright (c) 2015, The Last Check, LLC, All rights reserved.
 * ****************************************************************************
 */

package com.thelastcheck.commons.base.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CredentialsReader {

    private File file;

    public CredentialsReader(File file) {
        this.file = file;
    }

    public Credentials read() throws IOException, CredentialsEncryptionException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] ba = new byte[(int) file.length()];
            fis.read(ba);
            CredentialsEncrypter encrypter = new CredentialsEncrypter();
            return encrypter.decrypt(ba);
        }
    }
}

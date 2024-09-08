/*******************************************************************************
 * The Last Check, LLC
 * 9499 Grove Trail Lane
 * Germantown, TN 38139
 *
 * Unauthorized distribution, adaptation or use may be subject to civil and
 * criminal penalties.
 *
 * Copyright (c) 2015, The Last Check, LLC, All rights reserved.
 ******************************************************************************/

package com.thelastcheck.commons.base.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CredentialsWriter {

    final private File file;

    public CredentialsWriter(File file) {
        this.file = file;
    }

    public void write(Credentials credentials) throws IOException, CredentialsEncryptionException {
        CredentialsEncrypter encrypter = new CredentialsEncrypter();
        byte[] bytes = encrypter.encrypt(credentials);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
    }
}

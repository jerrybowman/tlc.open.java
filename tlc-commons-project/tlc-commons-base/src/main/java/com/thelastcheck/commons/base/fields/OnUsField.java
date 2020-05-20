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

package com.thelastcheck.commons.base.fields;

public class OnUsField {

    private static final String STRING_20 = "                    ";
    private static final String EMPTY_STRING = "";
    private static final char ONUS_FIELD_SEPARATOR = '/';
    private static final int MAX_ONUS_FIELD_LENGTH = 20;

    private String optionalField4;
    private String accountNumber;
    private String tranCode;

    public static OnUsField valueOf(String onusValue) {
        return new OnUsField(onusValue);
    }

    @SuppressWarnings("unused")
    private OnUsField() {
    }

    public OnUsField(String value) {
        parse(value);
    }

    public OnUsField(String optionalField4,
                     String accountNumber,
                     String tranCode) {
        this.optionalField4 = optionalField4.trim();
        this.accountNumber = accountNumber.trim();
        this.tranCode = tranCode.trim();
    }

    private void parse(String value) {
        optionalField4 = EMPTY_STRING;
        accountNumber = EMPTY_STRING;
        tranCode = EMPTY_STRING;
        int token = value.lastIndexOf(ONUS_FIELD_SEPARATOR);
        if (token == -1) {
            tranCode = value.trim();
            return;
        }
        tranCode = value.substring(token + 1).trim();
        value = value.substring(0, token);
        token = value.lastIndexOf('/');
        if (token == -1) {
            accountNumber = value.trim();
            return;
        }
        optionalField4 = value.substring(0, token).trim();
        accountNumber = value.substring(token + 1).trim();
    }

    public String getOptionalField4() {
        return optionalField4;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTranCode() {
        return tranCode;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof OnUsField) {
            OnUsField anOnUsField = (OnUsField) anObject;
            return this.optionalField4.equals(anOnUsField.optionalField4)
                    && this.accountNumber.equals(anOnUsField.accountNumber)
                    && this.tranCode.equals(anOnUsField.tranCode);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(MAX_ONUS_FIELD_LENGTH);
        if (!optionalField4.isEmpty()) {
            sb.append(optionalField4);
            sb.append("/");
        }
        if (!accountNumber.isEmpty()) {
            sb.append(accountNumber);
            sb.append("/");
        } else {
            if (sb.length() > 0) {
                sb.append("/");
            }
        }
        if (!tranCode.isEmpty()) {
            sb.append(tranCode);
        }
        String onusField = sb.toString();
        if (onusField.length() > MAX_ONUS_FIELD_LENGTH) {
            onusField = onusField.substring(onusField.length() - MAX_ONUS_FIELD_LENGTH);
        }
        if (onusField.length() < MAX_ONUS_FIELD_LENGTH) {
            onusField = STRING_20.substring(0, MAX_ONUS_FIELD_LENGTH - onusField.length()) + onusField;
        }
        return onusField;
    }
}

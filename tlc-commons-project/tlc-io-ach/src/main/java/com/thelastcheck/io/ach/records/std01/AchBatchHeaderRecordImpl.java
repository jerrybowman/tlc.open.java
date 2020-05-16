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

package com.thelastcheck.io.ach.records.std01;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.ach.records.AchBatchHeaderRecord;
import com.thelastcheck.io.ach.records.base.AchBatchHeaderRecordBase;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;

public class AchBatchHeaderRecordImpl extends AchBatchHeaderRecordBase {

    private static int maxFieldNumber = 13;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("ServiceClassCode", 2, 1, 3, FieldType.STRING);
        fields[3] = new Field("CompanyName", 3, 4, 16, FieldType.STRING);
        fields[4] = new Field("CompanyDiscretionaryData", 4, 20, 20, FieldType.STRING);
        fields[5] = new Field("CompanyIdentification", 5, 40, 10, FieldType.STRING);
        fields[6] = new Field("StandardEntryClassCode", 6, 50, 3, FieldType.STRING);
        fields[7] = new Field("CompanyEntryDescription", 7, 53, 10, FieldType.STRING);
        fields[8] = new Field("CompanyDescriptiveDate", 8, 63, 6, FieldType.DATE);
        fields[9] = new Field("EffectiveEntryDate", 9, 69, 6, FieldType.DATE);
        fields[10] = new Field("SettlementDate", 10, 75, 3, FieldType.STRING);
        fields[11] = new Field("OriginatorStatusCode", 11, 78, 1, FieldType.STRING);
        fields[12] = new Field("OriginatingDfiIdentification", 12, 79, 8, FieldType.STRING);
        fields[13] = new Field("BatchNumber", 13, 87, 7, FieldType.INT);
    }


    /*
     * AchBatchHeaderRecordImpl
     */

    public AchBatchHeaderRecordImpl() {
        super();
    }

    public AchBatchHeaderRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public AchBatchHeaderRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public AchBatchHeaderRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
    }

    @Override
    public int numberOfFields() {
        return maxFieldNumber;
    }

    @Override
    protected Field field(int fieldNumber) {
        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {
            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);
        }
        return fields[fieldNumber];
    }


    public String serviceClassCode() {
        return getFieldAsString(field(2));
    }

    public AchBatchHeaderRecord serviceClassCode(String value) {
        setField(value, field(2));
        return this;
    }

    public String companyName() {
        return getFieldAsString(field(3));
    }

    public AchBatchHeaderRecord companyName(String value) {
        setField(value, field(3));
        return this;
    }

    public String companyDiscretionaryData() {
        return getFieldAsString(field(4));
    }

    public AchBatchHeaderRecord companyDiscretionaryData(String value) {
        setField(value, field(4));
        return this;
    }

    public String companyIdentification() {
        return getFieldAsString(field(5));
    }

    public AchBatchHeaderRecord companyIdentification(String value) {
        setField(value, field(5));
        return this;
    }

    public String standardEntryClassCode() {
        return getFieldAsString(field(6));
    }

    public AchBatchHeaderRecord standardEntryClassCode(String value) {
        setField(value, field(6));
        return this;
    }

    public String companyEntryDescription() {
        return getFieldAsString(field(7));
    }

    public AchBatchHeaderRecord companyEntryDescription(String value) {
        setField(value, field(7));
        return this;
    }

    public Date companyDescriptiveDate()
        throws InvalidDataException {
        return getFieldAsDate(field(8), x9TimeZone);
    }

    public AchBatchHeaderRecord companyDescriptiveDate(Date value) {
        setFieldDate(value, field(8), x9TimeZone);        return this;
    }

    public String companyDescriptiveDateAsString() {
        return getFieldAsString(field(8));
    }

    public AchBatchHeaderRecord companyDescriptiveDate(String value) {
        setField(value, field(8));
        return this;
    }

    public Date effectiveEntryDate()
        throws InvalidDataException {
        return getFieldAsDate(field(9), x9TimeZone);
    }

    public AchBatchHeaderRecord effectiveEntryDate(Date value) {
        setFieldDate(value, field(9), x9TimeZone);        return this;
    }

    public String effectiveEntryDateAsString() {
        return getFieldAsString(field(9));
    }

    public AchBatchHeaderRecord effectiveEntryDate(String value) {
        setField(value, field(9));
        return this;
    }

    public String settlementDate() {
        return getFieldAsString(field(10));
    }

    public AchBatchHeaderRecord settlementDate(String value) {
        setField(value, field(10));
        return this;
    }

    public String originatorStatusCode() {
        return getFieldAsString(field(11));
    }

    public AchBatchHeaderRecord originatorStatusCode(String value) {
        setField(value, field(11));
        return this;
    }

    public String originatingDfiIdentification() {
        return getFieldAsString(field(12));
    }

    public AchBatchHeaderRecord originatingDfiIdentification(String value) {
        setField(value, field(12));
        return this;
    }

    public String batchNumber() {
        return getFieldAsString(field(13));
    }

    public AchBatchHeaderRecord batchNumber(String value) {
        setField(value, field(13));
        return this;
    }

    public int batchNumberAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(13));
    }

    public AchBatchHeaderRecord batchNumber(int value) {
        setField(value, field(13));
        return this;
    }

}


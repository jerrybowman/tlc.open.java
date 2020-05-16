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

package com.thelastcheck.io.ach.records.base;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.ach.AchRecordImpl;
import com.thelastcheck.io.ach.records.AchBatchHeaderRecord;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;

import java.util.Date;

public abstract class AchBatchHeaderRecordBase extends AchRecordImpl
        implements AchBatchHeaderRecord {



    /*
     * AchBatchHeaderRecordBase
     */

    public AchBatchHeaderRecordBase() {
        super();
        recordType(TYPE_BATCH_HEADER);
    }

    public AchBatchHeaderRecordBase(int stdLevel) {
        super(TYPE_BATCH_HEADER, stdLevel);
    }

    public AchBatchHeaderRecordBase(String encoding, int stdLevel) {
        super(TYPE_BATCH_HEADER, encoding, stdLevel);
    }

    public AchBatchHeaderRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String recordTypeCode() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord recordTypeCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String serviceClassCode() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord serviceClassCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String companyName() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String companyDiscretionaryData() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyDiscretionaryData(String value) {
        throw new InvalidStandardLevelException();
    }

    public String companyIdentification() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyIdentification(String value) {
        throw new InvalidStandardLevelException();
    }

    public String standardEntryClassCode() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord standardEntryClassCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String companyEntryDescription() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyEntryDescription(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date companyDescriptiveDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyDescriptiveDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String companyDescriptiveDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord companyDescriptiveDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date effectiveEntryDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord effectiveEntryDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String effectiveEntryDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord effectiveEntryDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String settlementDate() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord settlementDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String originatorStatusCode() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord originatorStatusCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String originatingDfiIdentification() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord originatingDfiIdentification(String value) {
        throw new InvalidStandardLevelException();
    }

    public String batchNumber() {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord batchNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public int batchNumberAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public AchBatchHeaderRecord batchNumber(int value) {
        throw new InvalidStandardLevelException();
    }

}


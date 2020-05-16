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
import com.thelastcheck.io.ach.records.AchFileHeaderRecord;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;

import java.util.Date;

public abstract class AchFileHeaderRecordBase extends AchRecordImpl
        implements AchFileHeaderRecord {



    /*
     * AchFileHeaderRecordBase
     */

    public AchFileHeaderRecordBase() {
        super();
        recordType(TYPE_FILE_HEADER);
    }

    public AchFileHeaderRecordBase(int stdLevel) {
        super(TYPE_FILE_HEADER, stdLevel);
    }

    public AchFileHeaderRecordBase(String encoding, int stdLevel) {
        super(TYPE_FILE_HEADER, encoding, stdLevel);
    }

    public AchFileHeaderRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String priorityCode() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord priorityCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateDestination() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord immediateDestination(String value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateOrigin() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord immediateOrigin(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date fileCreationDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord fileCreationDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String fileCreationDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord fileCreationDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date fileCreationTime()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord fileCreationTime(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String fileCreationTimeAsString() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord fileCreationTime(String value) {
        throw new InvalidStandardLevelException();
    }

    public String fileIDModifier() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord fileIDModifier(String value) {
        throw new InvalidStandardLevelException();
    }

    public String recordSize() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord recordSize(String value) {
        throw new InvalidStandardLevelException();
    }

    public String blockingFactor() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord blockingFactor(String value) {
        throw new InvalidStandardLevelException();
    }

    public String formatCode() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord formatCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String destination() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord destination(String value) {
        throw new InvalidStandardLevelException();
    }

    public String companyName() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord companyName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String referenceCode() {
        throw new InvalidStandardLevelException();
    }

    public AchFileHeaderRecord referenceCode(String value) {
        throw new InvalidStandardLevelException();
    }

}


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

package com.thelastcheck.io.ach.records;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.ach.AchRecord;

import java.util.Date;

public interface AchBatchHeaderRecord extends AchRecord {


    /*
     * AchBatchHeaderRecord
     */

    String recordTypeCode();
    AchBatchHeaderRecord recordTypeCode(String value);

    String serviceClassCode();
    AchBatchHeaderRecord serviceClassCode(String value);

    String companyName();
    AchBatchHeaderRecord companyName(String value);

    String companyDiscretionaryData();
    AchBatchHeaderRecord companyDiscretionaryData(String value);

    String companyIdentification();
    AchBatchHeaderRecord companyIdentification(String value);

    String standardEntryClassCode();
    AchBatchHeaderRecord standardEntryClassCode(String value);

    String companyEntryDescription();
    AchBatchHeaderRecord companyEntryDescription(String value);

    public Date companyDescriptiveDate()
        throws InvalidDataException;
    AchBatchHeaderRecord companyDescriptiveDate(Date value);

    String companyDescriptiveDateAsString();
    AchBatchHeaderRecord companyDescriptiveDate(String value);

    public Date effectiveEntryDate()
        throws InvalidDataException;
    AchBatchHeaderRecord effectiveEntryDate(Date value);

    String effectiveEntryDateAsString();
    AchBatchHeaderRecord effectiveEntryDate(String value);

    String settlementDate();
    AchBatchHeaderRecord settlementDate(String value);

    String originatorStatusCode();
    AchBatchHeaderRecord originatorStatusCode(String value);

    String originatingDfiIdentification();
    AchBatchHeaderRecord originatingDfiIdentification(String value);

    String batchNumber();
    AchBatchHeaderRecord batchNumber(String value);

    public int batchNumberAsInt()
        throws InvalidDataException;
    AchBatchHeaderRecord batchNumber(int value);

}


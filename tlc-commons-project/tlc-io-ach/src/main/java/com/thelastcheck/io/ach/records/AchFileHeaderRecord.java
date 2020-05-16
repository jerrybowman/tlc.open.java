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

public interface AchFileHeaderRecord extends AchRecord {


    /*
     * AchFileHeaderRecord
     */

    String priorityCode();
    AchFileHeaderRecord priorityCode(String value);

    String immediateDestination();
    AchFileHeaderRecord immediateDestination(String value);

    String immediateOrigin();
    AchFileHeaderRecord immediateOrigin(String value);

    public Date fileCreationDate()
        throws InvalidDataException;
    AchFileHeaderRecord fileCreationDate(Date value);

    String fileCreationDateAsString();
    AchFileHeaderRecord fileCreationDate(String value);

    public Date fileCreationTime()
        throws InvalidDataException;
    AchFileHeaderRecord fileCreationTime(Date value);

    String fileCreationTimeAsString();
    AchFileHeaderRecord fileCreationTime(String value);

    String fileIDModifier();
    AchFileHeaderRecord fileIDModifier(String value);

    String recordSize();
    AchFileHeaderRecord recordSize(String value);

    String blockingFactor();
    AchFileHeaderRecord blockingFactor(String value);

    String formatCode();
    AchFileHeaderRecord formatCode(String value);

    String destination();
    AchFileHeaderRecord destination(String value);

    String companyName();
    AchFileHeaderRecord companyName(String value);

    String referenceCode();
    AchFileHeaderRecord referenceCode(String value);

}


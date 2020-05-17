/*
 * ******************************************************************************
 *  Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ******************************************************************************
 */

package com.thelastcheck.io.x937.records;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.x9.X9Record;

public interface X937BundleHeaderRecord extends X9Record {

    String collectionTypeIndicator();
    X937BundleHeaderRecord collectionTypeIndicator(String value);

    RoutingNumber destinationRoutingNumber();
    X937BundleHeaderRecord destinationRoutingNumber(RoutingNumber value);

    String destinationRoutingNumberAsString();
    X937BundleHeaderRecord destinationRoutingNumber(String value);

    RoutingNumber ECEInstitutionRoutingNumber();
    X937BundleHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value);

    String ECEInstitutionRoutingNumberAsString();
    X937BundleHeaderRecord ECEInstitutionRoutingNumber(String value);

    Date bundleBusinessDate() throws InvalidDataException;
    X937BundleHeaderRecord bundleBusinessDate(Date value);

    String bundleBusinessDateAsString();
    X937BundleHeaderRecord bundleBusinessDate(String value);

    Date bundleCreationDate() throws InvalidDataException;
    X937BundleHeaderRecord bundleCreationDate(Date value);

    String bundleCreationDateAsString();
    X937BundleHeaderRecord bundleCreationDate(String value);

    String bundleID();
    X937BundleHeaderRecord bundleID(String value);

    String bundleSequenceNumber();
    X937BundleHeaderRecord bundleSequenceNumber(String value);

    String cycleNumber();
    X937BundleHeaderRecord cycleNumber(String value);

    RoutingNumber returnLocationRoutingNumber();
    X937BundleHeaderRecord returnLocationRoutingNumber(RoutingNumber value);

    String returnLocationRoutingNumberAsString();
    X937BundleHeaderRecord returnLocationRoutingNumber(String value);

    String userField();
    X937BundleHeaderRecord userField(String value);

    String reserved();
    X937BundleHeaderRecord reserved(String value);

}


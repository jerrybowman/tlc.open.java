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

package com.thelastcheck.io.x937.records.std1994;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.base.X937ReturnAddendumARecordBase;

public class X937ReturnAddendumARecordImpl extends X937ReturnAddendumARecordBase {

    private static int maxFieldNumber = 13;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("BOFDRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("BOFDBusinessDateConfidenceIndicator", 3, 11, 1, FieldType.STRING);
        fields[4] = new Field("BOFDBusinessDate", 4, 12, 8, FieldType.DATE);
        fields[5] = new Field("BOFDItemSequenceNumberConfidenceIndicator", 5, 20, 1, FieldType.STRING);
        fields[6] = new Field("BOFDItemSequenceNumber", 6, 21, 15, FieldType.STRING);
        fields[7] = new Field("DepositAccountNumberAtBOFDConfidenceIndicator", 7, 36, 1, FieldType.STRING);
        fields[8] = new Field("DepositAccountNumberAtBOFD", 8, 37, 18, FieldType.STRING);
        fields[9] = new Field("BOFDDepositBranchConfidenceIndicator", 9, 55, 1, FieldType.STRING);
        fields[10] = new Field("BOFDDepositBranch", 10, 56, 5, FieldType.STRING);
        fields[11] = new Field("PayeeNameConfidenceIndicator", 11, 61, 1, FieldType.STRING);
        fields[12] = new Field("PayeeName", 12, 62, 14, FieldType.STRING);
        fields[13] = new Field("Reserved", 13, 76, 4, FieldType.STRING);
    }


    /*
     * X937ReturnAddendumARecordImpl
     */

    public X937ReturnAddendumARecordImpl() {
        super();
    }

    public X937ReturnAddendumARecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ReturnAddendumARecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ReturnAddendumARecordImpl(ByteArray record, int stdLevel) {
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


    public RoutingNumber BOFDRoutingNumber() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937ReturnAddendumARecord BOFDRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String BOFDRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937ReturnAddendumARecord BOFDRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String BOFDBusinessDateConfidenceIndicator() {
        return getFieldAsString(field(3));
    }

    public X937ReturnAddendumARecord BOFDBusinessDateConfidenceIndicator(String value) {
        setField(value, field(3));
        return this;
    }

    public Date BOFDBusinessDate()
        throws InvalidDataException {
        return getFieldAsDate(field(4), x9TimeZone);
    }

    public X937ReturnAddendumARecord BOFDBusinessDate(Date value) {
        setFieldDate(value, field(4), x9TimeZone);        return this;
    }

    public String BOFDBusinessDateAsString() {
        return getFieldAsString(field(4));
    }

    public X937ReturnAddendumARecord BOFDBusinessDate(String value) {
        setField(value, field(4));
        return this;
    }

    public String BOFDItemSequenceNumberConfidenceIndicator() {
        return getFieldAsString(field(5));
    }

    public X937ReturnAddendumARecord BOFDItemSequenceNumberConfidenceIndicator(String value) {
        setField(value, field(5));
        return this;
    }

    public String BOFDItemSequenceNumber() {
        return getFieldAsString(field(6));
    }

    public X937ReturnAddendumARecord BOFDItemSequenceNumber(String value) {
        setField(value, field(6));
        return this;
    }

    public String depositAccountNumberAtBOFDConfidenceIndicator() {
        return getFieldAsString(field(7));
    }

    public X937ReturnAddendumARecord depositAccountNumberAtBOFDConfidenceIndicator(String value) {
        setField(value, field(7));
        return this;
    }

    public String depositAccountNumberAtBOFD() {
        return getFieldAsString(field(8));
    }

    public X937ReturnAddendumARecord depositAccountNumberAtBOFD(String value) {
        setField(value, field(8));
        return this;
    }

    public String BOFDDepositBranchConfidenceIndicator() {
        return getFieldAsString(field(9));
    }

    public X937ReturnAddendumARecord BOFDDepositBranchConfidenceIndicator(String value) {
        setField(value, field(9));
        return this;
    }

    public String BOFDDepositBranch() {
        return getFieldAsString(field(10));
    }

    public X937ReturnAddendumARecord BOFDDepositBranch(String value) {
        setField(value, field(10));
        return this;
    }

    public String payeeNameConfidenceIndicator() {
        return getFieldAsString(field(11));
    }

    public X937ReturnAddendumARecord payeeNameConfidenceIndicator(String value) {
        setField(value, field(11));
        return this;
    }

    public String payeeName() {
        return getFieldAsString(field(12));
    }

    public X937ReturnAddendumARecord payeeName(String value) {
        setField(value, field(12));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(13));
    }

    public X937ReturnAddendumARecord reserved(String value) {
        setField(value, field(13));
        return this;
    }

}


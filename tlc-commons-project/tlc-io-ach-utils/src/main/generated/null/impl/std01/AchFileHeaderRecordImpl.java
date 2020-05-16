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

package null.impl.std01;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import null.impl.AchFileHeaderRecord;
import null.impl.base.AchFileHeaderRecordBase;

public class AchFileHeaderRecordImpl extends AchFileHeaderRecordBase {

    private static int maxFieldNumber = 13;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("PriorityCode", 2, 1, 2, FieldType.STRING);
        fields[3] = new Field("ImmediateDestination", 3, 3, 10, FieldType.STRING);
        fields[4] = new Field("ImmediateOrigin", 4, 13, 10, FieldType.STRING);
        fields[5] = new Field("FileCreationDate", 5, 23, 6, FieldType.DATE);
        fields[6] = new Field("FileCreationTime", 6, 29, 4, FieldType.TIME);
        fields[7] = new Field("FileIDModifier", 7, 33, 1, FieldType.STRING);
        fields[8] = new Field("RecordSize", 8, 34, 3, FieldType.STRING);
        fields[9] = new Field("BlockingFactor", 9, 37, 2, FieldType.STRING);
        fields[10] = new Field("FormatCode", 10, 39, 1, FieldType.STRING);
        fields[11] = new Field("Destination", 11, 40, 23, FieldType.STRING);
        fields[12] = new Field("CompanyName", 12, 63, 23, FieldType.STRING);
        fields[13] = new Field("ReferenceCode", 13, 86, 8, FieldType.STRING);
    }


    /*
     * AchFileHeaderRecordImpl
     */

    public AchFileHeaderRecordImpl() {
        super();
    }

    public AchFileHeaderRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public AchFileHeaderRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public AchFileHeaderRecordImpl(ByteArray record, int stdLevel) {
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


    public String priorityCode() {
        return getFieldAsString(field(2));
    }

    public AchFileHeaderRecord priorityCode(String value) {
        setField(value, field(2));
        return this;
    }

    public String immediateDestination() {
        return getFieldAsString(field(3));
    }

    public AchFileHeaderRecord immediateDestination(String value) {
        setField(value, field(3));
        return this;
    }

    public String immediateOrigin() {
        return getFieldAsString(field(4));
    }

    public AchFileHeaderRecord immediateOrigin(String value) {
        setField(value, field(4));
        return this;
    }

    public Date fileCreationDate()
        throws InvalidDataException {
        return getFieldAsDate(field(5), x9TimeZone);
    }

    public AchFileHeaderRecord fileCreationDate(Date value) {
        setFieldDate(value, field(5), x9TimeZone);        return this;
    }

    public String fileCreationDateAsString() {
        return getFieldAsString(field(5));
    }

    public AchFileHeaderRecord fileCreationDate(String value) {
        setField(value, field(5));
        return this;
    }

    public Date fileCreationTime()
        throws InvalidDataException {
        return getFieldAsTime(field(6), x9TimeZone);
    }

    public AchFileHeaderRecord fileCreationTime(Date value) {
        setFieldTime(value, field(6), x9TimeZone);        return this;
    }

    public String fileCreationTimeAsString() {
        return getFieldAsString(field(6));
    }

    public AchFileHeaderRecord fileCreationTime(String value) {
        setField(value, field(6));
        return this;
    }

    public String fileIDModifier() {
        return getFieldAsString(field(7));
    }

    public AchFileHeaderRecord fileIDModifier(String value) {
        setField(value, field(7));
        return this;
    }

    public String recordSize() {
        return getFieldAsString(field(8));
    }

    public AchFileHeaderRecord recordSize(String value) {
        setField(value, field(8));
        return this;
    }

    public String blockingFactor() {
        return getFieldAsString(field(9));
    }

    public AchFileHeaderRecord blockingFactor(String value) {
        setField(value, field(9));
        return this;
    }

    public String formatCode() {
        return getFieldAsString(field(10));
    }

    public AchFileHeaderRecord formatCode(String value) {
        setField(value, field(10));
        return this;
    }

    public String destination() {
        return getFieldAsString(field(11));
    }

    public AchFileHeaderRecord destination(String value) {
        setField(value, field(11));
        return this;
    }

    public String companyName() {
        return getFieldAsString(field(12));
    }

    public AchFileHeaderRecord companyName(String value) {
        setField(value, field(12));
        return this;
    }

    public String referenceCode() {
        return getFieldAsString(field(13));
    }

    public AchFileHeaderRecord referenceCode(String value) {
        setField(value, field(13));
        return this;
    }

}


/*
 * ****************************************************************************
 * Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.io.x9.factory;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

public class DefaultX9RecordFactoryStrategy implements X9RecordFactoryStrategy {

    public X9RecordFactory factory(X937FileHeaderRecord fileHeaderRecord) {
        int standardLevel;
        try {
            standardLevel = fileHeaderRecord.standardLevelAsInt();
        } catch (InvalidDataException e) {
            throw new InvalidStandardLevelException(e);
        }
        String encoding = fileHeaderRecord.record().getEncoding();
        switch (standardLevel) {
            case X9Record.STANDARD_LEVEL_X9_100_187_2008:
                return factory(ANS_STANDARD_X9_100_187_2008, encoding);
            case X9Record.STANDARD_LEVEL_X9_37_2001:
                return factory(ANS_STANDARD_X9_37_2001, encoding);
            case X9Record.STANDARD_LEVEL_X9_37_1994:
                return factory(ANS_STANDARD_X9_37_1994, encoding);
            default:
                throw new InvalidStandardLevelException();
        }
    }

    public X9RecordFactory factory(String standardIdentifier) {
        return factory(standardIdentifier, X9Record.ENCODING_EBCDIC);
    }

    public X9RecordFactory factory(String standardIdentifier, String encoding) {
        switch (standardIdentifier) {
            case ANS_STANDARD_X9_100_187_2008:
                return new X937RecordFactoryDSTU(encoding);
            case ANS_STANDARD_X9_37_1994:
                return new X937RecordFactory1994(encoding);
            case ANS_STANDARD_X9_37_2001:
                return new X937RecordFactory2001(encoding);
            default:
                throw new InvalidStandardLevelException();
        }
    }

}

package com.thelastcheck.io.x9.factory;

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultX9RecordFactoryStrategyTest {

    @Test
    public void testFactory() throws Exception {
        X9RecordFactoryStrategy strategy = new DefaultX9RecordFactoryStrategy();
        X9RecordFactory factory = strategy.factory(X9RecordFactoryStrategy.ANS_STANDARD_X9_100_187_2008);
        Record x9Record = factory.newX9Record(X9Record.TYPE_FILE_HEADER);
        X937FileHeaderRecord fileHeaderRecord = (X937FileHeaderRecord) x9Record;
        assertEquals("03", fileHeaderRecord.standardLevel());
        assertEquals(3, fileHeaderRecord.standardLevelAsInt());
    }

}
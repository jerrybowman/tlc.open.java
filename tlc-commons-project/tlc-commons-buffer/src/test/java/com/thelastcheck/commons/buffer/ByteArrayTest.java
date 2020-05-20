package com.thelastcheck.commons.buffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

public class ByteArrayTest {

    ByteArray byteArray = null;

    @Test
    public void testByteArrayInt() {
        byteArray = new ByteArray(200);
        assertEquals(200, byteArray.getLength());
    }

    @Test
    public void testSlice() {
        byteArray = ByteArray.valueOf("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        ByteArray ba = byteArray.slice(10, 10);
        String s = ba.readAsString();
        assertEquals("KLMNOPQRST", s);
        dumpBuffer("sliceBefore", byteArray.getBuffer());
        dumpBuffer("sliceAfter ", ba.getBuffer());
        ByteArrayDumpFormatter.dumpByteArray("sliceBefore", byteArray);
        ByteArrayDumpFormatter.dumpByteArray("sliceAfter", ba);
        byte[] bs = ba.read(0, ba.getLength());
        ByteArrayDumpFormatter.dumpByteArray("sliceBS", bs);
        dumpBuffer("sliceBS    ", ba.getBuffer());
    }
    
    private void dumpBuffer(String title, ByteBuffer bb) {
        System.out.print(title + ":");
        System.out.print("capacity . . " + bb.capacity());
        System.out.print(", limit  . . . " + bb.limit());
        System.out.print(", position . . " + bb.position());
        System.out.println();
    }
    
    @Test
    public void testByteArrayIntString() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        assertEquals(200, byteArray.getLength());
        byteArray.write(" ", 0);
        assertEquals(0x40, (byteArray.getArray().value[0] & 0x00ff));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        assertEquals(200, byteArray.getLength());
        byteArray.write(" ", 0);
        assertEquals(0x20, (byteArray.getArray().value[0] & 0x00ff));
    }

    @Test
    public void testByteArrayByteArray() {
        byte[] bytes = new byte[200];
        byteArray = new ByteArray(bytes);
        assertEquals(200, byteArray.getLength());
    }

    @Test
    public void testByteArrayByteArrayString() {
        byte[] bytes = new byte[200];
        byteArray = new ByteArray(bytes, ByteArray.EBCDIC_CHARSET_NAME);
        assertEquals(200, byteArray.getLength());
        byteArray.write(" ", 0);
        assertEquals(0x40, (byteArray.getArray().value[0] & 0x00ff));

        byteArray = new ByteArray(bytes, ByteArray.ASCII_CHARSET_NAME);
        assertEquals(200, byteArray.getLength());
        byteArray.write(" ", 0);
        assertEquals(0x20, (byteArray.getArray().value[0] & 0x00ff));
    }

    @Test
    public void testByteArrayByteArray1() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        assertEquals(200, byteArray.getLength());
        byteArray.write(" ", 0);

        ByteArray byteArray2 = new ByteArray(byteArray);
        assertEquals(0x40, (byteArray2.getArray().value[0] & 0x00ff));
        assertEquals(byteArray, byteArray2);
        assertSame(byteArray.getArray().value, byteArray2.getArray().value);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write(10, 0);
        byteArray.write((long) 33333, 4); 
        byteArray.write("TESTTESTTEST", 12); 
        ByteArray byteArrayClone = (ByteArray) byteArray.clone();
        assertEquals(byteArray, byteArrayClone);
        assertNotSame(byteArray.getArray().value, byteArrayClone.getArray().value);
        assertEquals(byteArray.getLength(), byteArrayClone.getLength());
        assertEquals(byteArray.getEncoding(), byteArrayClone.getEncoding());
        assertSame(byteArray.getOrder(), byteArrayClone.getOrder());
        String s1 = byteArray.readPns(0, 200);
        String s2 = byteArrayClone.readPns(0, 200);
        assertEquals(s1, s2);
    }

    @Test
    public void testReadAsByteArray() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byte[] value = { 0, 1, 2, 3, 4 };
        byteArray.write(value, 0);
        byteArray.write(value, 20);
        ByteArray bytes1 = byteArray.readAsByteArray(0, 5);
        ByteArray bytes2 = byteArray.readAsByteArray(20, 5);
        assertEquals(bytes1, bytes2);
    }

    @Test
    public void testRead() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byte[] value = { 0, 1, 2, 3, 4 };
        byteArray.write(value, 0);
        byteArray.write(value, 20);
        // byteArray.write((byte) 0, 0);
        byte[] bytes1 = byteArray.read(0, 5);
        byte[] bytes2 = byteArray.read(20, 5);
        for (int i = 0; i < bytes2.length; i++) {
            assertEquals(bytes1[i], bytes2[i]);
        }
    }

    @Test
    public void testReadIntCharArrayIntInt() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 10);
        char[] chars = new char[5];
        byteArray.read(11, chars, 1, 3);
        byte[] bytes = new byte[5];
        for (int i = 0; i < 5;i++) {
            bytes[i] = (byte) chars[i];
        }
        ByteArray testBA = new ByteArray(bytes);
        String value = testBA.readPns(0, 5, false);
        assertEquals("0045535400", value);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntCharArrayIntInt", bytes);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntCharArrayIntIntBA", byteArray);
    }

    @Test
    public void testReadIntByteArrayIntInt() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 10);
        byte[] bytes = new byte[5];
        byteArray.read(11, bytes, 1, 3);
        ByteArray testBA = new ByteArray(bytes);
        String value = testBA.readPns(0, 5, false);
        assertEquals("00C5E2E300", value);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntByteArrayIntInt", bytes);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntByteArrayIntIntBA", byteArray);
    }

    @Test
    public void testReadAsCharArray() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        char[] value = byteArray.readAsCharArray(0, 4);
        assertEquals(4, value.length);
        // char[] test = { 0x00E3, 0x00C5, 0x00E2, 0x00E3 };
        char[] test = "TEST".toCharArray();
        ByteArrayDumpFormatter.dumpByteArray("TEST", byteArray.getArray().value);
        for (int i = 0; i < test.length; i++) {
            assertEquals(test[i], (value[i] & 0x00ff));
        }
    }

    @Test
    public void testReadAsString() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString();
        assertEquals(200, value.length());
        assertNotEquals("TEST", value);
        assertEquals("TEST", value.substring(0, 4));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString();
        assertEquals(200, value.length());
        assertNotEquals("TEST", value);
        assertEquals("TEST", value.substring(0, 4));
    }

    @Test
    public void testReadAsStringInt() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(1);
        assertEquals(199, value.length());
        assertNotEquals("TEST", value);
        assertNotEquals("EST", value);
        assertEquals("EST", value.substring(0, 3));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(1);
        assertEquals(199, value.length());
        assertNotEquals("TEST", value);
        assertNotEquals("EST", value);
        assertEquals("EST", value.substring(0, 3));
    }

    @Test
    public void testReadAsStringIntInt() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(0, 4);
        assertEquals(4, value.length());
        assertEquals("TEST", value);

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(0, 4);
        assertEquals(4, value.length());
        assertEquals("TEST", value);
    }

    @Test
    public void testReadAsStringIntIntBoolean() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(0, 8, true);
        assertEquals(4, value.length());
        assertEquals("TEST", value);

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(0, 8, true);
        assertEquals(4, value.length());
        assertEquals("TEST", value);
        value = byteArray.readAsString(0, 8, false);
        assertEquals(8, value.length());
        assertNotEquals("TEST", value);
    }

    @Test
    public void testTestBit() {
        byte[] ba = new byte[2];
        ba[0] = (byte) 0x41;
        ba[1] = (byte) 0x81;
        byteArray = new ByteArray(ba);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        assertTrue(byteArray.testBit(1, (byte) 0x80));
        assertTrue(byteArray.testBit(1, (byte) 0x81));
        assertTrue(byteArray.testBit(1, (byte) 0x01));
        assertFalse(byteArray.testBit(1, (byte) 0x83));
        System.out.println("TEST: " + byteArray.readPns(0, 2));
    }

    @Test
    public void testSetBit() {
        byte[] ba = new byte[2];
        byteArray = new ByteArray(ba);
        byteArray.setBit(0, (byte) 0x41);
        byteArray.setBit(1, (byte) 0x01);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertTrue(byteArray.testBit(0, (byte) 0x40));
        assertTrue(byteArray.testBit(0, (byte) 0x41));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        assertFalse(byteArray.testBit(1, (byte) 0x80));
        assertFalse(byteArray.testBit(1, (byte) 0x81));
        assertTrue(byteArray.testBit(1, (byte) 0x01));
        assertFalse(byteArray.testBit(1, (byte) 0x83));
        System.out.println("SET : " + byteArray.readPns(0, 2));
    }

    @Test
    public void testClearBit() {
        byte[] ba = new byte[2];
        byteArray = new ByteArray(ba);
        byteArray.setBit(0, (byte) 0x41);
        byteArray.setBit(1, (byte) 0x01);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertTrue(byteArray.testBit(0, (byte) 0x40));
        assertTrue(byteArray.testBit(0, (byte) 0x41));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        byteArray.clearBit(0, (byte) 0x40);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertFalse(byteArray.testBit(0, (byte) 0x40));
        System.out.println("CLR : " + byteArray.readPns(0, 2));
        byteArray.clearBit(0, (byte) 0x80);
        System.out.println("CLR : " + byteArray.readPns(0, 2));
        byteArray.clearBit(0, (byte) 0x01);
        byteArray.setBit(0, (byte) 0x42);
        System.out.println("CLR : " + byteArray.readPns(0, 2));
    }

    @Test
    public void testReadAsDoubleInt() {
        byteArray = new ByteArray(10);
        byteArray.write(1.5, 0);
        double d = byteArray.readAsDouble(0);
        assertEquals(1.5, d, 0.0);
        ByteArrayDumpFormatter.dumpByteArray("readAsDouble", byteArray);
    }

    @Test
    public void testReadSwappedAsDoubleInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write(1.5, 0);
        double d = byteArray.readAsDouble(0);
        assertEquals(1.5, d, 0.0);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsDouble", byteArray);
    }

    @Test
    public void testReadAsIntInt() {
        byteArray = new ByteArray(10);
        byteArray.write(15, 0);
        int i = byteArray.readAsInt(0);
        assertEquals(15, i);
        ByteArrayDumpFormatter.dumpByteArray("readAsInt", byteArray);
    }

    @Test
    public void testReadSwappedAsIntInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write(15, 2);
        int i = byteArray.readAsInt(2);
        assertEquals(15, i);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsInt", byteArray);
    }

    @Test
    public void testReadAsLongInt() {
        byteArray = new ByteArray(10);
        byteArray.write((long) 15, 2);
        long i = byteArray.readAsLong(2);
        assertEquals(15, i);
        ByteArrayDumpFormatter.dumpByteArray("readAsLong", byteArray);
    }

    @Test
    public void testReadSwappedAsLongInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write((long) 15, 2);
        long i = byteArray.readAsLong(2);
        assertEquals(15, i);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsLong", byteArray);
    }

    @Test
    public void testReadPnsIntInt() {
        byteArray = new ByteArray(20);
        byteArray.writeAsPns("1234-5", 0, 4);
        String value = byteArray.readPns(0, 4);
        assertEquals("AA1234D5", value);
        byte[] test = { (byte) 0xAA, (byte) 0x12, (byte) 0x34, (byte) 0xD5 };
        byte[] bytes = byteArray.getArray().value;
        for (int i = 0; i < test.length; i++) {
            assertEquals(test[i], bytes[i]);
        }
    }

    @Test
    public void testReadPnsIntIntBoolean() {
        byteArray = new ByteArray(20);
        byteArray.writeAsPns("1-2345", 4, 5);
        String value = byteArray.readPns(4, 5, false);
        assertEquals("AAAA1D2345", value);
        value = byteArray.readPns(4, 5, true);
        assertEquals("    1-2345", value);
    }

    @Test
    public void testFillByte() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xee);
        ByteArrayDumpFormatter.dumpByteArray("fill", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("EEEEEEEEEEEEEEEEEEEE", value);
    }

    @Test
    public void testFillByteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xee, 1, 8);
        ByteArrayDumpFormatter.dumpByteArray("fill 1 8", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("00EEEEEEEEEEEEEEEE00", value);
    }

    @Test
    public void testWriteCharArrayInt() {
        byteArray = new ByteArray(10);
        char[] charArray = { 'a', 'b', 'c' };
        byteArray.write(charArray, 1);
        ByteArrayDumpFormatter.dumpByteArray("WriteCharArrayInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("00616263000000000000", value);
    }

    @Test
    public void testWriteByteArrayIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xAA);
        ByteArray ba = new ByteArray(8);
        ba.write("TEST", 0);
        byteArray.write(ba, 1, 5);
        ByteArrayDumpFormatter.dumpByteArray("WriteByteArrayIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("AA5445535400AAAAAAAA", value);
    }

    @Test
    public void testWriteByteArrayInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        ByteArray ba = new ByteArray(8);
        ba.write("TEST", 0);
        byteArray.write(ba, 1);
        ByteArrayDumpFormatter.dumpByteArray("fWriteByteArrayInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("BB5445535400000000BB", value);
    }

    @Test
    public void testWriteStringInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 1);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("BB54455354BBBBBBBBBB", value);
    }

    @Test
    public void testWriteStringIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TES", 7, 3);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("BBBBBBBBBBBBBB544553", value);
    }

    @Test
    public void testWriteStringIntIntBoolean() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 5, 5, true);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntIntBooleanTrue", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("BBBBBBBBBB5445535400", value);

        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 5, 5, false);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntIntBooleanFalse", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("BBBBBBBBBB5445535420", value);
    }

    @Test
    public void testWriteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write(123, 0);
        byteArray.write(255, 6);
        ByteArrayDumpFormatter.dumpByteArray("WriteIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("0000007BBBBB000000FF", value);
    }

    @Test
    public void testWriteSwappedIntInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write(123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("7B000000BBBBBBBBBBBB", value);
    }

    @Test
    public void testWriteLongInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((long) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteLongInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("000000000000007BBBBB", value);
    }

    @Test
    public void testWriteSwappedLongInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((long) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedLongInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("7B00000000000000BBBB", value);
    }

    @Test
    public void testWriteFloatInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((float) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteFloatInt", byteArray);
        float f = byteArray.readAsFloat(0);
        assertEquals(123.0, f, 0.0);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("42F60000BBBBBBBBBBBB", value);
    }

    @Test
    public void testWriteSwappedFloatInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((float) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedFloatInt", byteArray);
        float f = byteArray.readAsFloat(0);
        assertEquals(123.0, f, 0.0);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("0000F642BBBBBBBBBBBB", value);
    }

    @Test
    public void testWriteDoubleInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((double) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteDoubleInt", byteArray);
        double d = byteArray.readAsDouble(0);
        assertEquals(123.0, d, 0.0);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("405EC00000000000BBBB", value);
    }

    @Test
    public void testWriteSwappedDoubleInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((double) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedDoubleInt", byteArray);
        double d = byteArray.readAsDouble(0);
        assertEquals(123.0, d, 0.0);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("0000000000C05E40BBBB", value);
    }

    @Test
    public void testWriteAsPs() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPs("1234+", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234+", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("01234CBBBBBBBBBBBBBB", value);

        byteArray.writeAsPs("+1234", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs+1234", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("01234CBBBBBBBBBBBBBB", value);

        byteArray.writeAsPs("1234-", 1, 4);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234-", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("010001234DBBBBBBBBBB", value);

        byteArray.writeAsPs("1234", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("01234F234DBBBBBBBBBB", value);
    }

    @Test
    public void testWriteAsPnsStringIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPns("1234A", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntInt1234A", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("A1234ABBBBBBBBBBBBBB", value);

        byteArray.writeAsPns("  123-42", 0, 5);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntInt123-42", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("AAAA123D42BBBBBBBBBB", value);
    }

    @Test
    public void testWriteAsPnsStringIntIntChar() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPns("1234A", 0, 3, '0');
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntIntChar0", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertEquals("01234ABBBBBBBBBBBBBB", value);

        byteArray.writeAsPns("  123-42", 0, 5, '0');
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntIntChar 0", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertEquals("00AA123D42BBBBBBBBBB", value);
    }

    @Test
    public void testGetSpaceFiller() {
        byte filler = ByteArray.getSpaceFiller(ByteArray.ASCII_CHARSET_NAME);
        assertEquals(0x20, filler);
        filler = ByteArray.getSpaceFiller(ByteArray.EBCDIC_CHARSET_NAME);
        assertEquals(0x40, filler);
    }

    @Test
    public void testStringToByteArray() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        String s = byteArray.readAsString();
        assertEquals("  12--TEST  ", s);
    }

    @Test
    public void testEqualsByteArray() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        assertEquals(byteArray, byteArray);

        ByteArray ba = ByteArray.valueOf("  12--TEST  ");
        assertEquals(byteArray, ba);

        ba = ByteArray.valueOf("  12--TESTNON");
        assertNotEquals(byteArray, ba);

        ba = ByteArray.valueOf("  12--TEST     ");
        assertNotEquals(byteArray, ba);
    }

    @Test
    public void testEqualTo() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        ByteArray ba = ByteArray.valueOf("  12--TEST  ");
        assertTrue(ByteArray.equalTo(byteArray, ba));
    }

    @Test
    public void testEqualSameByte() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        assertTrue(byteArray.equalSame((byte) 0xBB));
    }

    @Test
    public void testEqualSameByteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((byte) 0xAA, 3);
        byteArray.write((byte) 0xAA, 4);
        byteArray.write((byte) 0xAA, 5);
        assertTrue(byteArray.equalSame((byte) 0xBB, 0, 3));
        assertTrue(byteArray.equalSame((byte) 0xAA, 3, 3));
        assertTrue(byteArray.equalSame((byte) 0xBB, 6, 4));
    }

    @Test
    public void testGetLength() {
        byteArray = new ByteArray(10);
        assertEquals(10, byteArray.getLength());
    }

    @Test
    public void testGetByteArray() {
        byte[] ba = new byte[20];
        byteArray = new ByteArray(ba);
        doGetByteArray(byteArray);
    }

    @Test
    public void testGetByteArrayDirect() {
        ByteBuffer bb = ByteBuffer.allocateDirect(20);
        byteArray = new ByteArray(bb);
        doGetByteArray(byteArray);
    }

    private void doGetByteArray(ByteArray byteArray) {
        byteArray.write("TESTTESTTEST", 0);
        byte[] ba = byteArray.getBytes();
        assertEquals(20, ba.length);
        ByteArray ba2 = new ByteArray(ba);
        String s = ba2.readAsString(0, 20, true);
        assertEquals("TESTTESTTEST", s);
    }

}

/*
 * Copyright (c) 2009-2024 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thelastcheck.commons.base.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MonitorFileDirectoryTest implements java.util.Observer {
    private static Logger log = LoggerFactory.getLogger(MonitorFileDirectoryTest.class);

    private void setup() throws IOException {
        java.nio.file.Path path1 = java.nio.file.Paths.get("target", "test1.tst1");
        java.io.File file = path1.toFile();
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        java.nio.file.Path path2 = java.nio.file.Paths.get("target", "test1.tst2");
        file = path2.toFile();
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
    }

    @Test
    public void testMonitor() throws Exception {
        setup();
        fileCount = 0;
        com.thelastcheck.commons.base.io.MonitorFileDirectory monitor = new com.thelastcheck.commons.base.io.MonitorFileDirectory("target", "tst1", "tst2");
        monitor.addObserver(this);
        monitor.setStableTime(2);
        monitor.start();
        java.lang.Thread.sleep(java.util.concurrent.TimeUnit.SECONDS.toMillis(5));
        monitor.stop();
        assert fileCount == 2;
    }

    @Override
    public void update(java.util.Observable o, java.lang.Object arg) {
        log.info("Processing file: {}", arg);
        fileCount++;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    private int fileCount;
}

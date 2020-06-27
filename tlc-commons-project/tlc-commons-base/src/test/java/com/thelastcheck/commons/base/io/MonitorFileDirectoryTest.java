/*
 * ****************************************************************************
 *  Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *  <p/>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p/>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p/>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.commons.base.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.commons.base.exception.InvalidDirectoryException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class MonitorFileDirectoryTest  implements Observer {
    private static final Logger log = LoggerFactory.getLogger(MonitorFileDirectoryTest.class);

    private int fileCount;

    private void setup() throws IOException {
        Path path1 = Paths.get("target", "test1.tst1");
        File file = path1.toFile();
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        Path path2 = Paths.get("target", "test1.tst2");
        file = path2.toFile();
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
    }

    @Test
    public void testMonitor() throws InvalidDirectoryException, InterruptedException, IOException {
        setup();
        fileCount = 0;
        MonitorFileDirectory monitor = new MonitorFileDirectory("target", "tst1", "tst2");
        monitor.addObserver(this);
        monitor.setStableTime(2);
        monitor.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        monitor.stop();
        assert fileCount == 2;
    }

    @Test(expected = InvalidDirectoryException.class)
    public void testMonitorBadDirectory() throws InvalidDirectoryException, IOException {
        setup();
        fileCount = 0;
        MonitorFileDirectory monitor = new MonitorFileDirectory("targetbad", "tst1", "tst2");
        monitor.addObserver(this);
        monitor.setStableTime(2);
        monitor.start();
    }

    @Test(expected = InvalidDirectoryException.class)
    public void testMonitorMissingName() throws InvalidDirectoryException, IOException {
        setup();
        fileCount = 0;
        MonitorFileDirectory monitor = new MonitorFileDirectory("", "tst1", "tst2");
        monitor.addObserver(this);
        monitor.setStableTime(2);
        monitor.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        log.info("Processing file: " + arg);
        fileCount++;
    }
}

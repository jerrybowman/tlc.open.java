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

import com.thelastcheck.commons.base.exception.InvalidDirectoryException;
import com.thelastcheck.commons.base.utils.FileUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MonitorFileDirectory extends Observable implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MonitorFileDirectory.class);
    private List<String> suffixes;
    private String directoryName;
    private File directory;
    private Thread thread;
    private long sleepTime = TimeUnit.SECONDS.toMillis(1);
    private Map<String, Long> currentFileMap = new HashMap<String, Long>();
    private long stableTime = TimeUnit.SECONDS.toMillis(15);

    private MonitorFileDirectory() {
        super();
    }

    public MonitorFileDirectory(String directoryName, String... suffixes) {
        this();
        this.directoryName = directoryName.trim();
        this.suffixes = Arrays.asList(suffixes);
    }

    public void start() throws InvalidDirectoryException {
        this.directory = new File(directoryName);
        if (directoryName.isEmpty()) {
            throw new InvalidDirectoryException("Value specified for the directory to be monitored is missing");
        }

        directory = new File(directoryName);
        if (!directory.isDirectory()) {
            throw new InvalidDirectoryException("Value specified for the directory to be monitored is not a valid directory: '" + directory.getAbsolutePath() + "'");
        }

        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
        }

    }

    public void run() {
        log.info("Monitor started for directory: {}", directoryName);
        log.info("Looking for files ending in {}", String.valueOf(suffixes));
        FileFilter filter = pathname -> {
            if (pathname.isFile()) {
                if (suffixes.isEmpty()) {
                    return true;
                }

                String fileName = pathname.getAbsolutePath().toLowerCase();
                for (String suffix : suffixes) {
                    if (fileName.endsWith(suffix)) {
                        return true;
                    }
                }
            }

            return false;
        };
        while (true) {
            checkForNewFiles(filter);
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                break;
            }

        }

    }

    private void checkForNewFiles(FileFilter filter) {
        Map<String, Long> previousFileMap = currentFileMap;
        File[] currentFileList = directory.listFiles(filter);
        if (currentFileList == null) {
            return;
        }
        currentFileMap = Arrays.stream(currentFileList).map(file -> new Object() {
            final String name = file.getName();
            final long lastModified = file.lastModified();
        }).collect(HashMap::new, (map, obj) -> {
            map.put(obj.name, obj.lastModified);
        }, HashMap::putAll);
        final long currentSystemTime = System.currentTimeMillis();
        for (String fileName : currentFileMap.keySet()) {
            if (previousFileMap.containsKey(fileName)) {
                long previousFileModifiedTime = previousFileMap.get(fileName);
                long currentFileModifiedTime = currentFileMap.get(fileName);
                if ((previousFileModifiedTime == currentFileModifiedTime) && ((currentSystemTime - currentFileModifiedTime) > stableTime)) {
                    File fileReady = new File(directory, (String) fileName);
                    log.info(fileName + " : Arrived");
                    log.info(fileName + " : Size . . . . . . . . " + fileReady.length());
                    log.info(fileName + " : Arrival Date/Time  . " + new Date(fileReady.lastModified()));
                    log.info(fileName + " : Arrival Location . . " + directory.getAbsolutePath());
                    File newFile = FileUtilities.renameFile(fileReady, ".processing", true);
                    setChanged();
                    try {
                        notifyObservers(newFile);
                    } catch (Exception e) {
                        log.error(fileName + " : Exception Occurred . " + e.getLocalizedMessage(), e);
                    }

                }

            }

        }
    }

    public void setSleepTime(int seconds) {
        this.sleepTime = TimeUnit.SECONDS.toMillis(seconds);
    }

    public void setStableTime(int seconds) {
        this.stableTime = TimeUnit.SECONDS.toMillis(seconds);
    }
}

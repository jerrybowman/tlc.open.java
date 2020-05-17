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

package com.thelastcheck.commons.base.collect;

import com.google.common.collect.ForwardingMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Decorator for the Map that throws an IllegalArgumentException if you call get on a key that
 * doesn't exist in the underlying map
 * @author Michael Siler
 */
public class NullCheckingMap<K, V> extends ForwardingMap<K, V> implements Map<K, V> {
    private static final int maxNumberEntriesToReport = 50;
    private final Map<K, V> delegate;

    private NullCheckingMap(Map<K, V> delegate) {
        this.delegate = delegate;
    }

    public static <K, V> NullCheckingMap<K, V> decorate(Map<K, V> map) {
        if (map instanceof NullCheckingMap) {
            return (NullCheckingMap<K, V>) map;
        }
        return new NullCheckingMap<>(map);
    }

    @Override
    public V get(Object key) {
        V v = super.get(key);
        if (v != null) return v;

        Set<Entry<K, V>> entries = delegate.entrySet();

        List<String> collect = entries.stream()
                .limit(maxNumberEntriesToReport)
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.toList());
        String entryString = String.join(",", collect);

        if (entries.size() > maxNumberEntriesToReport) {
            entryString += "...";
        }

        throw new IllegalArgumentException("There is no mapping defined for: " + key + ", only know: " + entryString);
    }

    @Override
    protected Map<K, V> delegate() {
        return delegate;
    }
}


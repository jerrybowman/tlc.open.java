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

package com.thelastcheck.io.base;

import com.google.common.collect.ImmutableMap;
import com.thelastcheck.commons.base.collect.NullCheckingMap;

import java.util.Map;

public enum FieldType {
	STRING("S")
	, BINARY("B")
	, INT("I")
	, LONG("L")
	, NUMBER("N")
	, DATE("D")
	, TIME("T")
	, ROUTING_NUMBER("R")
	, ONUS("U")
	, AMOUNT("A");

	private static final Map<String, FieldType> codeMap;
	static {
		ImmutableMap.Builder<String, FieldType> builder = ImmutableMap.builder();
		for (FieldType type : values()) {
			builder.put(type.code, type);
		}
		codeMap = NullCheckingMap.decorate(builder.build());
	}

	private final String code;

	FieldType(String typeCode) {
		code = typeCode;
	}

	public String getCode() {
		return code;
	}

	public static FieldType forCode(String code) {
		return codeMap.get(code);
	}

}

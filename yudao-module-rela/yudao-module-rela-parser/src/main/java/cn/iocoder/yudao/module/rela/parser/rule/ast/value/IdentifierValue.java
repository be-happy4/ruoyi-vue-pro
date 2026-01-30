/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.iocoder.yudao.module.rela.parser.rule.ast.value;

import cn.iocoder.yudao.module.rela.enums.QuoteCharacter;

import java.util.Optional;

/**
 * Identifier value.
 */
public record IdentifierValue(String value, QuoteCharacter quoteCharacter)
        implements ValueASTNode<String> {

    public IdentifierValue(final String text) {
        var qc = QuoteCharacter.getQuoteCharacter(text);
        this(null == text ? null : qc.unwrap(text), qc);
    }

    /**
     * Get value with quote characters, i.e. `table1` or `field1`
     *
     * @return value with quote characters
     */
    public String getValueWithQuoteCharacters() {
        return Optional.ofNullable(value)
                .map(quoteCharacter::wrap)
                .orElse("");
    }
}

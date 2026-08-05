/*
 * Copyright 2020-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.context.config;

import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MessageConversionException;

/**
 * KOMUNE Modification
 * Signals that a {@link org.springframework.messaging.converter.MessageConverter}
 * has deliberately and definitively rejected a message - as opposed to simply not
 * supporting its type or content - and that the composite converter chain, and the
 * function-registry conversion pipeline built on top of it, must not attempt any
 * further converter nor swallow the failure.
 *
 * <p>Unlike a plain {@link MessageConversionException} (which several converters
 * throw routinely to mean "I can't handle this, try the next one"), this subtype
 * exists specifically so a converter can opt out of that fallback behavior.
 * {@code JsonMessageConverter} (in the {@code f2-spring-boot-starter-function-http}
 * module) throws it for malformed JSON; {@code SmartCompositeMessageConverter} and
 * {@link org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry}
 * rethrow it unwrapped instead of demoting it to a generic {@link IllegalStateException}.
 *
 * <p>Deliberately a {@link MessageConversionException} subtype rather than depending on
 * {@code spring-web}'s {@code ResponseStatusException} - keeps this module transport-agnostic;
 * HTTP status resolution happens at F2's own web-layer exception handlers instead.
 * KOMUNE End Of Modification
 *
 * @author KOMUNE
 * @since 5.0.3
 */
@SuppressWarnings("serial")
public class NonRecoverableConversionException extends MessageConversionException {

    public NonRecoverableConversionException(String description, @Nullable Throwable cause) {
        super(description, cause);
    }

}

/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.xianzhi.common.idempotent.annotations;

import java.lang.annotation.*;


/**
 * Idempotent Annotation
 * This annotation marks a method as idempotent, indicating that it can be safely invoked multiple
 * times with the same input without producing different results or unintended side effects beyond
 * the first invocation. It is commonly used in scenarios such as REST ful APIs, distributed systems,
 * or retry mechanisms to ensure that duplicate requests do not alter the system state unexpectedly.
 * The annotation serves as a marker, and its enforcement typically relies on external logic, such
 * as an interceptor, framework, or custom implementation, to detect and handle repeated invocations.
 *
 * @author Max
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {
}
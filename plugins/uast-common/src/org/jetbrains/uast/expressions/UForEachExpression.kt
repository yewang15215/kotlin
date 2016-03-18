/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.uast

interface UForEachExpression : ULoopExpression {
    val variableName: String?
    val iteratedValue: UExpression

    override fun traverse(handler: UastHandler) {
        iteratedValue.handleTraverse(handler)
        body.handleTraverse(handler)
    }

    override fun renderString() = buildString {
        append("for (")
        append(variableName ?: "<error name>")
        append(" : ")
        append(iteratedValue.renderString())
        append(") ")
        append(body.renderString())
    }

    override fun logString() = "UForEachExpression ($variableName)\n" +
            iteratedValue.logString().withMargin + "\n" +
            body.logString().withMargin
}
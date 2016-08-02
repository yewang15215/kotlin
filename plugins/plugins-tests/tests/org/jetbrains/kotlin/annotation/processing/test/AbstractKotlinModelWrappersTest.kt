/*
 * Copyright 2010-2016 JetBrains s.r.o.
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

package org.jetbrains.kotlin.annotation.processing.test

import com.intellij.psi.PsiClass
import org.jetbrains.kotlin.java.model.elements.DefaultJeElementRenderer
import org.jetbrains.kotlin.java.model.toJeElement
import org.jetbrains.kotlin.test.KotlinTestUtils
import java.io.File

private val RENDERER = DefaultJeElementRenderer()

private fun runTest(testDataFile: File, lightClass: PsiClass) {
    val modelFile = File(testDataFile.parent, testDataFile.nameWithoutExtension + ".txt")
    val jeElement = lightClass.toJeElement() ?: error("JeElement is null")
    KotlinTestUtils.assertEqualsToFile(modelFile, RENDERER.render(jeElement))
}

abstract class AbstractKotlinModelWrappersTest : AbstractKotlinAnnotationProcessingTest() {
    override fun doTest(testDataFile: File, lightClass: PsiClass) = runTest(testDataFile, lightClass)
}

abstract class AbstractJavaModelWrappersTest : AbstractJavaAnnotationProcessingTest() {
    override fun doTest(testDataFile: File, lightClass: PsiClass) = runTest(testDataFile, lightClass)
    
    
}
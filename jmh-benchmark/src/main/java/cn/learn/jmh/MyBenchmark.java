/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package cn.learn.jmh;

import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class MyBenchmark {

    String string1;
    String string2;
    String string3;
    String string4;

    @Setup(Level.Iteration)
    public void prepare() {
        string1 = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        string2 = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        string3 = String.valueOf(RandomUtils.nextInt(100000, 1000000));
        string4 = String.valueOf(RandomUtils.nextInt(100000, 1000000));
    }

    @Fork(1)
    @Benchmark
    public void stringBuild() {
        StringBuilder build = new StringBuilder();
        build.append("string1").append("=").append(string1).append(";").append("string2").append("=").append(string2)
                .append(";").append("string3").append("=").append(string3).append(";").append("string4").append("=")
                .append(string4).append(";");
        String string = build.toString();

    }

    @Fork(1)
    @Benchmark
    public void stringFormat() {
        String s = String.format("string1=%s;string2=%s;string3=%s;string4=%s;", string1, string2, string3, string4);
    }


    @Fork(1)
    @Benchmark
    public void stringPlus() {
        String s = "string1=" + string1 + ";string2=" + string2 + ";string3=" + string3 + ";string4=" + string4 + ";";
    }

}

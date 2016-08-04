/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.mrql;

import java.util.Iterator;
import scala.Tuple2;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFlatMapFunction;


abstract class FmFunction<T,R> implements FlatMapFunction<T,R> {
    abstract Iterator<R> eval ( T t ) throws Exception;

    public Iterable<R> call ( final T t ) throws Exception {
        return new Iterable<R>() {
            public Iterator<R> iterator() {
                try {
                    return eval(t);
                } catch (Exception ex) {
                    throw new Error(ex);
                }
            }
        };
    }
}

abstract class PairFmFunction<T,K,V> implements PairFlatMapFunction<T,K,V> {
    abstract Iterator<Tuple2<K,V>> eval ( T t ) throws Exception;

    public Iterable<Tuple2<K,V>> call ( final T t ) throws Exception {
        return new Iterable<Tuple2<K,V>>() {
            public Iterator<Tuple2<K,V>> iterator() {
                try {
                    return eval(t);
                } catch (Exception ex) {
                    throw new Error(ex);
                }
            }
        };
    }
}

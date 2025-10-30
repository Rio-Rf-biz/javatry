/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic.st6.dbms;

// TODO iwata author追加でお願いします by jflute (2025/10/30)
/**
 * @author jflute
 */
public class St6MySql extends Database{

    // TODO iwata offsetの計算はDBMSに依存しないので、再利用しましょう。 by jflute (2025/10/30)
    // TODO iwata もし仕様変更で... by jflute (2025/10/30)
    // 現状の2stepが3stepに変わったら:
    // 1. offsetの導出
    // 2. query文字列の生成
    // 　↓
    // 1. offsetの導出
    // 2. なんか新しい処理 (1と3とはそこまで連携するわけではなく独立的ロジック)
    // 3. query文字列の生成
    // こういうことがあったとき、現状だと2箇所(以上)修正しないといけないですが、
    // 1箇所で済むようにしてみましょう。
    // (つまり、現状は流れが再利用できていないと言える)
    @Override
    protected String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return "limit " + offset + ", " + pageSize;
    }
}

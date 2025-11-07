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

// done iwata author追加でお願いします by jflute (2025/10/30)
/**
 * @author jflute
 * @author Rio-Rf-biz
 */
public class St6MySql extends DatabaseManagementSystem {

    // done iwata offsetの計算はDBMSに依存しないので、再利用しましょう。 by jflute (2025/10/30)
    // done iwata もし仕様変更で... by jflute (2025/10/30)
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
    //
    // こちらを参考にしてみました https://zenn.dev/komorimisaki422/articles/68ffd0f82031da
    // Template Methodパターン
    // 共通処理のメソッドにfinalをつけるのは骨組みなので上書きを想定していないためか
    // 抽象メソッドにprotectedをつけるのは共通処理のメソッドを構成する一部なので、あくまで共通処理のメソッドだけで使うことを強調するためか
    // 仕様変更で新しい独立ロジックを追加するのであれば抽象メソッドを増やして共通処理のメソッドに追加する
    //
    // #1on1: Template Methodパターンがもたらしているもの
    // o 「個別処理 + 処理の流れ」の再利用 ☆☆☆ ここの理解こそ大事
    // o 流れは一緒で、一部処理だけが振る舞いが違うパターン、こういうときに再利用ができる
    //
    // o 一方で、Template Methodパターンだけがこれをできるってわけじゃない
    // o 他のやり方でも同じように再利用ができることもある
    // o 逆にいうと、Template Methodパターンの仕組みをわかっていれば、他のやり方も理解しやすい
    //
    // #1on1: GoFデザインパターンとの接し方
    // o コピペサンプルコード的な感覚だと、もう現場とサンプルコードが合わないので役に立たない
    // o でも、やりたいことは今の時代も大して変わらないので、本質を見極めて勉強していれば...
    // o 現場の環境、フレームワークバリバリの環境でも、自分でパターンを応用して解決することができる
    @Override
    protected String buildPagingQueryString(int offset, int pageSize) {
        return "limit " + offset + ", " + pageSize;
    }
}

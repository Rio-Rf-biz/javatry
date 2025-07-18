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
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

// TODO iwata [感謝] javadocのauthorありがとうございます！(^^ by jflute (2025/07/14)
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author Rio-Rf-biz
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai
    }
    //型の異なる変数を+演算子で文字列結合していると考えました。
    // TODO iwata [いいね] yes, String以外のクラスはtoString()が暗黙的に呼ばれます by jflute (2025/07/14)
    // なるほど。理解しました。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
    }
    // sea = land;でその時点でのlandの値(="oneman")が代入されるので、seaの値は"oneman"になると考えました。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
    }
    // sea = land; でその時点でのlandの値(=415)が代入されるので、seaの値は415になると考えました。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 416
    }
    // sea.add(new BigDecimal(1)); の部分でインクリメントされると考えて417にしていました。
    // しかし、sea.add(new BigDecimal(1)); では代入しているわけではないので416になると理解しました。
    // 気になったのでadd()メソッドについて調べたところ、戻り値はBigDecimal型で返すことを確認しました。
    // そもそもなぜlog(sea)で値が返ってくるか調べました。
    // log()ではtoString()が戻り値として呼び出されており、BigDecimalクラスのtoString() メソッドは、そのオブジェクトが表す数値を文字列形式で返すため数値のメンバ変数のみがログに出力される。
    // TODO iwata [いいね] ソースコード読んでる素晴らしいです(^^ by jflute (2025/07/14)
    // TODO iwata [ふぉろー] しっかり add()メソッド調べたの素晴らしいです by jflute (2025/07/14)
    // ぜひ、add()メソッドのJavaDocにも注目してみてください。IntelliJ上でカーソルを当てればツールチップで表示されます。
    // 一言目に Returns って書いてあるので、自分自身を変えるっというよりも「戻す」って主張が強いです。
    // JavaDocの戻り値の説明欄も this + argend とあって、足したものを戻すよって言っています。
    // 他にも、BigDecimal自身のクラスのJavaDocを読むと、一言目に immutable って書いてあります。
    // 自分(のインスタンス)は変わらないってことを示すので、このクラスの特性からaddしても自分は変えずに戻す、
    // ってことも読み取れます。色々な調べ方がありますが、JavaDocは目の前にある手軽な情報ということで。

    // ↑なるほどすぎました。addしても自分は変えずに戻す、の部分。JavaDoc今後は活用します。

    // TODO jflute 1on1のときに、Immutableの話とソースコードリーディングをする予定 (2025/07/14)
    // ↑このtodoは、くぼ用のものなのでそのまま残しておいてください

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => NULL
    }
    // NULLになると予想。初期化されていないので。
    // 正解はnullだった。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => null
    }
    // 初期化されていないのでnullになると予想。
    // 正解は0だった。
    // 調べたところint型の初期値は0であることがわかりました。
    // 参考：https://qiita.com/kumaGoro_95/items/3420cbb36f4e9edfcee1

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
    }
    // 初期値をまとめた表に記載がなかったのでnullになると予想。
    // intは型そのもの、nullは許容されていない。
    // Integer は int 型の値をラップしたオブジェクト。クラスのインスタンス。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1||burn
    }
    // helpInstanceVariableViaMethod()で値が変わることを踏まえてbigband|1||burnと予想
    // 正解はbigband|1|null|magicianだった。
    //
    // *nullについて*
    // nullをString型と+演算子で結合すると、nullは文字列の"null"に変換されて結合されます。
    // これはJavaの+演算子（文字列結合演算子）の特性によるものです。by gemini
    //
    // *instanceMagiclampについて*
    // Copilotに尋ねたらinstanceMagiclampはメソッドの引数として渡されているローカル変数であるためクラスのメンバ変数には影響しない
    // 納得した。
    // TODO iwata [ふぉろー] AI良い活用の仕方してますね(^^。 by jflute (2025/07/14)
    // そう、helpメソッドのinstanceMagiclampは「引数」で、引数は「メソッドのローカル変数」です。
    // メソッドを呼ばれるときに、呼び出し側で指定された値(インスタンス)を引数で受け取ります。
    // なので、呼び出し側で呼び出す直前まで入れていた変数(instanceMagiclamp)と、
    // 呼ばれた側で受け取るための宣言した引数(ローカル変数)(instanceMagiclamp)は、
    // たまたま名前が同じだけの別の変数なんですね。(たまたま名前が同じ別の箱)
    // (インスタンスだけhelpメソッドに旅立つだけで、変数は旅立たない)

    // TODO jflute 1on1にて、変数とインスタンスの関係性について、そもそもインスタンスとは？ (2025/07/14)

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
    }
    // landはメソッドでインクリメントされているがローカル変数なので変化なし、seaも同様、以上よりharborと予想

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }
    // landStrにはint型の416をString型に変換したものが代入される
    // concat()メソッドはString型のメソッドで、元の文字列に引数で渡した文字列を連結した新しい文字列を返す。

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor
    }
    // helpMethodArgumentMethodcall(sea, land)こちらはローカル変数なのでseaの値は変化しないと予想。
    // 以上よりharborと予想
    // 正解はharbor416だった。
    // 引数でStringBuilder型を渡すと参照渡しになる。
    // そのため、メソッド内でのseaの変更は呼び出し元のseaにも影響を与えると理解した。
    // オブジェクト自体は、メモリ上に存在するデータの塊であり、変数はそのオブジェクトがメモリ上のどこにあるかを示す「アドレス（参照）」を保持しています。
    // ↑らしい。たしかに納得。
    // TODO iwata [ふぉろー] おお、これですこれです。変数はただ(どこかに置いてある)インスタンスを指し示すだけで.. by jflute (2025/07/14)
    // 何かしら処理を振る舞うのはあくまでインスタンス(オブジェクト)なんですね。
    // そのインスタンス(のクラス)が、自分自身を書き換えるのか？書き換えずに新しいインスタンスを戻すのか？
    // という二択になって、StringBuilderは自分自身を書き換えるスタイルのクラスだったということですね。

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
    }
    // メソッド内ではローカル変数のseaに新しいインスタンスへの参照を渡しているだけなので、harborと予想
    // TODO iwata [いいね] yes, 1個目のインスタンスへの参照を破棄して、2個目のインスタンスを代入したということですね by jflute (2025/07/14)
    // (helpメソッドの最後の行)

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    int piari;

    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        // TODO iwata piariはインスタンス変数という要件なので、メソッドの外側に宣言しましょう by jflute (2025/07/14)
        // 通常はクラス宣言直下あたりに宣言するのが慣習ですが、ここはエクササイズのわかりやすさを優先して、
        // このメソッドの前後のどこかあたりでOKです。
        // int piari = 0;
        log(sea, land, piari);
    }
    // public void test_variable_writing()これをクラスだと勘違いしておかしなことになってました。
    // メソッド配下がローカル変数、インスタンス配下がインスタンス変数

    // 以下Copilotへの質問
    /**
     *理由
     * Javaではローカル変数とインスタンス変数で初期化の扱いが異なります：
     * インスタンス変数（フィールド）
     * 自動的にデフォルト値で初期化される
     * String → null
     * int → 0
     * boolean → false
     *
     * ローカル変数
     * 自動初期化されない
     * 使用前に明示的に初期化する必要がある
     * 初期化せずに使用するとコンパイルエラーになる
     *
     * 設計上の理由
     * 安全性: 未初期化の変数使用によるバグを防ぐ
     * 明示性: 開発者が意図的に初期値を設定することを強制
     * パフォーマンス: ローカル変数は頻繁に作成/破棄されるため、自動初期化のオーバーヘッドを避ける
     */

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * メソッド終了時の変数 sea の中身は？
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    private boolean bool;
    private char c;

    public void test_variable_yourExercise() {
        boolean bool = true;
        char c = 'A';
        String sea = "mystic";
        sea = sea + bool + c;
        log(sea); // your answer? => mystictrueA
    }

    // boolとcharの初期値の確認と見せかけて、インスタンス変数名とローカル変数名が衝突した場合の問題にしました。

    /**
     * 以下、Copilotへの質問
     * このファイルのテストコードってどの部分で正解を判断してる？
     *
     * 正解判断の仕組み
     * 予想を事前に記述: log(sea); // your answer? => mystic のように、コメントで予想答えを書く
     * テスト実行: log()メソッドを呼び出してコンソールに実際の値を出力
     * 手動比較: 実行結果と予想が一致しているかを目視で確認
     *
     * 自動判定はない理由
     * このテストは学習用エクササイズのため：
     * assertEqualsなどのアサーション文は使用していない
     * 自動的にパス/フェイルを判定しない
     * 学習者が自分で考えて答えを予想し、実行結果と比較することが目的
     */
}

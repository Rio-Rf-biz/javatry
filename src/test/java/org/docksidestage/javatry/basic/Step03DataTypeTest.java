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
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

// TODO iwata javadocのauthor, お願いしますm(_ _)m by jflute (2025/08/14)
/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);// 2001-09-05になる
        land = piari.getYear();// 2001になる。戻り値はyearでありlandはInteger。
        bonvo = bonvo.plusMonths(1);//2001-10-04 12:34:56になる.
        land = bonvo.getMonthValue();// 10になる。戻り値はmonthでありlandはInteger。
        land--;// 9になる。
        if (dstore) {// trueなので実行される。
            BigDecimal addedDecimal = amba.add(new BigDecimal(land)); // 9.4 + 9 = 18.4になる。
            sea = String.valueOf(addedDecimal);// "18.4"になる。
        }
        log(sea); // your answer? => 18.4と予想
    }
    //下調べ
    // LocalDate.plusDays()
    // →戻り値はLocalDate型、引数が0なら呼び出し元のLocalDateをそのまま返す。
    // 0 以外の場合は、現在の日付を「エポック日」（1970-01-01 からの通算日数）に変換し、daysToAdd を加算します。
    // 加算後のエポック日から新しい LocalDate オブジェクトを生成して返します。
    // *エポック日とは特定の日時（基準点）から現在までの経過時間を秒数またはミリ秒数で表した値.日付はそのままだとコンピュータ的に扱いづらいので変換している。
    // TODO iwata [いいね] エポック日のことまで調べてるの素晴らしいです by jflute (2025/08/14)
    // そう、結局「日付」って概念は、地球上での人間の決め事ですから、コンピューター上では何かしらの基準が必要になるわけですね。
    // ぼくらが目にする 2025-08-14 という文字列は、人間用の表現ということなわけです。

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;//fは数値リテラルがfloat型であることを示すサフィックス（接尾辞）です
        double amba = 2.3d;//dは数値リテラルがdouble型であることを示すサフィックス（接尾辞）です
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) { //trueなので実行される。
            bonvo = sea; // 127になる。bonvoはlong型なので、byteからlongへの自動変換が行われる。
            land = (short) bonvo; // 127になる。bonvoはlong型なので、longからshortへの明示的な変換が必要。
            bonvo = piari; // 1になる。long型はint型よりも広いので自動変換される。
            sea = (byte) land;// 127になる。landはshort型なので、shortからbyteへの明示的な変換が必要。
            if (amba == 2.3D) {// trueなので実行される。
                sea = (byte) amba; // 2になる。Double型からByte型への変換は切り捨てではなく、整数部分のみを取得します。
            }
        }
        if ((int) dstore > piari) { //falseなので実行されない。floatからintへの変換は明示的キャストが必要で、小数点以下が切り捨てされます。
            sea = 0;
        }
        log(sea); // your answer? => 2
    }
    // destore(float)とpiari(int)を比較する時はpiariがfloatに変換される。
    // Javaではより広い範囲の方に自動変換される
    /** Dが大文字な理由？
     * Dが大文字なのは、2.3DがJavaの数値リテラルでdouble型を明示するサフィックス（接尾辞）だからです。
     * d（小文字）でも同じ意味ですが、D（大文字）も使えます。
     * どちらもdouble型を表します。
     */
    // TODO iwata [いいね] Dが大文字な理由で言うと、Dもdも両方入れてエクササイズとして紛らわしくするためです笑 by jflute (2025/08/14)

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hanger
    }
    // hangarと予想
    // stageNameはコンストラクタで設定されている。
    // private finalフィールドなので、インスタンス化時に設定された値を変更することはできない。
    //
    // privateはアクセス修飾子の一つです。
    // 意味: そのフィールドが定義されたクラスの内部からのみアクセス可能であることを意味します。
    //
    // finalはキーワードで、変数やフィールドが一度初期化されたら再代入できないことを示します。
    // 不変(immutable)
    //
    // getterメソッドはpublicであるため、外部からでもstageNameの値を取得することができる。
    // TODO iwata [いいね] immutableのクラスの自作方法という感じですね by jflute (2025/08/14)
    // コンストラクターで受け取ったインスタンスは、finalのインスタンス変数で保持しておくと安心です(^^

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}

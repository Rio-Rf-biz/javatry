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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author Rio-Rf-biz
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
    }
    // "over"がreturnされるので、seaの中身は"over"になる。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic"); // ticがmysになるので"mysmys"
        consumeSomething(supplySomething()); // log出力 (引数でoverが入ってmysticになる)
        runnableSomething(); // log出力 outofshadow
        log(sea); // your answer? => mysmys
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable); //再代入してないのでseaは904のまま, mutaableにはmysticがセットされる
        if (!land) { // true
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 910
    }
    // 910と予想したらあってた
    // seaは値渡しでありhelloMutableメソッド内での変更は反映されない
    // landも値渡しでありhelloMutableメソッド内での変更は反映されない
    // mutableは参照渡しでstageNameにfinalが付いておらずmutableなので、helloMutableメソッド内での変更が反映される
    //
    // プリミティブ型（int, boolean等）：値そのものをコピー
    // オブジェクト：参照（メモリアドレス）の値をコピー
    // done iwata [いいね] step1の復習ですね。簡潔にまとまっています。 by jflute (2025/08/14)

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount; //0で初期化されてるはず
        offAnnualPassport(hasAnnualPassport); //bppleanは値渡しなのでhasAPはtrueのまま
        for (int i = 0; i < 100; i++) {
            goToPark();
        }
        ++sea;
        sea = inParkCount; // inPCの初期値は0
        log(sea); // your answer? => 0
    }
    // 100だった。
    // 間違えた要因はメソッド内でインスタンス変数を変更してもその変更がインスタンス変数に反映されないと勘違いしていたこと
    // ローカル変数とインスタンス変数を混同していた。メソッドの引数に指定していないので今回はインスタンス変数。
    //
    // メソッド内でインスタンス変数を変更するとその変更はメソッド外のインスタンス変数にも反映される
    // done iwata [いいね] そうですね、インスタンス変数は「そのインスタンス内では共有物」みたいな感じで... by jflute (2025/08/14)
    // メソッド(インスタンスメソッド)間で共有されていますので、別の人(メソッド)がいじったら変わっちゃうと。

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) { //hasAPの初期値はfalse
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }
    // done jflute 1on1にて、privateメソッドの定義順序のお話 (2025/08/14)
    // #1on1: 基本はpublicの直下で処理順序で並べていく
    // 応用として、再利用するprivateメソッドなら、コメントなどで区切って独立させる
    // これも応用、独立性の高いロジックのprivateメソッドも、コメントなどで区切って独立させる

    private boolean availableLogging = true;

    // done iwata [いいね] メソッドの定義順序が呼び出し順序と一緒でとてもわかりやすいです by jflute (2025/08/14)
    // write methods here
    private String replaceAwithB(String str) {
        return str.replace("A", "B");
    }
    private String replaceCwithB(String str) {
        return str.replace("C", "B");
    }
    // done iwata [いいね] 第二引数の引数名quotationってのがとてもわかりやすくて良いですね by jflute (2025/08/14)
    private String quote(String str, String quotation) {
        return quotation + str + quotation;
    }
    private boolean isAvailableLogging() {
        return availableLogging;
    }
    private void showSea(String sea) {
        log(sea);
    }
}

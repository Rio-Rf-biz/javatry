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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author Rio-Rf-biz
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }
    // sea > 904を満たさないのでelseに入る。seaは7になる。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }
    // sea > 904を満たさないのでelse ifに入る。seaは7になる。その後はelseなのでlog(sea)に飛ぶと予想。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) { //満たさない
            sea = 2001;
        } else if (land && sea >= 904) { // landはfalseなので満たさない
            sea = 7;
        } else if (sea >= 903 || land) { // sea >= 903は満たす, landはfalseなので満たさない, ||なのでelse ifの中に入る
            sea = 8;
            if (!land) { // !landなのでtrue, ifの中に入る
                land = true;
            } else if (sea <= 903) { // elseなので飛ばす
                sea++;
            }
        } else if (sea == 8) { // elseなので飛ばす
            sea++;
            land = false;
        } else { // elseなので飛ばす
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { // このときsea = 8, land = trueなのでifの中に入る
            sea--; // seaは8なので1減らして7になる
        }
        if (land) { // landはtrueなのでifの中に入る
            sea = 10;
        }
        log(sea); // your answer? => 10
    }
    // コメントアウトにメモしながら読んだ。seaは10と予想。
    // done iwata コメントアウトじゃなくてコメントですね^^ by jflute (2025/08/04)
    // コメントアウトは、実際のソースコードをコメントにして実行除外するということで。
    // #1on1: 歳を重ねると、細かいところ言われなくなるので、自分で律しないといけない話 (2025/08/04)
    
    // done jflute iwata 1on1にて、ソースコードリーディングの話をする予定 (2025/08/04)
    // #1on1 漠然読みで構造だけ把握して、気になるところ焦点を当ててフォーカス読み、ときに逆読み (2025/08/04)
    // #1on1 BigDecimalのadd()のときも同じ
    // #1on1 My Favorite Book: 仮説思考
    // https://jflute.hatenadiary.jp/entry/20150111/kasetsu
    // #1on1 package構造を利用しようとする姿勢は素晴らしい、ぼくもやる (2025/08/04)

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // 4つの文字列をリストに入れる
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) { // i < 4なので4回ループ
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage; // 1番目の要素は"dockside"
            }
        }
        log(sea); // your answer? => "dockside"
    }
    // seaはdocksideになると予想。
    // #1on1 1番目の表現のジレンマについて e.g. (0始まりとしての)1番目の... (2025/08/04)

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // 4つの文字列をリストに入れる
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }
    // この記法がわからない。仮に全ての要素について実行すると予想してseaは最後の要素の"magiclamp"になると予想。
    // あってた
    /** <以下Copilotの解説>
     * この記法は拡張for文（Enhanced for loop）またはfor-each文と呼ばれる構文です。
     * for (データ型 変数名 : コレクション) {
     *     // 処理
     * }
     *
     * 1. stageListの各要素を順番に取得
     * 2. 各要素をstage変数に格納
     * 3. ループ本体を実行
     * 4. 次の要素があれば繰り返し
     *
     * この記法はJava 5から導入され、配列やCollection（List、Setなど）で使用できます。
     */
    // #1on1 文法用語と現場用語のジレンマ話。
    // 個人的には、拡張for文で呼ぶ人ほとんどいない、普通のfor文と言うと拡張for文。
    // そして、int iのfor文は「いんとあいfor文」。
    // (インスタンスメソッドの話: カーナビゲーションシステムに近い)
    // (クラスメソッドの話: 紛らわしいのでstaticメソッドって言う)

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // 4つの文字列をリストに入れる
        String sea = null;
        for (String stage : stageList) {
            String prefix = "br";
            if (stage.startsWith(prefix)) { // brで始まるのは0番目の要素だけ
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) { // gaを含むのは2番目の要素だけ
                break;
            }
        }
        log(sea); // your answer? => hangar
    }
    // continueの意味: 現在のループだけをスキップし、次のループへ進む
    // hangarでbreakに入るのでseaはhangarになると予想。
    // あってた
    // TODO startWithとcontainsの実装を調べる
    // startWithは空文字列の場合もtrueを返す
    // containsは空文字列の場合もtrueを返す
    // done iwata [いいね] 空文字が入ったときの挙動はややこしいですよね by jflute (2025/08/04)
    // done jflute 1on1にて、contains()のソースコードリーディング少しやってみよう (2025/08/04)
    // #1on1 startsWith()のコード読んで、空文字がtrueになるときの挙動を確認した
    /**
     * Stringクラスのstatic int indexOf(char[] source, int sourceOffset, int sourceCount,
     *             char[] target, int targetOffset, int targetCount,
     *             int fromIndex)で含むかどうかを調べている。
     * source配列内でtarget配列（検索したい文字列）の最初の文字を探します。
     * 最初の文字が見つかったら、残りの文字が一致するかを確認します。
     * 全て一致した場合、部分文字列の開始インデックスを返します。
     * 一致しなければ次の位置で再度検索します。
     * 最後まで一致しなければ-1を返します（この部分は選択範囲外）。
     */

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) { // docksideが入った状態でメソッドが終了すると予想
                return;
            }
            if (stage.contains("i")) { //iを含むのは"dockside"(1番目)と"magiclamp"(3番目)の2つ
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => 
    }
    // <StringBuilder()の解説>
    // super(16); によって、親クラス（AbstractStringBuilder）のコンストラクタを呼び出し、初期容量16の文字バッファを作成します。
    // seaにdocksideが入った状態でメソッドが終了すると予想。log()は実行されないと予想。
    // 答えはdockside。log()は実行される。
    // done iwata [いいね] Good, StringBuilderは16サイズ確保して、足りなくなったら拡張するという感じですね by jflute (2025/08/04)
    // done jflute 1on1にて、StringBuilderもソースコードリーディング (2025/08/04)
    // #1on1 ensureCapacityInternal()のコード読んでみた (2025/08/04)
    /**
     * forEachのラムダ式内でreturnを使うと、そのラムダの現在のイテレーション（1回分の処理）だけを終了します。
     * forEach全体のループやメソッド自体の終了にはなりません。
     */
    // 以上を踏まえるとdockside以降のループでは最初のif文が常に発動するのでseaの値が変わることはなくループを抜けてseaが出力される。
    // done jflute 1on1にて、forEach()の技術的な内容とは？ソースコードリーディング (2025/08/04)
    // #1on1 forEach()メソッドは、Javaの文法ではなく、ただのメソッド。中で文法のfor文のforを回している。
    // returnは、単にメソッドを終了させるいつものreturn;と同じ。


    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        List<String> aList = new ArrayList<>();
        for (String stage : stageList) {// "a" が含まれているものだけのリストを作成
            if (stage.contains("a")) {
                aList.add(stage);
            }
        }
//        for (String a : aList) { // 作成したリストをループで回してログに表示
//            log(a);
//        }
        aList.forEach(a -> log(a)); // このように一行で書けることを学んだ。
    }
    // Stream APIとは
    /**
     * StreamAPIは、Java 8から導入されたコレクション（List、Setなど）を効率的に処理するためのAPIです。
     *
     * 基本的な仕組み
     * 従来のfor文による処理を、メソッドチェーンで表現できます：
     * 中間操作（処理を繋げる）
     * filter(): 条件に合うものだけを抽出
     * map(): 各要素を変換
     * distinct(): 重複を除去
     * sorted(): ソート
     * 終端操作（結果を取得）
     * collect(): リストやSetに変換
     * forEach(): 各要素に処理を実行
     * count(): 要素数を取得
     * findFirst(): 最初の要素を取得
     *
     * // StreamAPIを使った場合
     *     List<String> aList = stageList.stream()
     *         .filter(stage -> stage.contains("a"))
     *         .collect(Collectors.toList());
     *     .stream()って書くやつがstreamAPI
     */
    // done iwata [いいね] step8が楽しみですね^^ by jflute (2025/08/04)

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // should be same as before-fix
    }
    // 以下が置き換え後
    public void test_iffor_refactor_forEach() {
        List<String> stageList = prepareStageList();
        String[] sea = {null}; // 配列を使用してラムダ式内で変更可能にする
        stageList.forEach(stage -> {
            if (stage.startsWith("br")) {
                return; // continueの代わりにreturnを使用
            }
            sea[0] = stage;
            if (stage.contains("ga")) {
                // TODO iwata 例外投げっぱなしだとseaの値のログが出てこないので、catchもしてあげないとですね by jflute (2025/08/04)
                // (一般的に良い実装かどうかは置いておいて、このエクササイズとしてbreakの代わりに例外を使うのであれば)
                throw new RuntimeException("Break from forEach"); // breakの代わりに例外を投げる
            }
        });
        log(sea[0]);
    }
    // done iwata ぜひ、Live Templatesを入れて、_todo の補完を使ってみてください^^ by jflute (2025/08/04)
    // https://dbflute.seasar.org/ja/manual/topic/friends/intellij/index.html#dbflutelivetemplate
    // #1on1 質問するときとかは _trev を。
    
    // ラムダ式内で外部のローカル変数seaを変更しようとしているため、コンパイルエラーが発生します。
    // そのため、配列を使用してseaを変更可能にした。
    //TODO なぜ配列なら可能なのか
    /**
     * Javaのラムダ式（forEachの中など）では、外部のローカル変数は「実質的final」でなければ参照できません。
     * つまり、ラムダ内で値を変更できません。
     *
     * 一方、配列やAtomicReferenceなどのオブジェクトは参照自体はfinalなので、
     * 中身（要素）は変更可能です。
     * このため、sea[0]のように配列の要素を書き換えることは可能です。
     */
    // ↑らしい。確かに配列の先頭アドレスを指すというポインタの値は変わらないか。
    // なぜfinalにしないといけないのかは依然疑問が残る
    
    // #1on1: 「実質的final」でなければ参照できません、の意味について (昔の文法からおさらいして) (2025/08/04)
    
    /**
     * 1. スレッドセーフティの保証
     * ラムダ式は別スレッドで実行される可能性があり、変数の値が予期しないタイミングで変更されると競合状態が発生する可能性があります。
     *補足：スレッドは、プロセス内で実行される、さらに小さな処理の単位。スレッドは「工場（プロセス）の中の作業員」のようなものです。同じ工場の中で、複数の作業員（スレッド）が協力して作業を進めます。
     *
     *  2. 変数のライフサイクルの問題
     * ローカル変数はメソッド終了時にスタックから削除されますが、ラムダ式は後で実行される可能性があります。
     *
     * 3. 変数キャプチャの実装
     * Javaでは変数の値をラムダ式内にコピー（キャプチャ）します。元の変数が変更されると、コピーされた値と元の値が異なってしまい、予期しない動作を引き起こします。
     */

    // #1on1: Lambda式は、文法的には呼び出されたメソッド側で呼び出しタイミングをどうにでもできる
    // (Lambda式もただのクラスのインスタンスなので、自由に使える、別スレッドで実行することもできちゃう)
    //if (land) {
    //    // ←このタイミングでlambda式が実行
    //    log(land);
    //}
    // あくまで、ローカル変数の参照先を別の人が予期せぬタイミングで書き換えることができないようにしている。
    // インスタンスは自由な人なので誰からでもmutableであれば書き換えることができる。
    // だから、String[]のインスタンスの0番目は誰でも書き換えることができる。
    
    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     *
     * memo: breakが2重のfor文だとどこまで抜けるかの問題とかいいかも
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            for (int i = 0; i < stage.length(); i++) {
                if (stage.charAt(i) == 'a') {
                    sea = stage;
                    break;
                }
            }
            sea = stage;
        }
        log(sea); // your answer? => "magiclamp"
    }
    // breakで2つとも抜けると考えた人はbroadwayが出力されると予想するはず
    // done iwata [いいね] いいですねー、けっこう二重ループで break ってありますからね^^ by jflute (2025/08/04)
    // #1on1 ラベルのお話をちょこっと (2025/08/04)

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}

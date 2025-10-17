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

import java.time.LocalTime;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketCustomized;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author Rio-Rf-biz
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() { // done r.iwata このテストの意図がわからなかったので1on1で確認する (2025/10/01)
        // #1on1: 単純に既存コードの間違いを見つけるトレーニング。(オブジェクト指向と関係なく)
        // そして、オブジェクトを使ったやり方との比較をしてもらう。(これはオブジェクト指向と関係ある)

        // TODO iwata あと5個あります。by jflute (2025/10/03)
        // すべて単純ではあるけれども、単純中に種類がある。
        // → あと2個 → あと1個
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) { // quantity = 10なのでこの条件は必ず実行されないので不要。このエラーが正しく機能することを確認したいのであれば修正する必要がある
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) { // handedMoney = 10000, oneDayPrice = 7400なのでこの条件は必ず実行されないので不要。このエラーが正しく機能することを確認したいのであれば修正する必要がある
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        // #1on1: いわたさんが見つけてくれた (2025/10/03)
        --quantity;
        // #1on1: いわたさんが見つけてくれた (2025/10/03)
        salesProceeds = oneDayPrice;
        handedMoney -= oneDayPrice; // 見つけた

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + quantity);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // #1on1: いわたさんが自分で見つけてくれた (2025/10/03)
        // #1on1: 引数入れ替え事件の深堀り (2025/10/03)
        // int, int, int 怖い話。
        //
        // まず作り手として、できるだけこうならないよう努力/工夫する。
        // 業務的にこうならないようにしたり、入れ物クラス作ったり、Value的オブジェクト作ったり、引数順序変えたり...
        // JavaDocで引数にもっとフォーカスを当てやすく演出したり...
        //
        // そして呼び出し側として、できるだけ間違えないような努力/工夫する。
        // o 指差し確認。その5秒やる習慣があるかどうか？
        // o 間違えそうなポイントを経験上知っているか？意識しているか？ (嗅覚)
        // o そのポイントが目の前の来たら、集中力を高める (アクセル踏む) // 精神状態のコントロール
        //
        // レビューのポイントにもつながる by いわたさん
        // #1on1: それだけレビューワーって大変、くまなく見るの大変。(レビュー時間が見積もりで確保されているか話)
        // だからこそ、レビューイーがレビューしやすいコードを書くこと大事。
        //
        // TODO done iwata [読み物課題] プルリクであれこれ説明するならコードにコメントに書こう by jflute (2025/10/03)
        // https://jflute.hatenadiary.jp/entry/20181016/pulcomment
        // 読みました！
        // ソースコードのコメントは未来の人と通じ合える優秀なコミュニケーションツール
        // 自分が実装するときにコメントがあるとありがたいのでPRでやりとりが生じたらコメントで書いた方がいいかな？と疑問を持つようにします
        //
        // TODO done iwata [読み物課題] レビューしやすいコード (Reviewable Code) by jflute (2025/10/03)
        // https://jflute.hatenadiary.jp/entry/20160912/reviewable
        // 読みました！
        // そもそもレビューしやすいコードを書くべきということに納得です
        // メモ: 統一的なコードと理路整然としたコード
        // メモ: 考え続けることが答え、世の中そんな簡単な世界じゃない
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn, handedMoney); // handedMoneyもログで確認したいので追加
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn, int handedMoney) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            // #1on1: いわたさんが自分で見つけてくれた (2025/10/03)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
            showHandedMoney(handedMoney);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    private void showHandedMoney(int handedMoney) {
        log("Handed Money: handedMoney={}", handedMoney);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        TicketBooth.TicketBuyResult ticketBuyResult = booth.buyOneDayPassport(10000);

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        TicketCustomized ticket = ticketBuyResult.getTicket();
        ticket.doInPark(LocalTime.now());

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, TicketCustomized ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(TicketCustomized ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getTicketPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // - データと処理をまとめて保持できる入れ物
    //   - 処理の単位でまとめることができるのでどこまでが一連の処理かという観点でみやすい、管理しやすい
    // - privateにしておけば外部から直接アクセスできない
    //   - 意図しない変更を防げる
    //
    // _/_/_/_/_/_/_/_/_/_/

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => null
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
    }
    // log(sea)がwanだった
    // log(land)は正解
    //
    // log(land)は初期HPが10かつそれぞれのメソッドの中身を確認して3回デクリメントされていることを確認した
    // getBarkWord()に対してエディタのジャンプ機能を使うと抽象メソッドの方にジャンプしたので間違えた、抽象メソッドである時点で実装されていることを疑うべきだった
    // DogクラスにgetBarkWord()の実装があることを確認した

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog(); // DogはgetBarkWord()を実装している
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint(); // bark()内で3回downHitPoint()が呼ばれている
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal(); // Dogインスタンスがanimalに入る
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog); // dogインスタンスがanimalに入る
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat(); // catの鳴き声は"nya-", downHitPoint()もオーバーライドされている(デクリメントして偶数だったらさらにデクリメント)
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie(); // 初期HPは-1, 鳴き声は"uooo", downHitPoint()はオーバーライドされて何もしない
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // コードのほとんどを変えずに同じことができる
        // 例えば5thと6thの違いはnew XX()の部分だけだがlogにそれぞれの特徴を反映した結果を出力できる
        // = 拡張性が高い
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie(); // interfaceにZombieインスタンスを入れている
        String sea = loudable.soundLoudly(); // AnimalクラスはLoudableを実装している, return bark().getBarkWord();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
    }

    // interface型の変数loudableにZombieインスタンスを入れている
    // LoudableインターフェースのsoundLoudly()メソッドはAnimalクラスで実装されている
    // ZombieインスタンスはAnimalクラスを実装しているので、AnimalクラスのsoundLoudly()メソッドが呼ばれる
    // loudable変数からは、Loudableインターフェースで定義されたメソッド（soundLoudly()）のみ呼び出せる


    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        //
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        //
        // _/_/_/_/_/_/_/_/_/_/
    }
}

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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for animal(動物).
 * @author jflute
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======

    // done iwata 修行++: protectedに戻したいですねぇ... (packageは今のまま変えずに) by jflute (2025/11/21)
    // カプセル化が壊れてしまっているので、それはやりたくない。
    // 仲介するクラスを作成して、そこから呼び出すように変更した
    // 10分考えて思いつかなかったのでGeminiに聞きました
    // 解決策A（匿名内部クラス）
    //
    //推奨
    //
    //カプセル化が最も堅牢です（Animal 自身が許可したタイミングでしか実行されない）。
    //
    //現在主流の設計パターン（Strategyパターン/Commandパターン）に沿っています。
    //
    //解決策B（仲介者クラス）
    //
    //「メソッドの中にクラス定義（new Interface() {...}）を書くのが気持ち悪い・読みづらい」と感じる場合に有効です。
    //
    //構造が単純で、昔ながらのJavaらしい解決策です。
    //
    // 解決策Aは理解が難しかったため、解決策Bを採用しました。

    // done iwata 修行++: getBarkWord(), Bridge が public なのでどうにかしたい by jflute (2025/12/05)
    // hint1: downHitPoint() よりは簡単。
    // hint2: わかっちゃえば、「なーんだ」とか「あああああ、なんで思いつかなかった＞＜」って感じ。
    // hint3: (↑つまり、オブジェクト指向とかそういういう大げさなものじゃない)
    // hint4: step1,2, せめてstep4くらいの話
    // (ネタバレしました) (2025/12/19)
    // e.g.
    //  String barkWord = getBarkWord();
    //  return BarkingProcess.bark(bridge, barkWord);
    // #1on1: 人間、複雑なものを目の前にすると、高度な技じゃないと解決できないと思いこんじゃう (2025/12/19)
    // 高度な技ばかりに目が行ってしまって、基礎を忘れてしまう。今回の場合は、引数戻り値デザイン。
    // プログラミングスキルというのは、文法の知識だけじゃなく、文法を使いこなす(デザインできる)ことも含まれる。
    // プログラム知識とプログラミングって違う。
    // TODO iwata [読み物課題] 「ミング」の時間ですよ by jflute (2025/12/19)
    // https://jflute.hatenadiary.jp/entry/20121016/ming
    public BarkedSound bark() {
        AnimalBarkingBridge bridge = new AnimalBarkingBridge(this);
        return BarkingProcess.bark(bridge);
    }

    // 1on1: ZombieBarkingProcessを作る場合のイメージ (2025/12/19)
    // なので、シンプルなロジックのクラスでも、staticではなく普通の具象クラスで実装することが現場では多い。
    // (staticって、付けた瞬間にオブジェクト指向を捨てるようなもの、本当にそれでOKなものだけで使う)
    //protected BarkingProcess createBarkingProcess() {
    //    return new BarkingProcess();
    //}
    // TODO iwata static外したくなったと思うので外しておきましょう by jflute (2025/12/19)

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // done iwata 修行++: Zombie, これだと、bark()したときのbreatheIn処理と繋がってない by jflute (2025/12/05)
    // 元々はオーバーライドで、bark()が呼ばれた時のbreatheIn処理で、このオーバーライドメソッドが呼ばれて、
    // Zombieだけ日記を付けるという追加処理が入るようになっていた。
    // 今、new Zombie().bark() しても、BarkingProcessのbreatheIn()で、このcountBreatheIn()は呼ばれない。
    // hint1: オブジェクト指向の範疇内で実現可能
    // #1on1: たくさん持って帰ってきてるわけではなく、hookポイントを持って帰ってきただけなのでGood (2025/12/19)
    protected abstract String getBarkWord();

    // TODO iwata doBark()の処理は、barkingのロジックなので、BarkingProcessに閉じ込めたい by jflute (2025/12/19)
    // 抽象クラスの肥大化を抑えるという目的からしても、もしdoBark()がもっと大きなロジックだったら...
    protected BarkedSound doBark() {
        downHitPoint();
        return new BarkedSound(getBarkWord());
    }

    // ZombieがOverrideできるようにdoBreatheInを追加した
    // TODO iwata もしJavaDoc書くとしたら... by jflute (2025/12/19)
    // A. "吠えるときの息継ぎのヒットポイント消化を行う" downHitPointForBreatheIn()
    //  → Zombieのcountでは使いづらい (悪くはないけどちょっと間接的で変更時のすれ違いが怖い)
    // B. "吠えるときの息継ぎのAnimal側の後処理" :: hookAfterBreatheInOnAnimal()
    //  → こっちならZombieのcountもオーバーライドで追加しても良さそう
    // どっちせよ、今の doBreatheIn() だと本体ロジックかと思ってしまいそう。
    // #1on1: JavaDocとか自然言語で一度表現することで、物理のメソッド名が適切かどうか？ (2025/12/19)
    // そのメソッドの概念が適切かどうか？がわかることもあるので、オススメ。
    // #1on1: AIのお話、AIとコメントの話、特に「背景コメント」、「概念コメント」 (2025/12/19)
    protected void doBreatheIn() {
        downHitPoint();
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // done iwata 修行#: downHitPoint() が public なのでどうにかしたい、あと by jflute (2025/12/05)
    // hint1: Gemini の解決策Bは一瞬「おっ」って感じでおおぉ。
    // hint2: step7,8とか先に進んでからアプローチしてもOK

    // downHitPointをprotectedに変更してbridege経由で呼び出すようにした
    // Bridgeにprotectedなコンストラクタを用意してnewできる範囲を制限した by iwata
    // #1on1: ↑素敵、これはこれで一つのやり方でとっても良いです (2025/12/19)
    // 一方で、他の人がよくやってるやり方はあるけど、似たことやってると言えるかも。
    // そう考えると、自力でこれを今ある知識の中から思いついたというのは本当に素晴らしいこと。
    //  e.g. return BarkingProcess.bark(() -> downHitPoint());
    // step8の先取りをしてコールバックとは？話。なので似たことやってる。
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}

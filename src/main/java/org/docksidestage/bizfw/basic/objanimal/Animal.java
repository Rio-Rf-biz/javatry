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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for animal(動物).
 * @author jflute
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

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
    public BarkedSound bark() {
        return BarkingProcess.bark(this);
    }

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
    
    // TODO iwata 修行++: getBarkWord(), Bridge が public なのでどうにかしたい by jflute (2025/12/05)
    // hint1: downHitPoint() よりは簡単。
    // hint2: わかっちゃえば、「なーんだ」とか「あああああ、なんで思いつかなかった＞＜」って感じ。
    // hint3: (↑つまり、オブジェクト指向とかそういういう大げさなものじゃない)

    protected abstract String getBarkWord();

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // TODO iwata 修行#: downHitPoint() が public なのでどうにかしたい、あと by jflute (2025/12/05)
    // hint1: Gemini の解決策Bは一瞬「おっ」って感じでおおぉ。
    // hint2: step7,8とか先に進んでからアプローチしてもOK
    public void downHitPoint() {
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

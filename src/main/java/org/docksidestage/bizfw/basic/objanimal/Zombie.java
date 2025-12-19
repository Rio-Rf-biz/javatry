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

import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;

/**
 * The object for zombie(ゾンビ).
 * @author Rio-Rf-biz
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // TODO done iwata 修行++: Zombie, これだと、bark()したときのbreatheIn処理と繋がってない by jflute (2025/12/05)
    // 元々はオーバーライドで、bark()が呼ばれた時のbreatheIn処理で、このオーバーライドメソッドが呼ばれて、
    // Zombieだけ日記を付けるという追加処理が入るようになっていた。
    // 今、new Zombie().bark() しても、BarkingProcessのbreatheIn()で、このcountBreatheIn()は呼ばれない。
    // hint1: オブジェクト指向の範疇内で実現可能
    @Override
    protected void doBreatheIn() {
        super.doBreatheIn();
        zombieDiary.countBreatheIn();
    }

    // #1on1: もし、ZombieBarkingProcessを作る場合のイメージ (2025/12/19)
    //@Override
    //protected BarkingProcess createBarkingProcess() {
    //    return new ZombieBarkingProcess();
    //}

    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}

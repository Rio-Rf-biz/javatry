package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;

// #1on1: これはこれで、ついつい呼んじゃうパターンをある程度防げるかもしれないということで改善はされている (2025/12/05)
// でも、完全には防げていないので、五十歩百歩神拳なので根本的にはそこまで変わってない。
// Bridge という発想は悪くない。でも、Bridgeを隠せないかな？
/**
 * @author Rio-Rf-biz
 */
public class AnimalBarkingBridge {

    public BarkedSound executeBark(Animal animal) {
        // 同じパッケージにいるので、animalの protected メソッドが見える
        String barkWord = animal.getBarkWord();
        return animal.doBark(barkWord);
    }
}

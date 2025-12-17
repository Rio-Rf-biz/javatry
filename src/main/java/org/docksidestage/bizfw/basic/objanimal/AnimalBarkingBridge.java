package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;

// #1on1: これはこれで、ついつい呼んじゃうパターンをある程度防げるかもしれないということで改善はされている (2025/12/05)
// でも、完全には防げていないので、五十歩百歩神拳なので根本的にはそこまで変わってない。
// Bridge という発想は悪くない。でも、Bridgeを隠せないかな？
/**
 * @author Rio-Rf-biz
 */
public class AnimalBarkingBridge {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Animal animal;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // pretedcted にして、同じパッケージからしかインスタンス化できないようにした
    protected AnimalBarkingBridge(Animal animal) {
        this.animal = animal;
    }

    public BarkedSound executeBark() {
        // 同じパッケージにいるので、animalの protected メソッドが見える
        return animal.doBark();
    }

    public void breatheIn() {
        animal.doBreatheIn();
    }

    public void prepareAbdominalMuscle() {
        // 同じパッケージなので protected な downHitPoint が見える
        animal.downHitPoint();
    }
}

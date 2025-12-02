package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;

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

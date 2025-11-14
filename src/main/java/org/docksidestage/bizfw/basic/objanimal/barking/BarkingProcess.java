package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;

/**
 * @author Rio-Rf-biz
 */
public class BarkingProcess {

    public static BarkedSound bark(Animal animal) {
        animal.breatheIn();
        animal.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = animal.doBark(barkWord);
        return barkedSound;
    }
}

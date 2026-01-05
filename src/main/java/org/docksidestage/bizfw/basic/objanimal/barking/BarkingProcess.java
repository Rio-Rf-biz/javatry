package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.AnimalBarkingBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rio-Rf-biz
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final AnimalBarkingBridge bridge;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(AnimalBarkingBridge bridge) {
        this.bridge = bridge;
    }

    public BarkedSound bark(BarkedSound barkedSound) {
        breatheIn();
        prepareAbdominalMuscle();
        return doBark(barkedSound);
    }

    // done iwata ちなみに、barkingに依存した息継ぎのつもりなので BarkingProcess に移動 by jflute (2025/11/21)
    // もし汎用息継ぎなのであればAnimalに留まったほうがいいけど、固有処理なので固有の場所で定義したい。
    public void breatheIn() { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy処理
        bridge.hookAfterBreatheInToBark();
    }

    private void prepareAbdominalMuscle() { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy処理
        bridge.hookAfterPrepareAbdominalMuscleToBark();
    }

    private BarkedSound doBark(BarkedSound barkedSound) {
        bridge.hookBeforeDoBarkToBark();
        return barkedSound;
    }
}

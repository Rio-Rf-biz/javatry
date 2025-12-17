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

    public static BarkedSound bark(AnimalBarkingBridge bridge) {
        breatheIn(bridge);
        prepareAbdominalMuscle(bridge);
        return bridge.executeBark();
    }

    // done iwata ちなみに、barkingに依存した息継ぎのつもりなので BarkingProcess に移動 by jflute (2025/11/21)
    // もし汎用息継ぎなのであればAnimalに留まったほうがいいけど、固有処理なので固有の場所で定義したい。
    public static void breatheIn(AnimalBarkingBridge bridge) { // actually depends on barking
        logger.debug("...Breathing in for barking"); // dummy implementation
        bridge.breatheIn(); // bridge経由でdownHitPointを呼び出す
    }

    private static void prepareAbdominalMuscle(AnimalBarkingBridge bridge) { // also actually depends on barking
        logger.debug("...Using my abdominal muscle for barking"); // dummy implementation
        bridge.prepareAbdominalMuscle();
    }
}

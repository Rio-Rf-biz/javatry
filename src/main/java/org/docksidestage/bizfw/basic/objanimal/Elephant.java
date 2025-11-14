package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.walker.SlowlyWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for Elephant(象).
 * @author Rio-Rf-biz
 */
public class Elephant extends Animal implements SlowlyWalker {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Elephant.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Elephant() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    public String getBarkWord() { return "pao-"; } // trumpet? in English

    // ===================================================================================
    //                                                                              Walker
    //                                                                              ======
    @Override
    public void walk() {
        logger.debug("...Walking now"); // dummy implementation
        downHitPoint();
    }
}

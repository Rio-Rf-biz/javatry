package org.docksidestage.javatry.basic.st6.os;

/**
 * @author Rio-Rf-biz
 */
public class Mac extends St6OperationSystem {
    
    public Mac(String loginId) {
        super(loginId);
    }
    
    @Override
    protected String getFileSeparator(){
        return "/";
    }

    @Override
    protected String getUserDirectory(){
        return "/Users/" + loginId;
    }
}

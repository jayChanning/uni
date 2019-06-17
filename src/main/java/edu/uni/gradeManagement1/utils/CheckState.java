package edu.uni.gradeManagement1.utils;

/**
* @author 陈少鑫
* @description
* @date 9:06 2019-05-06
* @modified 9:06 2019-05-06
*/

public enum CheckState {

    UNREVIEWED((byte)0),
    REVIEWED_BY_ACADMIN((byte)1),
    DISAGREE((byte)2),
    AGREE((byte)3),
   // AGREE_BY_ACADMIN((byte)3),
    DISAGREE_BY_ACADMIN((byte)4);
    private byte status;

    CheckState(Byte status){
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }
}

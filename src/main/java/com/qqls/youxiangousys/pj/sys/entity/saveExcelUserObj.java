package com.qqls.youxiangousys.pj.sys.entity;

import java.util.List;

public class saveExcelUserObj {
    //导入car之后返回结果对象
    private int successNumber;//导入成功的数量
    private List<Integer> errorNumber;//导入失败的行号集合
    public saveExcelUserObj(){
    }
    public saveExcelUserObj(int successNumber, List<Integer> errorNumber){
        this.successNumber = successNumber;
        this.errorNumber = errorNumber;
    }
    public int getSuccessNumber() {
        return successNumber;
    }
    public void setSuccessNumber(int successNumber) {
        this.successNumber = successNumber;
    }
    public List<Integer> getErrorNumber() {
        return errorNumber;
    }
    public void setErrorNumber(List<Integer> errorNumber) {
        this.errorNumber = errorNumber;
    }
    public String toString() {
        return "saveExcelUerObj [successNumber=" + successNumber + ", errorNumber=" + errorNumber + "]";
    }
}

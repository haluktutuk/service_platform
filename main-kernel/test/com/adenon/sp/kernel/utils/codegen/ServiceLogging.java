package com.adenon.sp.kernel.utils.codegen;

public class ServiceLogging implements ISrv {

    @Override
    public int hebe(final int num, final String str) {
        System.out.println("hebe is called..");
        return 0;
    }

    @Override
    public String hube() {
        System.out.println("hube is called..");
        return "aaanaaaaaaaaaaaaaaaaaa";
    }

    public String hebele(final int i, final String s, final Object o) throws Exception {
        throw new Exception();
    }

}

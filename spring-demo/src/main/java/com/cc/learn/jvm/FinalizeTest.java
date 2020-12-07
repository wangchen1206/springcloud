package com.cc.learn.jvm;

/**
 * 对象死亡判断
 * 任何对象的 finalize() 方法都只会被系统调用一次。
 * 在这个方法中如果此对象将自己赋值给某个类变量时，则表示此对象已经被引用了。因此不能被标识为死亡状态
 *
 * @author wangchen
 * @createDate 2020/12/2
 **/
public class FinalizeTest {

    // 需要状态判断的对象

    public static FinalizeTest Hook = null;

    @Override

    protected void finalize() throws Throwable {

        super.finalize();

        System.out.println("执行了 finalize 方法");

        FinalizeTest.Hook = this;

    }

    public static void main(String[] args) throws InterruptedException {
        Hook = new FinalizeTest();
        // 卸载对象，第一次执行 finalize()
        Hook = null;
        System.gc();
        Thread.sleep(500); // 等待 finalize() 执行
        if (Hook != null) {
            System.out.println("存活状态");
        } else {
            System.out.println("死亡状态");
        }
        // 卸载对象，与上一次代码完全相同
        Hook = null;
        System.gc();
        Thread.sleep(500); // 等待 finalize() 执行
        if (Hook != null) {
            System.out.println("存活状态");
        } else {
            System.out.println("死亡状态");

        }

    }

}

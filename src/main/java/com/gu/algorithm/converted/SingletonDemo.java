package com.gu.algorithm.converted;

/**
 * 1)饿汉式(静态常量)
 * 2)饿汉式（静态代码块）
 * 3)懒汉式(线程不安全)
 * 4)懒汉式(线程安全，同步方法)
 * 5)懒汉式(线程安全，同步代码块)
 * 6)双重检查
 * 7)静态内部类
 * 8)枚举
 *  源码在jdk中的应用   Runtime
 * @author gu
 * @create 2021/1/28 下午3:05
 */
public class SingletonDemo {
}


//饿汉静态常量

/**
 * 优缺点说明：
 * 1)优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
 * 2)缺点：在类装载的时候就完成实例化，没有达到 Lazy Loading 的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
 * 3)这种方式基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，在单例模式中大多数都是调用 getInstance
 * 方法， 但是导致类装载的原因有很多种，因此不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 就没有达到 lazy loading 的效果
 * <p>
 * 4)结论：这种单例模式可用，可能造成内存浪费
 */
class Single1 {
    public Single1() {
    }

    private final static Single1 SINGLE_1 = new Single1();

    public static Single1 getInstance() {
        return SINGLE_1;
    }
}

/**
 * 饿汉静态代码块
 * 内存的浪费
 */
class Single2 {
    public Single2() {
    }

    public static Single2 single2;

    static {
        single2 = new Single2();
    }

    public static Single2 getInstance() {
        return single2;
    }
}

/**
 * 懒汉  线程不安全
 */
class Single3 {
    public Single3() {
    }

    public static Single3 single3;

    public static Single3 getInstance() {
        if (single3 == null) {
            single3 = new Single3();
        }
        return single3;
    }
}

/**
 * 1)解决了线程安全问题
 * 2)效率太低了，每个线程在想获得类的实例时候，执行 getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接 return 就行了。方法进行同步效率太低
 * 3)结论：在实际开发中，不推荐使用这种方式
 * 懒汉式(线程安全，同步方法)
 */
class Single4 {
    private static Single4 single4;

    public Single4() {
    }

    public static synchronized Single4 getInstance() {
        if (single4 == null) {
            single4 = new Single4();
        }
        return single4;
    }
}

/**
 * 饿汉线程安全  同步代码块
 */
class Single5 {
    public Single5() {
    }

    private static Single5 single5;

    public static Single5 getInstance() {
        if (single5 == null) {
            synchronized (Single5.class) {
                single5 = new Single5();
            }
        }
        return single5;
    }
}

/**
 * 饿汉  双重检索
 * <p>
 * 1)Double-Check 概念是多线程开发中常使用到的，如代码中所示，我们进行了两次 if (singleton == null)检查，这样就可以保证线程安全了。
 * 2)这样，实例化代码只用执行一次，后面再次访问时，判断 if (singleton == null)，直接 return 实例化对象，也避免的反复进行方法同步.
 * 3)线程安全；延迟加载；效率较高
 */
class Single6 {

    private static volatile Single6 single6;

    public Single6() {
    }

    public static synchronized Single6 getInstance() {
        if (single6 == null) {
            synchronized (Single6.class) {
                single6 = new Single6();
            }
        }
        return single6;
    }

    /**
     * 静态内部类
     */
    static class Single7 {
        private Single7() {
        }

        private static volatile Single7 single7;

        private static class Single7Instance {
            public final static Single7 single7 = new Single7();
        }

        public static synchronized Single7 getInstance() {
            single7 = Single7Instance.single7;
            return single7;
        }
    }
}
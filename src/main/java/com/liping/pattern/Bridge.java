package com.liping.pattern;

/**
 * 桥接模式
 * 桥接（Bridge）模式的定义如下：将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度
 * 桥接（Bridge）模式包含以下主要角色。
 * 抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
 * 扩展抽象化（Refined    Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
 * 实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
 * @author Felix
 * @create 2019-10-31-15:29
 */
public class Bridge {

    public static void main(String[] args){
        /**
         * result:
         *  扩展抽象化(Refined Abstraction)角色被访问
         * 具体实现化（Concrete Implementor）角色被访问
         */
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(implementor);
        abs.operation();
    }


}

//实现化角色
interface Implementor {
    
    public void operationImpl();
    
}

//具体实现化（Concrete Implementor）角色
class ConcreteImplementorA implements Implementor {
    
    @Override
    public void operationImpl() {
        System.out.println("具体实现化（Concrete Implementor）角色被访问");
    }
}

//扩展抽象化（Refined    Abstraction）角色
abstract class Abstraction {
    protected Implementor imple;
    
    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }
    
    public abstract void operation();
}

class RefinedAbstraction extends Abstraction {
    
    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }
    
    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问" );
        imple.operationImpl();
    }
}


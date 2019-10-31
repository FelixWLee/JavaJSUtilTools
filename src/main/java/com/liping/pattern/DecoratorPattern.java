package com.liping.pattern;

/**
 * 装饰模式：指在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）的模式，它属于对象结构型模式。
 *
 *  装饰模式主要包含以下角色。
 * 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
 * 具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
 * 抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 * 具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 * @author MG01967
 * @create 2019-10-31-16:46
 */
public class DecoratorPattern {
    
    /**
     * 创建具体构架角色
     * 调用具体构件角色的方法operation()
     * --------------------------------
     * 调用具体构件角色的方法operation()
     * 为具体构件角色增加额外的功能addedFunction()
     * @param args
     */
    public static void main(String[] args){
        Component component = new ConcreteComponent();
        component.operation();
        System.out.println("--------------------------------");
        Component d = new ConreteDecorator(component);
        d.operation();
    }
    
}

//抽象构件（Component）角色
interface Component {
    public void operation();
}

//具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
class ConcreteComponent implements  Component {
    
    public ConcreteComponent() {
        System.out.println("创建具体构架角色");
    }
    
    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}

//抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
class Decorator implements Component {
    
    private Component component;
    
    public Decorator(Component component) {
        this.component = component;
    }
    
    @Override
    public void operation() {
        component.operation();
    }
}

//具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任
class ConreteDecorator extends Decorator {
    
    public ConreteDecorator(Component component) {
        super(component);
    }
    
    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }
    
    private void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
    
}


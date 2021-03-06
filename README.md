# Reflector

***

* **По поводу багов или идей для данного репозитория можно писать в Discord или ВК(обратная связь)**

***
## Обратная связь
* Discord: UnLegit#6190
* **[ВКонтакте](https://vk.com/unlegit_code)**

***

## FieldAccessor

Интерфейс доступа и работы с полями предоставляет базовые методы для получения и установки значений для данного поля

```java
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.Reflector;

import java.util.concurrent.ThreadLocalRandom;

public class FieldAccessTest {

    public static void main(String[] args) {
        String object = "БлаБлаБлаБлаБла"; //Создаём объект для обработки
        //P.S.:Про класс 'Reflector' поговорим немного позднее

        FieldAccessor hashAccessor = Reflector.getFieldAccess(object.getClass(), "hash");
        //Получаем объект 'FieldAccessor' для работы с полем 'hash' класса 'String'

        int hashValue = hashAccessor.getValue(object); //Получаем значение поля для объекта 'Object'

        hashAccessor.setValue(object, ThreadLocalRandom.current().nextInt()); //Устанавливаем значение поля на случайное число

        //P.S.:Если поле является статическим, то передаваемый объект должен быть 'null'
    }
}
```

***

## MethodAccessor

Интерфейс, похожий на 'FieldAccessor', но для работы с методами. 
Принцип у него аналогичен с FieldAccessor, так что обойдёмся без примера

***

## ConstructorAccessor

Опять же не сложный интерфейс, но для работы с конструкторами. 
Работать с ним также не сложно, так что без примера.

***

## ClassAccessor

Интерфейс, для работы с классом и получения доступа к полям, методам и конструкторам данного класса.

```java
import ru.unlegit.reflector.AccessStrategy;
import ru.unlegit.reflector.clas.ClassAccessor;
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.Reflector;

public class ClassAccessorTest {

    public static void main(String[] args) {
        ClassAccessor<String> stringAccessor =
                Reflector.getClassAccess(AccessStrategy.SIMPLE, String.class);
        //Получаем экземпляр для работы с классом 'String'
        //P.S:Про 'AccessStrategy' поговорим немного позднее

        FieldAccessor hashAccessor = stringAccessor.getFieldAccess("hash");
        //Получаем доступ для поля 'hash' для дальнейшей работы с ним
        //Таким образом можно получить доступ и к методам, и к конструкторам
    }
}
```

## ObjectAccessor

Интерфейс для работы непосредственно с объектом класса

```java
import ru.unlegit.reflector.AccessStrategy;
import ru.unlegit.reflector.object.ObjectAccessor;
import ru.unlegit.reflector.Reflector;

public class ClassAccessorTest {

    public static void main(String[] args) {
        String object = "БлаБлаБлаБлаБла";

        ObjectAccessor<String> stringAccessor =
                Reflector.getObjectAccess(AccessStrategy.SIMPLE, object);

        stringAccessor.setFieldValue("hash", 10100);
        //Установили значение поля 'hash' для обрабатываемого объекта

        int hash = stringAccessor.getFieldValue("hash");
        //Получили значение поля

        String substring = stringAccessor.invokeMethod("substring", 1, 5);
        //Выполняем метод 'substring' с параметрами '9' и '10', а также получаем результат
    }
}
```

***

## Reflector

Наконец-то дошла очередь до класса Reflector. Что касается данного класса, то это просто фабрика для удобства работы.

***

## AccessStrategy

Данные enum предназначен для классов 'ClassAccessor' и 'ObjectAccessor'. Это стратегия получения доступа к элементам класса.

* SIMPLE: каждый раз, при попытке получения доступа к элементам класса будут создаваться новые объекты-accessor'ы
* CACHING: при такой стратегии все accessor'ы будут кешироваться в хранилище и при дальнейшей попытке использования будут доставаться из хранилища или
создаваться новые, если ещё не были созданы
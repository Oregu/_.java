_.java
======

Java 8 functional utility _ belt

Examples
--------
```Java
_.map(Field::getName, fields)
```
```Java
currySum = _.curry((int a, int b) -> a+b);
sumWith7 = currySum.apply(7);
```

License
-------
MIT

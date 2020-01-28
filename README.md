# Kompilator
Autor: Piotr Andrzejewski

Nr indeksu: 244931

## Wymagania

Do korzystania z kompilatora potrzebne są:
* Maven 
* Java

To compile:

```
mvn compile test
```

This will build and test that the `src/test/resources/test.txt` file
is parsed and generates the expected `src/test/resources/output.good`.

To run:

```
mvn package
java -jar target/simple-maven-full-1.0.jar core.kompilator test.txt
```

## Files:

* `src/main/java/core.kompilator.java`         demo of a main program
* `src/main/flex/lcalc.flex`        the lexer spec
* `src/main/cup/ycalc.cup`          the parser spec
* `src/test/resources/test.txt`     sample input for testing
* `src/test/resources/output.good`  how the output should look like for the test
* `pom.xml`                         the Maven Project model
  - The `jflex-maven-plugin` generates `Lexer.java` from `lcalc.flex`
  - The `cup-maven-plugin` generates `sym.class` from `ycalc.cup`
  

# Kompilator
Autor: Piotr Andrzejewski

Nr indeksu: 244931

## Wymagania

Do korzystania z kompilatora potrzebne są:
* Maven (testowane na wersji 3.6.0)
* Java (testowane na wersji 13)

Na systemie Ubuntu można zainstalować Mavena komendą:

```
sudo apt install maven
```

## Budowanie

Do skompilowania używana jest komenda:

```
mvn compile
```

Do kompilacji i zbudowania pliku JAR używana jest komenda:

```
mvn package -Dmaven.test.skip=true
``` 

Przygotowany został plik Makefile, który wykonuje powyższą komendę. Można go uruchomić poleceniem:
```
make
```

## Uruchomienie

Uruchomienie:

```
java -cp target/kompilator-full-1.0.jar core.kompilator <nazwa pliku wejściowego> <nazwa pliku wyjściowego>
```

## Files:

* `src/main/java/core/kompilator.java`  główny plik
* `src/main/flex/lexer.flex`            specyfikacja leksera
* `src/main/cup/parser.cup`             specyfikacja parsera
* `pom.xml`                             Maven Project model
  - `jflex-maven-plugin` generuje `Lexer.java` z `lexer.flex`
  - `cup-maven-plugin` generuje `sym.class` z `parser.cup`
  

# MI-RUN interpret
Cílem tohoto projektu bylo vytvořit interpret bytecodu podmožiny jazyka Java.
## Popis jazyka
Interpret umožňuje intepretovat přibližně 70 základních instrukcí javovského bytecodu.
Implementovány jsou:
* řídící struktury programu: if-else, for, while, do-while
* matematické operace s celočíselnými hodnotami
* logické operace: &&, ||, !, ^
* práce s poli //TODO
* volání metod //TODO umíme i nestatické metody?
* vstup ze stdin (ASCII hodnoty znaků)
* výstup na stdout (celočíselné datové typy a textové řetězce)
* dynamické alokace paměti
* //TODO dopsat další features

## Použití interpretu
Práce s intepretem vyžaduje nainstalované [JDK8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
nebo [GraalVM] (https://github.com/graalvm/graalvm).
### Sestavenení interpretu
Sestavení se provádí buidovacím nástrojem [Maven] (https://maven.apache.org/), všechny závislosti jsou dostupné ze standartních veřejných repozitářů.
Pro sestavení projektu spustíme v jeho kořenovém adresáři příkaz `mvn package`.

### Spuštění programu
1. Napiš program v podmnožině jazyka Java, který využívá pouze výše popsané konstrukty a je přeložitelný překladačem `javac`.
2. Tento program ulož do *.java souboru.
3. Přelož program překladačem javac např: `javac TestPrimes.java`
4. Spusť program v interpretu např: `java -jar run-interpret-1.0-SNAPSHOT-jar-with-dependencies.jar TestPrimes.class`.//TODO upravit, otestovat

### Ukázkové soubory
Ukázkové soubory určené k demonstraci funkčnosti interpretu jsou připraveny ve složce test_files.
Nejsofistikovanější ukázky jsou připraveny v souborech TestPrimes.java a Knapsack.java.

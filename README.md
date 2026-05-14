# Activitat RA3 i RA4: JUnit 5, CI, optimització i documentació

Aquest projecte és la base de treball per escriure proves unitàries amb JUnit 5, configurar CI amb GitHub Actions i incorporar una part pràctica del RA4.

## Tasques RA3

1. Obre el projecte amb IntelliJ IDEA.
2. Revisa les classes del paquet `cat.inspla.ra3.reserves`.
3. Completa els tests a `src/test/java/cat/inspla/ra3/reserves`.
4. Executa els tests amb l'IDE o amb:

```bash
mvn test
```

5. Revisa el workflow `.github/workflows/maven-tests.yml`.
6. Fes push a GitHub i comprova la pestanya Actions.

## Tasques RA4 incorporades

1. Localitza els mètodes optimitzables de `ServeiReserves`:
   - `obtenirRecursosOrdenatsPerNom()`
   - `generarInformeRecursos()`
2. Optimitza almenys un dels dos mètodes sense canviar-ne el comportament.
3. Documenta la classe `Aula` utilitzant Javadoc.
4. Explica en el README o en un document breu què has modificat, per què és una optimització i quina prova demostra que continua funcionant.

## Requisits mínims

- 12 tests unitaris.
- 2 tests amb `assertThrows`.
- 1 prova parametritzada.
- Tests dels mètodes optimitzats.
- Classe `Aula` documentada amb Javadoc.
- Pipeline de GitHub Actions funcionant.

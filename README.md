# Maces Plus

A Fabric mod for Minecraft 1.21.11 that adds a full tier progression of maces:
stone, golden, diamond and netherite.

## Requirements

| | |
|---|---|
| Minecraft | 1.21.11 |
| Fabric Loader | 0.20.1 |
| Fabric API | 0.141.6+1.21.11 |
| Mappings | Yarn 1.21.11+build.6 |
| Loom | 1.19 |
| Gradle | 9.8.2 (via the wrapper) |
| **JDK to run Gradle** | **25 or newer** |
| JDK the mod targets | 21 |

Loom 1.19 refuses to run on anything below Java 25, so the terminal needs a
JDK 25+ on `JAVA_HOME` even though the mod itself still compiles to Java 21
bytecode. IntelliJ is already pointed at OpenJDK 27 via `.idea/gradle.xml`, so
IDE syncs work without any extra setup. For the command line:

```sh
export JAVA_HOME=/Users/bobthewizard/Library/Java/JavaVirtualMachines/openjdk-27/Contents/Home
```

## Commands

```sh
./gradlew build       # compile and produce build/libs/MacesPlus-1.0-SNAPSHOT.jar
./gradlew runClient   # launch a dev client with the mod loaded
./gradlew runServer   # launch a dev server
./gradlew runDatagen  # regenerate everything under src/main/generated
```

## Layout

```
src/main/java/...         # common code: items, creative tab
src/client/java/...       # client-only code and the data generators
src/main/resources/       # fabric.mod.json, mixin configs, textures, mod icon
src/main/generated/       # produced by runDatagen - do not hand-edit
```

Models, the `en_us` language file and recipes are **generated**. Change the
providers in `src/client/java/org/BobTheWizard/maces_plus/client/datagen/` and
re-run `./gradlew runDatagen` rather than editing the JSON by hand.

## Adding another mace

1. Add a field in `ModItems`, reusing the `maceSettings(...)` helper.
2. Add it to both entry lists in `ModItemGroups`.
3. Register a model in `ModItemModelProvider`, a name in `ModLanguageProvider`,
   a recipe in `ModRecipeProvider` and its enchantment tags in
   `ModItemTagProvider`.
4. Drop a 16x16 PNG at
   `src/main/resources/assets/maces_plus/textures/item/<name>.png`.
5. Run `./gradlew runDatagen`.

## A note on mappings

This project uses **Yarn**, but the official Fabric docs for 1.21.11 are written
in **Mojang mappings**, so examples copied from them will not compile as-is.
The translations you will hit most often:

| Fabric docs (Mojang) | This project (Yarn) |
|---|---|
| `ResourceKey` | `RegistryKey` |
| `Item.Properties` | `Item.Settings` |
| `Item.Properties#setId` | `Item.Settings#registryKey` |
| `BuiltInRegistries.ITEM` | `Registries.ITEM` |
| `Identifier.fromNamespaceAndPath` | `Identifier.of` |

[Linkie](https://linkie.shedaniel.dev/mappings) and
[mappings.dev](https://mappings.dev) translate names both ways.

Note that Yarn is frozen after 1.21.11 and is not supported on 26.1. Moving
past this version means migrating to Mojang mappings first, via
`./gradlew migrateMappings --mappings "net.minecraft:mappings:1.21.11"`.

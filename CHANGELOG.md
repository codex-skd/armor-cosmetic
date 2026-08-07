---

## [1.0.6] - 2026-08-07

### Fix

- **Sincronización de inventario**: Corregido bug crítico donde cambiar del inventario cosmético al normal cerraba el inventario normal inesperadamente. El servidor ahora cierra correctamente el contenedor cosmético sin requerir gestión manual de pantalla en el cliente.

### Feature

- **Render del avatar del jugador**: Añadido renderizado del modelo del jugador directamente en la pantalla de inventario cosmético. El personaje ahora se muestra con los cosméticos equipados actualmente, rotando suavemente para seguir el movimiento del ratón.

## [1.0.5] - 2026-08-05

### Change

- **Recompilado contra NeoForge `26.2.0.37-beta`**: bump de `neo_version` en `gradle.properties` (`26.2.0.32-beta` -> `26.2.0.37-beta`). Verificado con `runServer` (arranque sin errores).

## [
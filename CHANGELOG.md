---

## [1.0.7] - 2026-08-07

### Improvement

- **Reposicionamiento del avatar del jugador**: El modelo del jugador ahora aparece correctamente en el lado derecho de la interfaz. Aumentado el tamaño de vista previa de 30 a 40 píxeles para mejor visibilidad, y optimizado el punto de rotación para animaciones suaves.

### Enhancement

- **Mejora del botón de cerrar inventario**: El servidor ahora envía explícitamente el packet de cierre del contenedor para asegurar sincronización correcta entre cliente y servidor al volver al inventario normal.

### Feature

- **Soporte expandido de idiomas**: Añadidos 4 nuevos idiomas (Italiano, Holandés, Sueco, Polaco) expandiendo de 11 a 15 idiomas soportados. Mejor cobertura para jugadores europeos.

## [1.0.6] - 2026-08-07

### Fix

- **Sincronización de inventario**: Corregido bug crítico donde cambiar del inventario cosmético al normal cerraba el inventario normal inesperadamente. El servidor ahora cierra correctamente el contenedor cosmético sin requerir gestión manual de pantalla en el cliente.

### Feature

- **Render del avatar del jugador**: Añadido renderizado del modelo del jugador directamente en la pantalla de inventario cosmético. El personaje ahora se muestra con los cosméticos equipados actualmente, rotando suavemente para seguir el movimiento del ratón.

## [1.0.5] - 2026-08-05

### Change

- **Recompilado contra NeoForge `26.2.0.37-beta`**: bump de `neo_version` en `gradle.properties` (`26.2.0.32-beta` -> `26.2.0.37-beta`). Verificado con `runServer` (arranque sin errores).

## [
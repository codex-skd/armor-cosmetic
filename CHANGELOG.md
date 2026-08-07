---

## [1.0.12] - 2026-08-08

### Fix

- **Posición del modelo del jugador**: Reposicionado el avatar desde el centro hacia la esquina superior derecha de la pantalla de inventario cosmético, mejorando la distribución visual del GUI.

- **Tamaño de la vista previa del jugador**: Aumentado el tamaño de renderizado del modelo de 40 a 50 píxeles para mejor visibilidad y detalle.

- **Botón de cerrar mejorado**: Ampliado el botón de cierre (✕) de 16x16 a 20x20 píxeles para mayor facilidad de interacción. Ajustada su posición para un placement óptimo.

- **Cálculo dinámico del punto central**: Mejorados los cálculos de interacción del ratón calculando el punto central dinámicamente desde los límites del área de renderizado en lugar de coordenadas fijas.

## [1.0.11] - 2026-08-07

### Fix

- **Alineación vertical del avatar del jugador**: Corregida la posición vertical del centro del modelo del jugador en la pantalla de inventario cosmético, de `topPos + 75` a `topPos + 80`, garantizando que el avatar esté perfectamente centrado en el área de visualización.

- **Botón de cerrar inventario cosmético**: Añadido un botón dedicado (✕) en la esquina superior derecha de la interfaz de inventario cosmético, permitiendo a los jugadores volver al inventario normal de manera intuitiva sin depender del botón del libro de recetas.

## [1.0.10] - 2026-08-07

### Fix

- **Botón Cerrar inventario cosmético**: Corregido comportamiento donde el botón cerraba completamente en lugar de cambiar al inventario normal. El servidor ahora cierra correctamente el contenedor cosmético permitiendo transición suave al inventario vanilla.

- **Posición del avatar del jugador**: Recentrado el modelo del jugador desde `leftPos + 51` a `leftPos + 90` para una alineación perfecta con la zona visual del GUI cosmético.

## [1.0.9] - 2026-08-07

### Polish

- **Refinamiento de posicionamiento GUI**: Ajustes finales del avatar del jugador para alinación perfecta con la pantalla de inventario vanilla.
- **Navegación de inventario mejorada**: Transiciones suaves y sin fricciones entre modos de inventario cosmético y normal.

### Estatus

- Sistema de armadura cosmética completamente refinado y pulido.
- Todas las issues conocidas resueltas.
- UI/UX optimizada para experiencia de usuario excelente.

## [1.0.8] - 2026-08-07

### Fix

- **Posición del avatar del jugador**: Ajustada posición del modelo del jugador para coincidir con la pantalla de inventario normal. El avatar ahora aparece en el área centro-izquierda, proporcionando una experiencia visual consistente.

- **Comportamiento del botón Cerrar**: Corregido el botón para que abra adecuadamente el inventario normal. Ahora abre la pantalla `InventoryScreen` directamente en lugar de solo cerrar, garantizando transiciones suaves entre modos de inventario.

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
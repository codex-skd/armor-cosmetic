---


## [1.1.18] - 2026-08-19

### Change

- **Actualización de NeoForge**: actualizado de 26.2.0.45-beta a 26.2.0.57.
- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `armor_cosmetic-26.2-neoforge-26.2.0.57-1.1.18.jar`.
- **Documentación del workflow**: actualizada `docs/WORKFLOW_ARMOR_COSMETIC_26-2.md` para reflejar la nueva rama de trabajo.

## [1.0.18] - 2026-08-18

### Change

- **Actualización de NeoForge**: actualizado de 26.2.0.37-beta a 26.2.0.45-beta.
- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `armor_cosmetic-26.2-neoforge-26.2.0.45-beta-1.0.18.jar`.
- **Documentación del workflow**: actualizada `docs/WORKFLOW_ARMOR_COSMETIC_26-2.md` para reflejar la nueva rama de trabajo.


## [1.0.17] - 2026-08-13

### Fix

- **Interacción con inventario normal tras cerrar cosmético (fix definitivo)**: El intento de la versión 1.0.15/1.0.16 (diferir la apertura de `InventoryScreen` a `extractRenderState`, la fase de extracción de estado del renderizado) no resolvía el problema — el inventario normal seguía sin responder a clics tras cerrar el inventario cosmético. La causa real era que `setScreenAndShow` se llamaba desde esa fase de renderizado en vez de al pulsar el botón, dejando la nueva `InventoryScreen` mal inicializada para la interacción del ratón. Ahora el botón ✕ cambia de pantalla directamente en su callback `onPress`, igual que ya hacía el botón del libro de recetas. Ahora el botón ✕ cambia de pantalla directamente en su callback `onPress`, igual que ya hacía el botón del libro de recetas.

### Technical

- Eliminado el flag `closeToInventory` y el bloque diferido en `extractRenderState` de `GuiCosArmorInventory`, ya sin uso tras el fix.


- Eliminado el flag `closeToInventory` y el bloque diferido en `extractRenderState` de `GuiCosArmorInventory`, ya sin uso tras el fix.

## [1.0.15] - 2026-08-08

### Fix

- **Interacción con inventario normal tras cerrar cosmético**: Corrección definitiva del bug donde el inventario normal no respondía a interacciones después de cerrar el inventario cosmético con el botón ✕. El problema era que `setScreenAndShow` abría `InventoryScreen` en el cliente mientras el servidor aún tenía el contenedor cosmético activo. Ahora el botón ✕ envía `PayloadOpenNormalInventory`, espera a que el servidor cierre el contenedor (lo que dispara `removed()`), y solo entonces abre `InventoryScreen`. Esto garantiza que el servidor ya liberó el contenedor cosmético antes de que el cliente intente interactuar con el inventario normal.

### Technical

- Añadido flag `closeToInventory` a `GuiCosArmorInventory` para diferir la apertura de `InventoryScreen` al momento de `removed()`, cuando el servidor ya procesó el cierre.
- Eliminada la llamada inmediata a `setScreenAndShow` desde el callback del botón ✕.

## [1.0.14] - 2026-08-08

### Fix

- **Botón toggle oculto en inventario cosmético**: Eliminado el GuiCosArmorButton redundante de la pantalla `GuiCosArmorInventory`. El botón toggle para abrir/cerrar el inventario cosmético ahora solo aparece en el `InventoryScreen` normal, ya que el botón ✕ dedicado maneja el cierre desde el inventario cosmético.

- **Botón de cierre (✕) funcional**: Corregido comportamiento del botón ✕ que solo enviaba el packet de cierre al servidor pero no mostraba la pantalla de inventario normal. Ahora envía `PayloadOpenNormalInventory` para sincronizar el estado del servidor Y llama a `setScreenAndShow` para mostrar inmediatamente el `InventoryScreen`.

## [1.0.13] - 2026-08-08

### Fix

- **Posición del avatar del jugador**: Reposicionado el modelo del jugador al lado izquierdo de la pantalla de inventario cosmético (coordenadas vanilla: `leftPos + 26` a `leftPos + 76`, `topPos + 8` a `topPos + 78`), reemplazando el posicionamiento anterior en la zona central-derecha. Tamaño reducido de 50 a 30 píxeles para coincidir con las proporciones del inventario normal.

- **Tooltip del botón de cierre (✕)**: Añadido tooltip `"cos.gui.tooltip.close"` ("Cerrar Inventario Cosmético") al botón de cierre, mejorando la accesibilidad y claridad de la interfaz.

- **Interacción con inventario normal tras cerrar cosmético**: Corregido bug crítico donde el botón ✕ creaba una `InventoryScreen` solo en cliente sin notificar al servidor, causando que el inventario normal no respondiera a interacciones. Ahora envía `PayloadOpenNormalInventory` al servidor, que ejecuta `doCloseContainer()` sincronizando correctamente el estado.

- **Traducciones del tooltip de cierre**: Añadida clave `cos.gui.tooltip.close` en los 15 archivos de idioma (es_es: "Cerrar Inventario Cosmético", en_us: "Close Cosmetic Inventory", etc.).

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
<h1 align="center">&#128084; Armor Cosmetic</h1>

<p align="center"><strong>Wear two sets of armor &mdash; one for function, one for fashion.</strong></p>

<p align="center">
<img src="https://img.shields.io/badge/loader-NeoForge-orange?style=plastic&logo=curseforge" alt="NeoForge">
<img src="https://img.shields.io/badge/minecraft-26.2%20%7C%2026.1.2%20%7C%201.21.1-blue?style=plastic" alt="Minecraft 26.2, 26.1.2 and 1.21.1">
<img src="https://img.shields.io/badge/side-client%20%2B%20server-brightgreen?style=plastic" alt="Client and Server">
<img src="https://img.shields.io/badge/license-MMPL--1.0.1-lightgrey?style=plastic" alt="Minecraft Mod Public License 1.0.1">
</p>

<br>

---

<br>

<h2>&#10024; Overview</h2>

<table>
<tr>
<td width="65%">
<p>Armor Cosmetic lets you equip a separate cosmetic armor set that displays over your real armor, keeping your best protective gear equipped while showing off any look you want &mdash; the stats of your netherite, the appearance of your choice.</p>

<p>A fork of <strong>CosmeticArmorReworked</strong> by <em>zlainsama</em> and <em>dmillerw</em>, ported to NeoForge. Not affiliated with or endorsed by the original authors.</p>
</td>
<td width="35%" align="center">
<a href="https://codex.skdragons.com/" target="_blank"><img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="160"></a>
</td>
</tr>
</table>

<br>

<h2>&#127919; Features</h2>

<h3>&#128737;&#65039; Dual Armor System</h3>
<p>Wear one armor set for protection stats, another purely for display. Your real armor stays equipped underneath.</p>

<h3>&#128421;&#65039; Cosmetic Inventory GUI</h3>
<p>Open the cosmetic armor screen from your normal inventory with a single button click and manage all cosmetic slots in a familiar interface.</p>

<h3>&#128280; Per-Slot Toggles</h3>
<p>Each cosmetic slot has an S/A toggle that hides your real armor for that slot &mdash; show off your cosmetic helmet while keeping your real chestplate visible.</p>

<h3>&#127760; Global Toggle</h3>
<p>Disable all cosmetic rendering with one click &mdash; handy for PvP or when you want to see your actual armor.</p>

<h3>&#9904;&#65039; Corail Tombstone Compatible</h3>
<p>Cosmetic items are captured by tombstones on death, so you won't lose your fashion.</p>

<h3>&#127760; 15 Languages</h3>
<p>English, Spanish, German, French, Italian, Dutch, Swedish, Polish, Japanese, Portuguese, Russian, Chinese, Korean, Turkish, Czech.</p>

<h3>&#9881;&#65039; Fully Configurable</h3>
<p>Button positions, sizes and visibility are customizable in the config file.</p>

<h3>&#128187; Commands</h3>
<ul>
<li><code>/clearcosarmor</code> &mdash; clear cosmetic armor from players</li>
<li><code>/coshat</code> &mdash; toggle cosmetic helmet visibility</li>
</ul>

<br>

<h2>&#129521; Mod Structure</h2>

<table>
<tr><th align="left">Area</th><th align="left">What it provides</th></tr>
<tr><td><code>api</code></td><td>Public surface for other mods: the cosmetic-inventory accessor, its slot definitions, and the events fired when cosmetic armor changes.</td></tr>
<tr><td><code>impl/inventory</code></td><td>The cosmetic-armor inventory itself: per-player storage, the S/A per-slot render flags and the global toggle state.</td></tr>
<tr><td><code>impl/client/gui</code></td><td>The cosmetic-armor screen opened from the inventory (slots, per-slot toggle buttons, the global toggle button) and the layer that renders cosmetic armor over the player.</td></tr>
<tr><td><code>impl/network/payload</code></td><td>Client&#8596;server sync of the cosmetic inventory and toggle state.</td></tr>
<tr><td><code>mixins</code></td><td>The small vanilla patches needed to draw cosmetic armor and to open the screen from the inventory button.</td></tr>
</table>

<br>

<h2>&#128203; Requirements</h2>

<table>
<tr><td><strong>Minecraft / NeoForge</strong></td><td>see <em>Available Versions</em> below</td></tr>
<tr><td><strong>Side</strong></td><td>Client and Server (required on both)</td></tr>
</table>

<br>

<h2>&#128230; Available Versions</h2>

<table>
<tr><th align="left">Minecraft</th><th align="left">NeoForge</th><th align="left">Java</th><th align="left">Latest build</th></tr>
<tr><td>26.2</td><td>26.2.0.57+</td><td>25</td><td><code>1.1.18</code></td></tr>
<tr><td>26.1.2</td><td>26.1.2.78+</td><td>25</td><td><code>1.0.23</code></td></tr>
<tr><td>1.21.1</td><td>21.1.249+</td><td>21</td><td><code>1.0.0</code></td></tr>
</table>

<p><em>All Minecraft versions share this CurseForge project. Pick the file that matches your Minecraft version.</em></p>

<br>

<h2>&#127918; How to Use</h2>

<ol>
<li>Install the mod on <strong>both client and server</strong>.</li>
<li>Open your inventory and click the new cosmetic armor button.</li>
<li>Place cosmetic armor pieces in the slots &mdash; they render over your real armor.</li>
<li>Use the S/A toggle per slot to control what shows.</li>
<li>Use the global toggle to disable cosmetic rendering entirely.</li>
</ol>

<br>

---

<br>

<h2>&#128591; Credits &amp; License</h2>

<p>Armor Cosmetic is a fork of <a href="https://www.curseforge.com/minecraft/mc-mods/cosmetic-armor-reworked">CosmeticArmorReworked</a> by <strong>zlainsama</strong> and <strong>dmillerw</strong>, ported to NeoForge by <strong>Stalking Dragons</strong>.</p>

<p><strong>License:</strong> distributed under the <strong>Minecraft Mod Public License (MMPL) version 1.0.1</strong>, the same license as the upstream mod. Per the MMPL this fork stays under the MMPL and its full source is published as a <code>-sources.jar</code> alongside every release. The full licence text and attribution ship in the jar and the repository <code>LICENSE</code> file.</p>

<br>
<br>

<p align="center">
  <a href="https://codex.skdragons.com/" target="_blank">
    <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="200">
  </a>
  <br>
  <a href="https://codex.skdragons.com/">https://codex.skdragons.com/</a>
  <br>
  <em>Codex Stalking Dragons &mdash; Minecraft Modding</em>
</p>

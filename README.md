# Tablee

**Tablee** is an experimental programming language based on a unified **"Everything is a Table"** paradigm. The project explores an alternative data and execution model where scopes, data structures, objects, and functions are represented as hierarchical table nodes.

### 🌟 Core Philosophy: The Tabular Paradigm
Instead of traditional OOP classes, objects, and nested scopes, Tablee models the entire program environment as a tree of specialized tables:
* **Tabular (`TableTabular`):** The primary container structure (arrays/lists/namespaces) capable of holding sub-tables, with optional type constraints and dynamic extensibility.
* **Template (`TableTemplate`):** Blueprints and prototypes used to instantiate structured tabular instances.
* **Functional (`TableFunctional`):** Executable nodes with defined variable arguments and support for both native hooks and custom bytecode.
* **Variable (`TableVariable`):** Typed value-holding leaf nodes supporting primitives (`Integer`, `Float`, `String`, `Boolean`) with immutability/constant guarantees.

### ⚙️ Architecture & Runtime
* **Custom Stack-based VM (`Executor`):** Bytecode execution engine with stack manipulation, table tree traversal (`EnterTable`, `LeaveTable`), arithmetics, and jump instructions.
* **Modular Standard Library (`PackageBase`):** Extensible package system integrated into a root `Global` table (e.g., standard I/O).

### 🚧 Project Status & Post-Mortem
Tablee is currently an **archived concept / prototype**. 

While the virtual machine, table hierarchy, type system, and runtime execution engine are fully designed and implemented, the project reached a dead end at the **Parsing / AST-to-Bytecode stage**. Translating non-linear, multi-dimensional tabular syntax and hierarchical scoping into a flat stack-based bytecode stream proved overly complex for a single-developer architecture without dedicated compiler generation tools.

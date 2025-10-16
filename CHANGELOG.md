### 0.11.0

- Project divided by modules
- Result API for managing return values and exceptions of methods
that can result in error. It takes inspiration in 
https://doc.rust-lang.org/std/result/ rust Result API. 
Use pattern matching or more traditional conditional statements
to manage results.
- Fl API for common IO tasks

### 0.9.0

- Added `Strings.toCamelCase`, `Strings::toKebabCase`, `Strings::toPascalCase`, 
`Strings::toSnakeCase`, `Strings::toLowerDotCase`
- Fixes and improvements for working with `Hexes`

### 0.8.0

- Rework of Hex representation utils

### 0.6.0

- Added more features to RegEx engine
- Added github workflow
- Updated baseline JDK -> 21

### 0.5.0

- Added Strings.toHexString(bytes[]) method

### 0.4.0

- Updated Gradle -> 8.2
- Updated baseline JDK -> 20
- Improved hex<>Number conversions
- Improved hex<>String conversions
- Added Numbers class with helpers to work with number conversions
- Added Dates class with helpers to work with dates
- Added more substr() methods to Strings helpers

### 0.3.0

- Added Convert class with conversion helpers

### 0.2.0

- Added Fls class for Files util methods

### 0.1.0

- Updated dependency: Gradle -> 8.0.2
- Removed dependency: org.junit.platform:junit-platform-launcher
- Added toPattern() method to RegExpBuilder for faster Pattern creation
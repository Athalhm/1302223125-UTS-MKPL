✨ Refactoring Summary
1. ✅ Magic Numbers → Constants
- Problem: Hardcoded values (e.g., 3000000, 1.5, 54000000) made the code hard to read and modify.

- Solution: Replaced with named constants like GRADE_1_SALARY, FOREIGNER_SALARY_MULTIPLIER, BASE_NON_TAXABLE_INCOME, etc.

- Benefit: Improved readability and easier future changes.

2. ✅ Long Method → Decomposed into Smaller Functions
- Problem: calculateTax() method was too long and complex.

- Solution: Broke it into smaller private helper methods for each calculation step.

- Benefit: Easier to understand, test, and debug individual parts.

3. ✅ Duplicate Code → Extracted to Common Methods
- Problem: Salary calculation for each grade repeated the same logic.

- Solution: Centralized salary logic into getBaseSalaryForGrade() and applied a single conditional foreigner multiplier.

- Benefit: Reduced redundancy and risk of inconsistency.

4. ✅ Poor Readability → Improved Naming & Structure
- Problem: Vague or misleading names like tax, income, or nested logic blocks.

- Solution: Renamed variables/methods to meaningful names and restructured blocks with early returns and clearer formatting.

- Benefit: Code is easier to read and follow.

5. ✅ Primitive Obsession → Domain-Specific Classes
- Problem: Strings and booleans used to represent complex entities like spouse, children, and gender.

- Solution: Introduced Spouse, Child, and Gender abstractions.

- Benefit: Improved type safety, encapsulation, and clarity.

6. ✅ Long Parameter List → Builder & Object Encapsulation
- Problem: Constructor and method calls had too many parameters.

- Solution: Replaced with object constructors and encapsulated values into classes like Spouse and Child, and used LocalDate instead of separate date integers.

- Benefit: Cleaner object instantiation and easier testing.

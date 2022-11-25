package tech.hibbs.expensereportkata;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

record Expense(String name, ExpenseType type, int amount) {}

public class ExpenseReport {
    private final List<Expense> expenses;

    public ExpenseReport(final List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String printReport() {
        StringBuilder stringBuilder = new StringBuilder();
        int total = 0;
        int mealExpenses = 0;

        stringBuilder.append("Expenses " + new Date() + "\n");

        for (Expense expense : expenses) {
            if (expense.type() == ExpenseType.DINNER || expense.type() == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount();
            }

            String mealOverExpensesMarker = expense.type() == ExpenseType.DINNER && expense.amount() > 5000 || expense.type() == ExpenseType.BREAKFAST && expense.amount() > 1000 ? "X" : " ";

            stringBuilder.append(expense.name() + "\t" + expense.amount() + "\t" + mealOverExpensesMarker + "\n");

            total += expense.amount();
        }

        stringBuilder.append("Meal expenses: " + mealExpenses + "\n");
        stringBuilder.append("Total expenses: " + total + "\n");

        return stringBuilder.toString();
    }
}

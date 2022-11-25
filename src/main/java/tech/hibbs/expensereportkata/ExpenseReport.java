package tech.hibbs.expensereportkata;

import java.util.Date;
import java.util.List;

// TODO:
//  - Pass in Date object

enum ExpenseType {
    DINNER("Dinner", 5000, true),
    BREAKFAST("Breakfast", 1000, true),
    CAR_RENTAL("Car Rental", 0, false),

    ;

    final String name;
    final int limit;
    final boolean isMealType;

    ExpenseType(String name, int limit, boolean isMealType) {
        this.name = name;
        this.limit = limit;
        this.isMealType = isMealType;
    }
}

record Expense(String name, ExpenseType type, int amount) {
    public boolean isMealType() {
        return type.isMealType;
    }

    private int getLimit() {
        return type.limit;
    }

    private boolean exceedsLimit() {
        int limit = getLimit();

        return limit > 0 && amount > limit;
    }

    private String getLimitMarker() {
        return exceedsLimit() ? "X" : "";
    }

    @Override
    public String toString() {
        return name() + "\t" + amount() + "\t" + getLimitMarker();
    }
}

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
            if (expense.isMealType()) {
                mealExpenses += expense.amount();
            }

            stringBuilder.append(expense + "\n");

            total += expense.amount();
        }

        stringBuilder.append("Meal expenses: " + mealExpenses + "\n");
        stringBuilder.append("Total expenses: " + total + "\n");

        return stringBuilder.toString();
    }
}

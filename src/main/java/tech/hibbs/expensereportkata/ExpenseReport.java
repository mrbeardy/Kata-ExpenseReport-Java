package tech.hibbs.expensereportkata;

import java.util.Date;
import java.util.List;

// TODO:
//  - Remove hard-coded meal-types in expense report

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

record Expense(String name, ExpenseType type, int amount) {
    public final static int BREAKFAST_OVER_EXPENSES_AMOUNT = 1000;
    public final static int DINNER_OVER_EXPENSES_AMOUNT = 5000;

    public int getOverExpensesAmount() {
        return switch(type) {
            case BREAKFAST -> BREAKFAST_OVER_EXPENSES_AMOUNT;
            case DINNER -> DINNER_OVER_EXPENSES_AMOUNT;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return name() + "\t" + amount();
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
            if (expense.type() == ExpenseType.DINNER || expense.type() == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount();
            }

            boolean mealOverExpenses = 
                expense.type() == ExpenseType.DINNER && expense.amount() > expense.getOverExpensesAmount() ||
                expense.type() == ExpenseType.BREAKFAST && expense.amount() > expense.getOverExpensesAmount();

            String mealOverExpensesMarker = mealOverExpenses ? "X" : " ";

            stringBuilder.append(expense.toString() + "\t" + mealOverExpensesMarker + "\n");

            total += expense.amount();
        }

        stringBuilder.append("Meal expenses: " + mealExpenses + "\n");
        stringBuilder.append("Total expenses: " + total + "\n");

        return stringBuilder.toString();
    }
}

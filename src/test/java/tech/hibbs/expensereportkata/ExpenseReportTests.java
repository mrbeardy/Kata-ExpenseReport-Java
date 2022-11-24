package tech.hibbs.expensereportkata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import org.junit.Test;

public class ExpenseReportTests {

    @Test
    public void testOriginal() {
        Expense carRentalExpense = new Expense();
        carRentalExpense.type = ExpenseType.CAR_RENTAL;
        carRentalExpense.amount = 1000;

        Expense breakfastExpense = new Expense();
        breakfastExpense.type = ExpenseType.BREAKFAST;
        breakfastExpense.amount = 50;

        Expense dinnerExpense = new Expense();
        dinnerExpense.type = ExpenseType.DINNER;
        dinnerExpense.amount = 120;

        Expense lavishDinnerExpense = new Expense();
        lavishDinnerExpense.type = ExpenseType.DINNER;
        lavishDinnerExpense.amount = 8000;

        List<Expense> expenses = List.of(carRentalExpense, breakfastExpense, dinnerExpense, lavishDinnerExpense);
        ExpenseReport expenseReport = new ExpenseReport();

        String result = expenseReport.printReport(expenses);
        System.out.println(result);

        assertTrue(result.contains("Expenses " + new Date()));
        assertTrue(result.contains("Car Rental\t" + carRentalExpense.amount));
        assertTrue(result.contains("Breakfast\t" + breakfastExpense.amount));
        assertTrue(result.contains("Dinner\t" + dinnerExpense.amount));
        assertTrue(result.contains("Dinner\t" + lavishDinnerExpense.amount + "\tX"));
        assertTrue(result.contains("Meal expenses: " + (breakfastExpense.amount + dinnerExpense.amount + lavishDinnerExpense.amount)));
        assertTrue(result.contains("Total expenses: " + (breakfastExpense.amount + dinnerExpense.amount + carRentalExpense.amount + lavishDinnerExpense.amount)));
    }
}

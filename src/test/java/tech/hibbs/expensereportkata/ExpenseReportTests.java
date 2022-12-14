package tech.hibbs.expensereportkata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import org.junit.Test;

public class ExpenseReportTests {

    @Test
    public void testOriginal() {
        Expense carRentalExpense = new Expense("Car Rental", ExpenseType.CAR_RENTAL, 1000);
        Expense breakfastExpense = new Expense("Breakfast", ExpenseType.BREAKFAST, 50);
        Expense dinnerExpense = new Expense("Dinner", ExpenseType.DINNER, 120);
        Expense lavishDinnerExpense = new Expense("Fancy Dinner", ExpenseType.DINNER, 8000);

        List<Expense> expenses = List.of(carRentalExpense, breakfastExpense, dinnerExpense, lavishDinnerExpense);
        ExpenseReport expenseReport = new ExpenseReport(expenses);

        String result = expenseReport.printReport();
        System.out.println(result);

        // TODO(mh): Refactor use of Date to avoid leaky test by allowing us to mock dependency.
        // assertTrue(result.contains("Expenses " + new Date()));
        assertTrue(result.contains("Car Rental\t" + carRentalExpense.amount() + "\t\n"));
        assertTrue(result.contains("Breakfast\t" + breakfastExpense.amount() + "\t\n"));
        assertTrue(result.contains("Dinner\t" + dinnerExpense.amount() + "\t\n"));
        assertTrue(result.contains("Fancy Dinner\t" + lavishDinnerExpense.amount() + "\tX\n"));
        assertTrue(result.contains("Meal expenses: " + (breakfastExpense.amount() + dinnerExpense.amount() + lavishDinnerExpense.amount())));
        assertTrue(result.contains("Total expenses: " + (breakfastExpense.amount() + dinnerExpense.amount() + carRentalExpense.amount() + lavishDinnerExpense.amount())));
    }
}

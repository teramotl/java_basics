package sqlAvgMonthlyCalculator;

import java.sql.*;

public class AvgMonthlySales {

    public static void main(String[] args) {
        String sql = "WITH CoursePeriod AS (" +
                "    SELECT " +
                "        course_name, " +
                "        MIN(MONTH(subscription_date)) AS first_month, " +
                "        MAX(MONTH(subscription_date)) AS last_month, " +
                "        COUNT(*) AS total_purchases " +
                "    FROM " +
                "        purchaselist " +
                "    WHERE " +
                "        YEAR(subscription_date) = 2018 " +
                "    GROUP BY " +
                "        course_name " +
                "), " +
                "MonthRange AS (" +
                "    SELECT " +
                "        course_name, " +
                "        (last_month - first_month + 1) AS months_count " +
                "    FROM " +
                "        CoursePeriod " +
                ") " +
                "SELECT " +
                "    cp.course_name, " +
                "    total_purchases / mr.months_count AS avg_purchases_per_month " +
                "FROM " +
                "    CoursePeriod cp " +
                "JOIN " +
                "    MonthRange mr " +
                "ON " +
                "    cp.course_name = mr.course_name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                double avgPurchasesPerMonth = rs.getDouble("avg_purchases_per_month");

                System.out.printf("Course: %s, Average Purchases Per Month in 2018: %.2f%n",
                        courseName, avgPurchasesPerMonth);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


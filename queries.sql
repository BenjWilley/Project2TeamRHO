/*finds week with where Breakfast is most popular*/
SELECT MAX(Breakfast) 
FROM sales;

/*compare the sales of tea and coffee*/
SELECT Coffee,Tea AS Comparison
FROM sales;

/*Shows results from week 1*/
SELECT * FROM sales LIMIT 1;

/*shows weeks where there were more orders of espresso*/
SELECT Week FROM sales
WHERE Espresso > Coffee;

/*this orders weeks by sales*/ 
SELECT Week FROM sales ORDER BY Revenue;

/*finds the income from all days and assigns totalRevenue with total*/
SELECT MAX(Revenue) AS totalRevenue 
FROM sales;

/*find least amount of revenue*/
SELECT MIN(Revenue) AS Broke
FROM sales;

/*finds week with where espresso most popular*/
SELECT MAX(Espresso) AS popular
FROM sales;

/*shows gameday week*/
SELECT * FROM sales WHERE week='38';

/*finds the number of weeks*/
SELECT COUNT(DISTINCT Week) 
FROM sales;

/*Gets average number*/
SELECT AVG(revenue) as avg_revenue
FROM sales;

/*Sums the bakery items sold*/
SELECT SUM(bakery) as total_bakery
FROM sales;

/*Lists espresso in order of revenue that week*/
SELECT espresso FROM sales ORDER BY Revenue;

/*first 10 weeks of hotchocolate orders*/
SELECT hotchocolate FROM sales LIMIT 10;

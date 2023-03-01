SELECT drinks, AVG(price) as avg_price
FROM sales;


SELECT food, SUM(price) as total_food
FROM sales;

SELECT food, SUM(price) as breakfast_total
FROM sales
WHERE category = “Breakfast”;

SELECT customizations
FROM sales
WHERE name = “Syrup” OR name = “Non Dairy”;

/finds week with where Breakfast is most popular/
SELECT Week,MAX(Breakfast) AS popular
From sales;

/compare the sales of tea and coffee/
SELECT Week,Coffe,Tea AS Comparison
FROM sales;

/Shows results from week 1/
SELECT * 
FROM sales
WHERE Week = 1;

/shows weeks where there were more orders of espresso/
SELECT Week
FROM sales
WHERE Espresso > Coffee;

/this orders weeks by sales/ 
SELECT Week FROM sales ORDER BY Revenue;


/finds the income from game day/
SELECT Week, MAX(Revenue) AS gameDayWeek FROM sales;

/find the income from the other game day/
SELECT Week, MAX(Revenue) AS gameDayWeek2 
FROM sales
WHERE Revenue NOT IN gameDayWeek;

/find least amount of revenue/
SELECT Week, MIN(Revenue) AS Broke
From sales;

/finds week with where espresso most popular/
SELECT Week,MAX(Espresso) AS popular
From sales;

/finds the number of weeks/
SELECT COUNT(DISTINCT Week) 
FROM sales;
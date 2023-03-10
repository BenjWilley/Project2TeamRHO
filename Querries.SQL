
/*finds the income from game day*/
SELECT Week, MAX(Revenue) AS gameDayWeek FROM sales;

/*find the income from the other game day*/
SELECT Week, MAX(Revenue) AS gameDayWeek2 
FROM sales
WHERE Revenue NOT IN gameDayWeek;

/*find least amount of revenue*/
SELECT Week, MIN(Revenue) AS Broke
From sales;

/*finds week with where espresso most popular*/
SELECT Week,MAX(Espresso) AS popular
From sales;

/*compare the sales of tea and coffee*/
SELECT Week,Coffe,Tea AS Comparison
FROM sales;

/*Shows results from week 1*/
SELECT * 
FROM sales
WHERE Week = 1;

/*shows weeks where there were more orders of espresso*/
SELECT Week
FROM sales
WHERE Espresso > Coffee
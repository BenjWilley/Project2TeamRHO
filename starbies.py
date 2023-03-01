import math
import random

# declare varibles for array accessing

name = 2
tall = 3
grande = 4
venti = 5
price = 3

# open a csv file in read mode

file = open(r"C:\Users\ohbro\Downloads\StarbucksMenu.csv", "r")

# serperate the contents into several 2d arrays

drinks = []
foods = []
customization = []

# for each line in the file, sort into each array by appending
#   for each item, use the indices as access

for x in file:
    item = x.split(",")
    if item[0] == 'Drink':
        drink = [item[name], item[tall], item[grande], item[venti]]
        #TODO trim off the \n attached to some of the lines
        drinks.append(drink)
    if item[0] == 'Food':
        food = [item[name], item[price]]
        foods.append(food)
    if item[0] == 'Customization':
        custom = [item[name], item[price]]
        customization.append(custom)

# now that the data is usable, generate an order by choosing a random number of drinks and a random number of food items

def generate_order():
    order = []
    total = 0
    for food in range(random.randrange(3)):
        pick = random.choice(foods)
        order.append(pick)
        total += float(pick[1])

    for drink in range(random.randrange(3)): # for a random number of drinks in the order
        pick = random.choice(drinks) # pick the drink from the menu
        custom = random.choice(customization) # pick a random customization
        pick = [pick[0], custom[0], float(pick[random.randrange(1,4)]) + float(custom[1])] # set a random size

        order.append(pick)
        total += pick[2]
    
    return [total] + order

for day in range(7):
    day_total = 0
    for i in range(random.randrange(600, 1300)):
        order = generate_order()
        day_total += order[0]
    print(round(day_total, 2))


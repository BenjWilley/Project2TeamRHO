
class Order:
    def __init__(self):
        self.drinks = []
        self.foods = []
        self.total = 0
        self.notes = ""
        self.week = -1
        self.day = -1
        self.id = -1

    def add_drink(self, name, size, price, **kwargs):
        drink = {'name': name, 'size' : size, 'price' : price, 'customizations': kwargs}
        self.drinks.append(drink)

    def add_food(self, name, price, **kwargs):
        food = {'name': name, 'price' : price, 'customizations': kwargs}
        self.foods.append(food)

    def print(self):
        print(self.total)

    def update_total(self):
        total = 0
        for i in drinks:
            print(i)
            # total += i.get('price')
        for i in food:
            print(i)
            # total += i.get('price')

order = Order()

import math
import random
import datetime

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


'''
def generate_order():
    order = Order()
    for food in range(random.randrange(3) + 1):
        pick = random.choice(foods)
        order.add_food(pick[0], pick[1])

    for drink in range(random.randrange(3) + 1): # for a random number of drinks in the order
        pick = random.choice(drinks) # pick the drink from the menu
        custom = random.choice(customization) # pick a random customization
        pick = [pick[0], custom[0], float(pick[random.randrange(1,4)]) + float(custom[1])] # set a random size

        order.append(pick)
        total += pick[2]
    
    return [total] + order
'''

order = Order()

order.add_drink("Latte", "tall", 3)
order.add_drink("other drink", "short", 7)
order.add_food("food item", 3)
print(order.drinks)
order.update_total()
print(order.total)


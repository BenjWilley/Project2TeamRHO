import math
import random
import datetime
import copy

# declare varibles for array accessing

name = 2
tall = 3
grande = 4
venti = 5
price = 3

# open a csv file in read mode


# serperate the contents into several 2d arrays

# need to take in all menu items
# maybe use a menu class with items
# each item will have calories, price, an id, and a classificationv (food, drink, custom)

class Drink:
    def __init__(self, name, price, id, size):
        self.name = name
        self.price = float(price)
        self.id = id
        self.customizations = []
    
    def updateName(self, new_name):
        self.name = new_name

    def updatePrice(self, new_price):
        self.price = new_price

    def addCustomization(self, customization):
        self.customizations.append(customization)
        self.price += float(customization.price)


class Customization:
    def __init__(self, name, price, id):
        self.name = name
        self.price = float(price)
        self.id = id

    def updateName(self, new_name):
        self.name = new_name

    def updatePrice(self, new_price):
        self.price = new_price


class Food:
    def __init__(self, name, price, id):
        self.name = name
        self.price = price
        self.id = id

    def updateName(self, new_name):
        self.name = new_name

    def updatePrice(self, new_price):
        self.price = new_price


class Menu:
    def __init__(self):
        self.drinks = []
        self.foods = []
        self.customizations = []

    def addDrink(self, drink):
        self.drinks.append(drink)

    def addFood(self, food):
        self.foods.append(food)

    def addCustomization(self, customization):
        self.customizations.append(customization)

class Order:
    def __init__(self, time):
        self.time = time
        self.name = ""
        self.drinks = []
        self.foods = []
        self.total = 0
    
    def addDrink(self, drink):
        self.drinks.append(drink)
        self.total += float(drink.price)

    def addFood(self, food):
        self.foods.append(food)
        self.total += float(food.price)

    def setName(self, name):
        self.name = name


def loadMenu(menu, file):
    id = 1

    for x in file:
        item = x.split(",")
        if item[0] == 'Drink':
            # drink = [item[name], item[tall], item[grande], item[venti]]
            # TODO trim off the \n attached to some of the lines
            # drinks.append(drink)

            drink_tall = Drink("Tall " + item[name], float(item[tall]), id, "Tall")
            menu.addDrink(drink_tall)
            id += 1

            drink_grande = Drink("Grande " + item[name], float(item[grande]), id, "Grande")
            menu.addDrink(drink_grande)
            id += 1

            drink_venti = Drink("Venti " + item[name], float(item[venti]), id, "Venti")
            menu.addDrink(drink_venti)
            id += 1
        if item[0] == 'Food':
            # food = [item[name], item[price]]
            # foods.append(food)

            food = Food(item[name], item[price], id)
            menu.addFood(food)
            id += 1
        if item[0] == 'Customization':
            # custom = [item[name], item[price]]
            # customization.append(custom)
            custom = Customization(item[name], item[price], id)
            menu.addCustomization(custom)
            id += 1


menu = Menu()

file = open(r"C:\Users\ohbro\Downloads\StarbucksMenu.csv", "r")

loadMenu(menu, file)


def generate_order(date): # date in the format mm/dd/yyyy

    minute = str(random.randrange(50) + 10) # avoiding minutes less than 10 because the time formatting is funky
    hour = str(random.randrange(12) + 10) # open from 10am to 10pm
    time = date + " " + hour + ":" + minute

    order = Order(time)
    for food in range(random.randrange(3) + 1):
        pick = copy.deepcopy(random.choice(menu.foods))
        order.addFood(pick)

    for drink in range(random.randrange(3) + 1): # for a random number of drinks in the order
        pick = copy.deepcopy(random.choice(menu.drinks)) # pick the drink from the menu
        # custom = copy.deepcopy(random.choice(menu.customizations)) # pick a random customization
        for custom in range(random.randrange(3)):
            custom_pick = copy.deepcopy(random.choice(menu.customizations))
            pick.addCustomization(custom_pick)

        order.addDrink(pick)
    
    return order

def print_order(order):
    print(order.time, order.name)
    for drink in order.drinks:
        print(drink.price, drink.name, drink.id)
        for custom in drink.customizations:
            print("    ", custom.name, custom.id)
    for food in order.foods:
        print(food.price, food.name, food.id)
    print(order.total)



#print_order(generate_order("05/17/2023"))


# generator should make up a date and generate orders for those days
# printables:
# Drink : name, price, id, customizations and their name and id
# Food : name, price, id

file_out = open("order_data.csv", 'wt')

for i in range(1000):
    order = generate_order("00/00/0000")
    file_out.write(order.time + ", " + str(round(order.total, 2)) + ", " + str(len(order.drinks)) + ", " + str(len(order.foods)) + "\n")

file_out.close()










    


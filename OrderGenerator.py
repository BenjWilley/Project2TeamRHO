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
    def __init__(self, name, price, id, size, sub_category):
        self.name = name
        self.price = float(price)
        self.id = id
        self.customizations = []
        self.sub_category = sub_category

    def updateName(self, new_name):
        self.name = new_name

    def updatePrice(self, new_price):
        self.price = new_price

    def addCustomization(self, customization):
        self.customizations.append(customization)
        self.price += float(customization.price)


class Customization:
    def __init__(self, name, price, id, sub_category):
        self.name = name
        self.price = float(price)
        self.id = id
        self.sub_category = sub_category

    def updateName(self, new_name):
        self.name = new_name

    def updatePrice(self, new_price):
        self.price = new_price


class Food:
    def __init__(self, name, price, id, sub_category):
        self.name = name
        self.price = float(price)
        self.id = id
        self.sub_category = sub_category

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

            drink_tall = Drink("Tall " + item[name], float(item[tall]), id, "Tall", item[1])
            menu.addDrink(drink_tall)
            id += 1

            drink_grande = Drink("Grande " + item[name], float(item[grande]), id, "Grande", item[1])
            menu.addDrink(drink_grande)
            id += 1

            drink_venti = Drink("Venti " + item[name], float(item[venti]), id, "Venti", item[1])
            menu.addDrink(drink_venti)
            id += 1
        if item[0] == 'Food':
            # food = [item[name], item[price]]
            # foods.append(food)

            food = Food(item[name], item[price], id, item[1])
            menu.addFood(food)
            id += 1
        if item[0] == 'Customization':
            # custom = [item[name], item[price]]
            # customization.append(custom)
            custom = Customization(item[name], item[price], id, item[1])
            menu.addCustomization(custom)
            id += 1


menu = Menu()

# file = open(r"C:\Users\Ashwin\Documents\CSCE331\menu\StarbucksMenu.csv", "r")
file = open(r"C:\Users\ohbro\OneDrive\Desktop\CSCE331\Project2\Project2TeamRHO\StarbucksMenu.csv", "r")

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
        for custom in range(random.randrange(4)):
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

file_out.write("mm-dd-yyyy HH:MM:SS, order_id, sub_category, price, name, item_id, shots, venti_iced, syrup, non_dairy\n")

days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

order_id = 1

random_times = []

game_day_one_month = 9
game_day_one_day = 9
game_day_two_month = 11
game_day_two_day = 25

year = 2022

for month in range(12):
    month = (month + 2) % 12
    if month == 1:
        year += 1
    for day in range(days_in_month[month]):

            random_times = []

            '''
            for i in range(random.randrange(100)):
                hour = random.randrange(12) + 8
                minute = random.randrange(60)
                second = random.randrange(60)
                date = datetime.datetime(2023, month + 1, day + 1, hour, minute, second)
                date_str = date.strftime("%m-%d-%Y %H:%M:%S")
                random_times.append(date_str)
            sort(random_times)
            '''
            num_orders = random.randrange(500, 700)
            if month == game_day_one_month and day == game_day_one_day:
                num_orders = random.randrange(1100, 1300)
            elif month == game_day_two_month and day == game_day_two_day:
                num_orders = random.randrange(1200, 1500)

            for i in range(num_orders):
                hour = random.randrange(12) + 8
                minute = random.randrange(60)
                second = random.randrange(60)
                date = datetime.datetime(year, month + 1, day + 1, hour, minute, second)
                date_str = date.strftime("%m-%d-%Y %H:%M:%S")
                random_times.append(date_str)
            random_times.sort()

            for i in range(num_orders):
                order = generate_order("00/00/0000")
                '''
                hour = random.randrange(12) + 8
                minute = random.randrange(60)
                second = random.randrange(60)

                date = datetime.datetime(2023, month + 1, day + 1, hour, minute, second)

                date_str = date.strftime("%m-%d-%Y %H:%M:%S")
                '''
                date_str = random_times[i]


                for drink in order.drinks:

                    shots = False
                    venti_iced = False
                    syrup = False
                    non_dairy = False

                    for custom in drink.customizations:
                        shots = shots or custom.name == "Shots"
                        venti_iced = venti_iced or custom.name == "Venti Iced"
                        syrup = syrup or custom.name == "Syrup"
                        non_dairy = non_dairy or custom.name == "Non Dairy"

                    string = date_str + ", " + str(order_id) + ", " + drink.sub_category + ", " + str(round(drink.price, 2)) + ", " + drink.name + ", " + str(drink.id) + ", " + str(shots) + ", " + str(venti_iced) + ", " + str(syrup) + ", " + str(non_dairy) + "\n"
                    file_out.write(string)

                for food in order.foods:

                    string = date_str + ", " + str(order_id) + ", " + drink.sub_category + ", " + str(round(food.price, 2)) + ", " + food.name + ", " + str(food.id) + ", False, False, False, False\n"
                    file_out.write(string)


                # FORMAT: mm-dd-yyyy HH:MM:SS, order_id, sub_category, price, name, item_id, shots, venti_iced, syrup, non_dairy

                order_id += 1

file_out.close()
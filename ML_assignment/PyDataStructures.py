#python data structures
# 1. List:list is a sequence data type in python, it can be written as a list of comma-separated values (items) between square brackets. Lists might contain items of different types
squares = [1, 4, 9, 16, 25]
print(squares)
print(squares[0]) # indexing returns the item
print(squares[-1])
print(squares[-3:]) # slicing returns a new list
print(squares + [36, 49, 64, 81, 100]) #List concatination
squares[0] = 2
print(squares) # List are mutable, meaning unlike strings... the values of list can be changed
squares.append(36) #append() helps to add the items in the list at its last
print(squares)
rgb = ["Red", "Green", "Blue"] #When you assign a list to a variable, the variable refers to the existing list. Any changes you make to the list through one variable will be seen through all other variables that refer to it
rgba = rgb
rgba.append("Alph")
print(rgb)
correct_rgba = rgba[:] #All slice operations return a new list containing the requested elements. This means that the following slice returns a shallow copy of the list
correct_rgba[-1] = "Alpha"
print(correct_rgba)
print(rgba)
letters = ['a', 'b', 'c', 'd']
print(len(letters))
a = ['a', 'b', 'c']
n = [1, 2, 3]
x = [a, n]

#it is possible to create list inside a list
print(x)
print(x[0])
print(x[1])
print(x[0][1])
print(x[1][2])
list1=[1,2,3,4,5]
list2=[6,7,8,9,10]
print(list1.extend(list2))
list1.insert(0,0)
print(list1)
print(list1.remove(0))
fruits = ['orange', 'apple', 'pear', 'banana', 'kiwi', 'apple', 'banana']
print(fruits.index('banana'))
print(fruits.reverse())
print(fruits.sort())
print(fruits.pop()) 

#List Comprehensions
square = []
for x in range(10):
    squares.append(x**2)
    
#normal looping!
squares = [x**2 for x in range(10)] #list comprehensions


#TUPLES
#they are ordered, and are immutable, allows hetrogenous values
t = 12345, 54321, 'hello!'
print(t)
u = t, (1, 2, 3, 4, 5)#nested tuple

#t[0] = 88888 tuples are immutable, this will throw an error: 'tuple' object does not support item assignment

v = ([1, 2, 3], [3, 2, 1])
print(v)
#tuple allow duplicates 
newT=("k","k")
print(newT)

#accessing the values of a tuple can also be done by unpacking

x,y=newT
print(x,y)


#SETS

'''
. A set is an unordered collection with no duplicate elements. Basic uses include membership testing and eliminating duplicate entries. 
Set objects also support mathematical operations like union, intersection, difference, and symmetric difference.
'''

basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
print(basket)

# Demonstrate set operations on unique letters from two words
a = set('abracadabra')
b = set('alacazam')
print(a)# unique letters in a
print(b)
print(a-b) # letters in a but not in b
print(a|b)# letters in a or b or both
print(a&b)# letters in both a and b
print(a^b)# letters in a or b but not both


#DICTIONARY

#dictionary stores data in key:value pair format

#The main operations on a dictionary are storing a value with some key and extracting the value given the key

names = {1:'komal',2:'keerthu',3:'bhavani',4:'bhavana'}
print(names)
names[5]='idiot'
print(names)
print(list(names))

#The dict() constructor builds dictionaries directly from sequences of key-value pairs:
new=dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])
print(new)

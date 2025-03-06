import numpy as np 

N=int(input("enter the value of N to generate Ndarray:"))
shape = tuple([N] * N)
arr = np.random.randint(1, 1000, size=shape)
print(arr)

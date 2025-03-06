result = float(input("Enter the first number: "))
while True:
    print("\n1. ADD (+)")
    print("2. SUBTRACT (-)")
    print("3. MULTIPLY (*)")
    print("4. DIVIDE (/)")
    print("Enter '#' to quit and show final result.")

    operation = input("Enter the operation (1/2/3/4): ")

    if operation == "#":
        print("\nFinal result:", result)
        break

    if operation not in ["1", "2", "3", "4"]:
        print("Choose an appropriate option")
        continue

    new_number = float(input("Enter the next number: "))

    match operation:
        case "1":
            result += new_number
        case "2":
            result -= new_number
        case "3":
            result *= new_number
        case "4":
            if new_number != 0:
                result /= new_number
            else:
                print("Error! Division by zero is not allowed.")
                continue

    print("*" * 50)
    print("Current result:", result)
    print("*" * 50)
    

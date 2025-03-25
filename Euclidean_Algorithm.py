def gcd_with_steps(a, b):
    steps = []
    while b != 0:
        q = a // b
        r = a % b
        steps.append((a, b, q, r))
        a, b = b, r
    return a, steps


def main():
    try:
        num1 = int(input("Enter the first number: "))
        num2 = int(input("Enter the second number: "))
        if num1 < 0 or num2 < 0:
            print("Please enter non-negative integers.")
        gcd, steps = gcd_with_steps(num1, num2)
        print("\nSteps of the Euclidean algorithm:")
        print(f"{'a':>10} {'b':>10} {'q':>10} {'r':>10}")
        for a, b, q, r in steps:
            print(f"{a:>10} {b:>10} {q:>10} {r:>10}")
        print(f"\nThe GCD of {num1} and {num2} is {gcd}.")
    except ValueError as e:
        print(f"Invalid input: {e}")


if __name__ == "__main__":
    main()
